package devtruly.spring.mvc.service;

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
    public MemberVO selectMember(int mno) {
        return mdao.selectOneMember(mno);
    }

    @Override
    public boolean checkLogin(MemberVO memberVO) {
        boolean isLogin = false;
        if ((mdao.selectOneMember(memberVO)) > 0) isLogin = true;
        System.out.println(isLogin);
        return isLogin;
    }
}
