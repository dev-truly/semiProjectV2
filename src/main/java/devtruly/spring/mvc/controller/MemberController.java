package devtruly.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @GetMapping(path = {"/join"})
    public String join() {
        return "member/join";
    }

    @PostMapping(path = {"/join"})
    public String joinOk() {
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
