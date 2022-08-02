package project.projectspring.repository;

import project.projectspring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                //member.getName이 파라미터로 넘어온 name 같은지 확인하고, 같은 경우만 필터링
                .filter(member -> member.getName().equals(name))
                //Map을 돌면서 하나라도 찾으면 결과가 Optional로 반환
                //완전 탐색했는데도 없으면 Optoinal에 null이 포함돼서 반환
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //store에 있는 member가 반환됨.
        return new ArrayList<>(store.values());
    }
}
