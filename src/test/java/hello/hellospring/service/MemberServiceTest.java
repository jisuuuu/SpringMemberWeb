package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

// Spring 띄우는 테스트는 시간이 좀 걸리기 때문에 순수 자바 테스트도 진행함
// 단위 테스트를 진행하는 것이 중요 -> 단위 테스트를 제대로 진행하는 것이 좋은 자바 코드
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //테스트가 독립적으로 실행되어야하기 때문에 외부에서 생성자로 넣어주도록 바꿈
    // Dependency Injection
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { //실제 동작하는 메소드 아니니까 테스트니까 한글로 써도 괜찮아 -> 직관적이라 오히려 좋아
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    /*
    예외 테스트
     */
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //alt + ctrl + v
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }

 */

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}