package devtruly.spring.mvc.dao;

import devtruly.spring.mvc.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO {
    //@Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleInsert;

    public MemberDAOImpl(DataSource dataSource) {
        simpleInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("member")
                .usingColumns("userid", "passwd", "name", "email");
    }

    @Override
    public int insertMember(MemberVO memberVO) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(memberVO);

        return simpleInsert.execute(params);
    }
}
