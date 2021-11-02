package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    /*
    private final MemberService memberService = new MemberService();
    안에 들어가면 별 기능 없음
    하나만 생성 후 공용으로 쓰면되기 때문에 그래서 이렇게 사용하지 않고 스프링 컨테이너에 주입함
    */
    private final MemberService memberService;
    //@Autowired private MemberService memberService; //Field 주입 권장하지 않음-> 수정 방법이 없기 때문

    /*
    @Autowired
    public void setMemberService(MemberService memberService){
        this.memberService = memberService;
    }
    //Setter injection 주입 -> public이기 때문에 누군가가 조작할 수 있음, 설정하면 바꿀 일이 없는데
     */

    @Autowired //DI : 의존관계를 주입하는 것
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
