package project.projectspring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import project.projectspring.domain.Member;
import org.junit.jupiter.api.Test;
import project.projectspring.repository.MemoryMemberRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {
    //테스트는 한글로 작성해도 무관하다.
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    //join을 거친 후, validateDuplicateMember을 잘 통과하는지 확인
    public void 중복_회원_예외() {
        //given

        //만약 setName 안의 메시지가 똑같으면 join의 validateDuplicateMember에서 걸려서 IllegalStateException에서 터짐
        //그러므로 when이하의 내용을 작성하였음.
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        //밑의 try catch보다 좋은 문법 예시
        memberService.join(member1);

        //memberService.join을 실행하는데, IllegalStateException이 터져야함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            //isEqualTo의 메세지는 MemberService와 같아야한다.
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
//        //then

    }
    @Test
    void findMembers() {

    }

    @Test
    void findOne() {
    }
}