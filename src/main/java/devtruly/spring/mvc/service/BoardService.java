package devtruly.spring.mvc.service;

import devtruly.spring.mvc.vo.BoardVO;

import java.util.List;

public interface BoardService {
    boolean newBoard(BoardVO boardVO);

    List<BoardVO> readBoard(int snum);

    BoardVO readOneBoard(int boardNo);
}
