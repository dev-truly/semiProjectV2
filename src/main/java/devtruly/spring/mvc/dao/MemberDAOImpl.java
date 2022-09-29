package devtruly.spring.mvc.dao;

import devtruly.spring.mvc.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insertMember(MemberVO memberVO) {
        String sql = "insert into member" +
                "(userid, passwd, name, email)" +
                " values (?, ?, ?, ?)";

        Object[] params = new Object[] {
            memberVO.getUserid(),
            memberVO.getPasswd(),
            memberVO.getName(),
            memberVO.getEmail(),
        };

        return jdbcTemplate.update(sql, params);
    }
}
