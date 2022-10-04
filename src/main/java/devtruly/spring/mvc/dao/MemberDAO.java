package devtruly.spring.mvc.dao;

import devtruly.spring.mvc.vo.MemberVO;

import java.util.List;

public interface MemberDAO {
    int insertMember(MemberVO memberVO);

    MemberVO selectOneMember(String userId);

    int selectOneMember(MemberVO memberVO);
}
