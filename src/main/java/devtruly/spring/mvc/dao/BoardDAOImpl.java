package devtruly.spring.mvc.dao;

import devtruly.spring.mvc.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository("bdao")
public class BoardDAOImpl implements BoardDAO {
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleInsert;

    public BoardDAOImpl(DataSource dataSource) {
        simpleInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("board")
                .usingColumns("title", "userid", "contents");
    }

    @Override
    public int insertBoard(BoardVO boardVO) {
        /*String sql = "Insert into board (title, userid, contents) values (?, ?, ?);";
        Object[] params = new Object[] {
                boardVO.getTitle(), boardVO.getUserid(), boardVO.getContents()
        };
        return jdbcTemplate.update(sql, params);*/

        Map<String, String> map = new HashMap<>();

        map.put("title", boardVO.getTitle());
        map.put("userid", boardVO.getUserid());
        map.put("contents", boardVO.getContents());

        return simpleInsert.execute(map);
        //return 0;
    }
}
