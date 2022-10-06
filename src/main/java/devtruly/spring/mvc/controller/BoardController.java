package devtruly.spring.mvc.controller;

import devtruly.spring.mvc.service.BoardService;
import devtruly.spring.mvc.vo.BoardVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class BoardController {
    Logger LOGGER = LoggerFactory.getLogger(getClass());

    // bean 클래스로 정의한 경우 @Autowired 어노테이션 생략 가능 Spring 5.0 이상
    @Autowired
    BoardService bsrv;

    /* 페이징처리
    * 페이지당 게시물수 perPage : 25
    * 총페이지 수 : 전체 게시물수 / 페이지당 게시물 수
    * 총페이지 수 : ceil(getTotalPage / perPage)
    * -----------------------------------------
    * 페이지별 읽어올 게시글 범위
    * 총 게시글이 55건이라 할때
    * 1page : 1 ~ 25
    * 2page : 26 ~ 50
    * 3page : 51 ~ 75
    * ipage : m번째 ~ n번째 게시글 읽어옴
    * snum = (i - 1) * 25
    * 현재 페이지에 따라서 보여줄 페이지 블럭 결정
    * ex) 총페이지수가 27일때
    * stpgn = ((cpg - 1) / 10) * 10 + 1
    * */
    @GetMapping(path = {"/board/list"})
    public String list(Model model,
            @RequestParam(name = "cpg", required = true, defaultValue = "1") int cpg,
            @RequestParam(name = "fkey", required = true, defaultValue = "") String fkey,
            @RequestParam(name = "fvalue", required = true, defaultValue = "") String fvalue
       ) {
        cpg = (cpg < 1) ? 1 : cpg;

        int perPage = 25;
        int snum = (cpg - 1) * perPage;
        int stpgn = ((cpg - 1) / 10) * 10 + 1;

        model.addAttribute("bdlist", bsrv.readBoard(snum, fkey, fvalue));
        model.addAttribute("pages", bsrv.readCountBoard(fkey, fvalue));
        model.addAttribute("cpg", cpg);
        model.addAttribute("stpgn", stpgn);
        model.addAttribute("fkey", fkey);
        model.addAttribute("fvalue", fvalue);

        return "board/list";
    }
    @GetMapping(path = {"/board/view/{boardNo}"})
    public ModelAndView view(
            @PathVariable("boardNo") int boardNo,
            ModelAndView mv
    ) {
        mv.setViewName("board/view");
        mv.addObject("bd", bsrv.readOneBoard(boardNo));
        mv.addObject("bno", boardNo);
        //String returnPage = "redirect:/board/list";

        return mv;
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

    @GetMapping(path = {"/board/del/{boardNo}"})
    public String remove(
            HttpSession session,
        @PathVariable("boardNo") int boardNo
    ) {
        String returnPage = "redirect:/board/view/" + boardNo;

        if (session.getAttribute("m") == null) returnPage = "redirect:/login";

        if (bsrv.delBoard(boardNo)) returnPage = "redirect:/board/list";

        return returnPage;
    }

    @GetMapping(path = {"/board/upd/{boardNo}"})
    public String update(
            HttpSession session,
            @PathVariable("boardNo") int boardNo,
            Model model
    ) {
        String returnPage = "board/modify";

        if (session.getAttribute("m") == null) returnPage = "redirect:/login";
        else model.addAttribute("bd", bsrv.readOneBoard(boardNo));

        return returnPage;
    }

    @PostMapping(path = {"/board/upd/{boardNo}"})
    public String updateok(
            @PathVariable("boardNo") String boardNo,
            BoardVO boardVO
    ) {
        boardVO.setBno(boardNo);
        String returnPage = "redirect:/board/list";

        bsrv.modifyBoard(boardVO);

        return returnPage;
    }
}
