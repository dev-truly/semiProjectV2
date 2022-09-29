package devtruly.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @GetMapping(path = {"/join"})
    public String join() {
        return "member/join";
    }

    @GetMapping(path = {"/login"})
    public String login() {
        return "member/login";
    }

    @GetMapping(path = {"/myinfo"})
    public String myinfo() {
        return "member/myinfo";
    }
}
