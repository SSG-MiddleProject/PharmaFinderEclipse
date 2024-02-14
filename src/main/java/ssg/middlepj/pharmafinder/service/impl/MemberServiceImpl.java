package ssg.middlepj.pharmafinder.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssg.middlepj.pharmafinder.dao.MemberDao;
import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;
import ssg.middlepj.pharmafinder.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
    
	@Autowired
    MemberDao dao;

    @Override
    public boolean idcheck(String username) {
        int count = dao.idcheck(username);
        return count > 0;
    }
    
    @Override
    public boolean emailcheck(String email) {
        int count = dao.emailcheck(email);
        return count > 0;
    }

    @Override
    public boolean addmember(MemberDto mem) {
        int count = dao.addmember(mem);
        return count > 0;
    }

    @Override
    public boolean addpharmacy(PharmacyDto pharmacy) {
        int count = dao.addpharmacy(pharmacy);
        return count > 0;
    }

	@Override
	public MemberDto login(String username, String password) {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("username", username);
		paramMap.put("password", password);
		// DAO의 수정된 login 메서드 호출
		return dao.login(paramMap);
	}
	
    //이메일로 유저아이디 찾기
    @Override
    public String findUsernameByEmail(String email) {
        // 이메일을 통해 아이디를 조회하는 DAO 메서드 호출
        return dao.findUsernameByEmail(email);
    }
    
    // 이메일과 유저 로그인id로 비밀번호 찾기
    @Override
    public boolean updatePassword(String username, String email) {
    	// 아이디와 이메일을 기준으로 임시 비밀번호 생성
        String temporaryPassword = generateTemporaryPassword();
        
        // 임시 비밀번호를 사용자 계정에 업데이트
        boolean updateResult = dao.updateTemporaryPassword(username, email, temporaryPassword);
        
        if (updateResult) {
            // 업데이트 성공 시, 임시 비밀번호를 사용자 이메일로 전송
            sendTemporaryPasswordEmail(email, temporaryPassword);
            return true;
        }
        
        // 업데이트 실패 시
        return false;
    }

    private String generateTemporaryPassword() {
        // 임시 비밀번호 생성 로직 구현 (예: 랜덤 문자열 생성)
        return RandomStringUtils.randomAlphanumeric(10);
    }
    
    private void sendTemporaryPasswordEmail(String email, String temporaryPassword) {
        // 임시 비밀번호를 포함하는 이메일 전송 로직 구현
        // 이메일 전송 기능에 대한 구현은 프로젝트의 이메일 전송 방식에 따라 다를 수 있음
    }

}