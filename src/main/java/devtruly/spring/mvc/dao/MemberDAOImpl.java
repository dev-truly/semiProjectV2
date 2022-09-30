package devtruly.spring.mvc.dao;

import devtruly.spring.mvc.vo.MemberVO;
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

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO {
    //@Autowired
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcNamedTemplate;
    private SimpleJdbcInsert simpleInsert;
    private RowMapper<MemberVO> memberMapper = BeanPropertyRowMapper.newInstance(MemberVO.class);

    public MemberDAOImpl(DataSource dataSource) {
        simpleInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("member")
                .usingColumns("userid", "passwd", "name", "email");
        jdbcNamedTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int insertMember(MemberVO memberVO) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(memberVO);

        return simpleInsert.execute(params);
    }

    @Override
    public MemberVO selectOneMember(int mno) {
        String sql = "Select userid, passwd, email, regdate from member where mno = :mno";

        return jdbcNamedTemplate.queryForObject(sql, Collections.singletonMap("mno", mno), memberMapper);
    }
}
