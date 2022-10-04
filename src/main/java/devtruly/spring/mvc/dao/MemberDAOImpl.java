package devtruly.spring.mvc.dao;

import devtruly.spring.mvc.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcNamedTemplate;
    private SimpleJdbcInsert simpleInsert;

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
    public MemberVO selectOneMember(String userId) {
        String sql = "Select userid, name, email, regdate from member where userid = :userid";
        RowMapper<MemberVO> memberMapper = (rs, num) -> {
            MemberVO m = new MemberVO();
            m.setUserid(rs.getString("userid"));
            m.setName(rs.getString("name"));
            m.setEmail(rs.getString("email"));
            m.setRegdate(rs.getString("regdate"));

            return m;
        };

        return jdbcNamedTemplate.queryForObject(sql, Collections.singletonMap("userid", userId), memberMapper);
    }

    @Override
    public int selectOneMember(MemberVO memberVO) {
        String sql = "Select count(mno) from member Where userid = ? and passwd = ? ";
        Object[] params = { memberVO.getUserid(), memberVO.getPasswd() };

        return jdbcTemplate.queryForObject(sql, params, Integer.class);
    }
    /*
    // 콜백 메소드의 정의
    private class MemberRowMapper implements RowMapper<MemberVO> {

        @Override
        public MemberVO mapRow(ResultSet rs, int num) throws SQLException {
            MemberVO m = new MemberVO();
            m.setUserid(rs.getString("userid"));
            m.setPasswd(rs.getString("name"));식
            m.setEmail(rs.getString("email"));
            m.setRegdate(rs.getString("regdate"));

            return m;
        }
    }*/
}
