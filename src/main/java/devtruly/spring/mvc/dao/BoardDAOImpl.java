package devtruly.spring.mvc.dao;

import devtruly.spring.mvc.vo.BoardVO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("bdao")
public class BoardDAOImpl implements BoardDAO {
    //@Autowired // bean태그에 정의한 경우 생략가능
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcNamedTemplate;
    private RowMapper<BoardVO> boardMapper = BeanPropertyRowMapper.newInstance(BoardVO.class);

    private SimpleJdbcInsert simpleInsert;

    public BoardDAOImpl(DataSource dataSource) {
        simpleInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("board")
                .usingColumns("title", "userid", "contents");

        jdbcNamedTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int insertBoard(BoardVO boardVO) {
        /*String sql = "Insert into board (title, userid, contents) values (?, ?, ?);";
        Object[] params = new Object[] {
                boardVO.getTitle(), boardVO.getUserid(), boardVO.getContents()
        };
        return jdbcTemplate.update(sql, params);*/

        /*Map<String, String> map = new HashMap<>();

        map.put("title", boardVO.getTitle());
        map.put("userid", boardVO.getUserid());
        map.put("contents", boardVO.getContents());

        return simpleInsert.execute(map);*/
        SqlParameterSource params = new BeanPropertySqlParameterSource(boardVO);

        return simpleInsert.execute(params);
        //return 0;
    }

    @Override
    public List<BoardVO> selectBoard(int snum) {
        String sql = "Select bno, title, userid, regdate, views From board order by bno desc limit :snum, 25";
        Map<String, Object> params = new HashMap<>();
        params.put("snum", snum);
        //params.put("enum", snum + 25);
        return jdbcNamedTemplate.query(sql, params, boardMapper);
    }

    @Override
    public BoardVO selectOneBoard(int boardNo) {
        String sql = " update board set views = views + 1 where bno = :bno";
        jdbcNamedTemplate.query(sql, Collections.singletonMap("bno", boardNo), boardMapper);

        sql = "Select * From board Where bno = :bno";
        BoardVO board = null;

        List<BoardVO> boardList = jdbcNamedTemplate.query(sql, Collections.singletonMap("bno", boardNo), boardMapper);

        if (boardList.size() >= 1) {
            board = boardList.get(0);
        }

        return board;
    }
}
