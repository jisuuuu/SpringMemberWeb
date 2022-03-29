package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service //DI
@Transactional //jpa를 사용하려면 transation 필요!
public class MemberService {

    private final MemberRepository memberRepository;

    //new 해서 매번 생성되는 것이 아니라 외부에서 넣어주도록 설정해야함
    //@Autowired //DI
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     *
     * 회원 가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 X
        //과거에는 if로 null 처리를 하였지만 지금은 Optinal로 한 번 감싸서 확인
        //코드가 안 이쁘면 이렇게 할 수 있음

        validateDulicateMember(member); //중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDulicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     *
     * id로 회원 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
