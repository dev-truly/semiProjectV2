package devtruly.spring.mvc.dao;

import devtruly.spring.mvc.vo.BoardVO;

import java.util.List;

public interface BoardDAO {
    int insertBoard(BoardVO boardVO);

    List<BoardVO> selectBoard(int snum, String fkey, String fvalue);

    BoardVO selectOneBoard(int boardNo);

    int selectBoardCount(String fkey, String fvalue);

    boolean deleteBoard(int boardNo);

    int updateBoard(BoardVO boardVO);
}
