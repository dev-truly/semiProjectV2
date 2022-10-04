package devtruly.spring.mvc.dao;

import devtruly.spring.mvc.vo.BoardVO;

import java.util.List;

public interface BoardDAO {
    int insertBoard(BoardVO boardVO);

    List<BoardVO> selectBoard();

    BoardVO selectOneBoard(int boardNo);
}
