package project.projectspring.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import project.projectspring.domain.Member;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositoryTest {
    //테스트 주도 개발
    //구현 클래스를 만들고, 잘 작동하는지 테스트 케이스를 제작
    //혼자 개발 시에는 테스트 케이스가 없어도 큰 문제 발생 가능성 낮음
    //but 단체 개발 시 무조건 중요하며, 익숙해져야 함

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        //테스트가 실행이되고 끝날 때마다 repository를 지운다. 그러므로 순서가 상관이 없어짐.
        repository.clearStore();

    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}
