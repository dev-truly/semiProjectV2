package devtruly.spring.mvc.service;

import devtruly.spring.mvc.vo.BoardVO;

import java.util.List;

public interface BoardService {
    boolean newBoard(BoardVO boardVO);

    List<BoardVO> readBoard(int snum, String fkey, String fvalue);

    BoardVO readOneBoard(int boardNo);

    int readCountBoard(String fkey, String fvalue);

    boolean delBoard(int boardNo);

    boolean modifyBoard(BoardVO boardVO);
}
