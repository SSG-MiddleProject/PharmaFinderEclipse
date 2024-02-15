package ssg.middlepj.pharmafinder.dao;

import java.util.Map;

import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;

public interface MemberDao {
    // 중복체크
    int idcheck(String username);
    int emailcheck(String email);

    // 회원가입
    int addmember(MemberDto mem);
	int addpharmacy(PharmacyDto pharmacy);    // 약국 추가정보

    // 로그인
    // boolean login(Map<String, String> paramMap); // 인자를 두 개로 변경
	MemberDto login(Map<String, String> paramMap);
	
	// 아이디찾기 (이메일로 찾기)
    String findUsernameByEmail(String email);
	
    // 비밀번호찾기
    boolean updateTemporaryPassword(String username, String email, String temporaryPassword);
	
    boolean updatePasswordWithTemporary(String username, String encryptedTempPassword, String encryptedNewPassword);

}