package devtruly.spring.mvc.controller;

import devtruly.spring.mvc.service.BoardService;
import devtruly.spring.mvc.vo.BoardVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BoardController {
    Logger LOGGER = LoggerFactory.getLogger(getClass());

    // bean 클래스로 정의한 경우 @Autowired 어노테이션 생략 가능 Spring 5.0 이상
    @Autowired
    BoardService bsrv;

    @GetMapping(path = {"/board/list"})
    public String list(Model model) {
        model.addAttribute("bdlist", bsrv.readBoard());

        return "board/list";
    }
    @GetMapping(path = {"/board/view"})
    public String view() {
        return "board/view";
    }

    @GetMapping(path = {"/board/write"})
    public String write(HttpSession session) {
        String returnPage = "board/write";

        if (session.getAttribute("m") == null) returnPage = "redirect:/login";

        return returnPage;
    }

    @PostMapping(path = {"/board/write"})
    public String writeok(BoardVO boardVO) {
        bsrv.newBoard(boardVO);

        return "redirect:/board/list";
    }
}
