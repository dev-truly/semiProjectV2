package devtruly.spring.mvc.service;

import devtruly.spring.mvc.vo.MemberVO;

import java.util.List;

public interface MemberService {
    boolean newMember(MemberVO memberVO);

    MemberVO selectMember(int mno);

    boolean checkLogin(MemberVO memberVO);
}
