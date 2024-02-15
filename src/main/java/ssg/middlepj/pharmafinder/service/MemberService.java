package ssg.middlepj.pharmafinder.service;

import java.security.NoSuchAlgorithmException;

import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;

public interface MemberService {

    boolean addmember(MemberDto mem);

	boolean addpharmacy(PharmacyDto pharmacy);

    boolean usernamecheck(String username);
    
    boolean emailcheck(String email);

    MemberDto login(String username, String password);    // 로그인하기

    String findUsernameByEmail(String email);    //이메일로 유저아이디 찾기

	String findPassword(String username, String email) throws NoSuchAlgorithmException; // 비밀번호 찾기 메서드 추가
	
	boolean updatePasswordWithTemporary(String username, String temporaryPassword, String newPassword) throws NoSuchAlgorithmException;

	MemberDto getMemberInfoByUsername(String username);

	boolean registerPharmacy(MemberDto member, PharmacyDto pharmacy) throws NoSuchAlgorithmException;

}
