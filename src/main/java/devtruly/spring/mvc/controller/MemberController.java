package devtruly.spring.mvc.controller;

import devtruly.spring.mvc.service.MemberService;
import devtruly.spring.mvc.vo.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    // 로그인 안했다면 -> member/join
    // 로그인 했다면 -> redirect:/myinfo
    @GetMapping(path = {"/join"})
    public String join(HttpSession session) {
        String returnPage = "member/join";
        if (session.getAttribute("m") != null) returnPage = "redirect:/myinfo";
        LOGGER.info("join 호출!");
        return returnPage;
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
    public String loginOk(MemberVO memberVO, HttpSession session) {
        String returnPage = "member/lgnfail";

        if (msrv.checkLogin(memberVO)) {
            session.setAttribute("m", memberVO); // 세션 저장
            returnPage = "redirect:/myinfo";
        }

        return returnPage;
    }

    @GetMapping(path = {"/logout"})
    public String myinfo(
            HttpSession session
    ) {
        session.invalidate(); // 모든 세션 제거
        //model.addAttribute("member", msrv.selectMember(mno));
        return "redirect:/";
    }

    // 로그인 안했다면 -> redirect:/login
    // 로그인 했다면 -> member/myinfo
    @GetMapping(path = {"/myinfo"})
    public String myinfo(
            HttpSession session,
            Model model
    ) {
        String returnPage = "member/myinfo";
        if (session.getAttribute("m") != null) {
            MemberVO memberVO = (MemberVO) session.getAttribute("m");
            model.addAttribute("member", msrv.readOneMember(memberVO.getUserid()));
        }
        else {
            returnPage = "redirect:/login";
        }

        return returnPage;
    }
}
