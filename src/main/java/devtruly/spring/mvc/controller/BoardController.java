package devtruly.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    @GetMapping(path = {"/board/list"})
    public String list() {
        return "board/list";
    }
}
