package project.projectspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller

public class MemberController {

    private final MemberController memberService;

    //Autowired: memberService를 스프링이 스프링 컨테이너에 있는 memberService를 가져와서 연결
    @Autowired
    public MemberController(MemberController memberService) {
        this.memberService = memberService;
    }
}
