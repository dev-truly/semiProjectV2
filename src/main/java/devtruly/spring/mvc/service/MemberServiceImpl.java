package devtruly.spring.mvc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import devtruly.spring.mvc.dao.MemberDAO;
import devtruly.spring.mvc.dao.MemberDAOImpl;
import devtruly.spring.mvc.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("msrv")
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDAO mdao;
    @Override
    public boolean newMember(MemberVO memberVO) {
        boolean isInsert = false;
        // 회원 가입이 성공시 true 리턴
        if (mdao.insertMember(memberVO) > 0) isInsert = true;

        return isInsert;
    }

    @Override
    public MemberVO readOneMember(String userId) {
        return mdao.selectOneMember(userId);
    }

    @Override
    public boolean checkLogin(MemberVO memberVO) {
        boolean isLogin = false;
        if ((mdao.selectOneMember(memberVO)) > 0) isLogin = true;
        System.out.println(isLogin);
        return isLogin;
    }

    @Override
    public int checkUserid(String userId) {
        return mdao.selectCountUserId(userId);
    }

    @Override
    public String serachZipcode(String searchText) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";

        return mapper.writeValueAsString(
                mdao.selectZipode(searchText + "%")
        );
    }
}
