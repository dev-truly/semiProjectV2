package devtruly.spring.mvc.service;

import devtruly.spring.mvc.dao.BoardDAO;
import devtruly.spring.mvc.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bsrv")
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDAO bdao;

    @Override
    public boolean newBoard(BoardVO boardVO) {
        boolean result = false;
        if (bdao.insertBoard(boardVO) > 0) result = true;
        return result;
    }
}
