package devtruly.spring.mvc.controller;

import devtruly.spring.mvc.service.MemberService;
import devtruly.spring.mvc.vo.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @Autowired
    private MemberService msrv;
    // 로그 유형 : trace, debug, info, warn, error
    // trace : 상세
    // debug : info 보다는 디테일 trace 보다는 러프
    // info : 일반
    // warn : 주의
    // error : 에러 발생
    protected Logger LOGGER = LoggerFactory.getLogger(getClass());

    @GetMapping(path = {"/join"})
    public String join() {

        LOGGER.info("join 호출!");
        return "member/join";
    }

    @PostMapping(path = {"/join"})
    public String joinOk(MemberVO memberVO) {
        LOGGER.info("joinok 호출! {}", memberVO);

        // 회원 정보 저장
        if (msrv.newMember(memberVO)) LOGGER.info("회원 가입 성공!!");

        return "redirect:login";
    }

    @GetMapping(path = {"/login"})
    public String login() {
        return "member/login";
    }

    @PostMapping(path = {"/login"})
    public String loginOk() {
        return "redirect:/myinfo";
    }

    @GetMapping(path = {"/myinfo"})
    public String myinfo() {
        return "member/myinfo";
    }
}
