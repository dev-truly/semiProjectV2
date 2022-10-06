package devtruly.spring.mvc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import devtruly.spring.mvc.vo.MemberVO;

import java.util.List;

public interface MemberService {
    boolean newMember(MemberVO memberVO);

    MemberVO readOneMember(String userId);

    boolean checkLogin(MemberVO memberVO);

    int checkUserid(String userId);

    String serachZipcode(String searchText) throws JsonProcessingException;
}
