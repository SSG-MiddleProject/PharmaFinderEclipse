package ssg.middlepj.pharmafinder.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ssg.middlepj.pharmafinder.dao.MemberDao;
import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberDaoImpl implements MemberDao {

    @Autowired
    SqlSession session;
    private static final String namespace = "ssg.middlepj.pharmafinder.dao.MemberDao";

    String ns = "Member.";

    public int idcheck(String username) {
        return session.selectOne(ns + "idcheck", username);
    }
    
    public int emailcheck(String email) {
        return session.selectOne(ns + "emailcheck", email);
    }

    @Override
    public int addmember(MemberDto mem) {
        return session.insert(ns + "addmember", mem);
    }

    @Override
    public int addpharmacy(PharmacyDto pharmacy) {
        return session.insert(ns + "addpharmacy", pharmacy);
    }

    @Override
    public MemberDto login(Map<String, String> paramMap) {
        // 여기서는 하나의 파라미터로만 전달하도록 변경
        // MyBatis Mapper에서는 하나의 객체에 필요한 데이터를 담아 전달하므로,
        // 여러 개의 파라미터를 받을 수 없습니다.
        // 따라서 Mapper에서는 해당 파라미터를 전달할 수 있는 방식으로 변경해야 합니다.
        // 예를 들어, Map<String, String> 형태로 전달하거나, 객체 하나로 묶어 전달하는 방식을 사용할 수 있습니다.
        // 여기서는 Map<String, String>으로 변경하여 처리합니다.
        return session.selectOne(ns + "login", paramMap);
    }
    
    // 이메일로 아이디 찾기
    @Override
    public String findUsernameByEmail(String email) {
        // 이메일을 통해 아이디를 조회하는 쿼리를 실행하는 부분
        return session.selectOne(ns + "findUsernameByEmail", email);
    }

    // 비밀번호 찾기
    @Override
    public boolean updateTemporaryPassword(String username, String email, String temporaryPassword) {
    	Map<String, Object> paramMap = new HashMap<>();
    	paramMap.put("username", username);
        paramMap.put("email", email);
        paramMap.put("temporaryPassword", temporaryPassword);
    	int updatedRows = session.update(ns + "updateTemporaryPassword", paramMap);
    	return updatedRows > 0;
    }
    
    @Override
    public boolean updatePasswordWithTemporary(String username, String encryptedTempPassword, String encryptedNewPassword) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", username);
        paramMap.put("temporaryPassword", encryptedTempPassword);
        paramMap.put("newPassword", encryptedNewPassword);
        int updatedRows = session.update(ns + "updatePasswordWithTemporary", paramMap);
        return updatedRows > 0;
    }

	@Override
	public int updateMember(MemberDto member) {
		 return session.update("Member.updateMember", member);
	}

}
