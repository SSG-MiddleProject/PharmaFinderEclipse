package ssg.middlepj.pharmafinder.service;

import java.security.NoSuchAlgorithmException;

import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;

public interface MemberService {

    boolean addmember(MemberDto mem);	// 회원추가

	boolean addpharmacy(PharmacyDto pharmacy);	// 약추가

    boolean idcheck(String username);	// 아이디 중복체크
    
    boolean emailcheck(String email);	// 이메일 중복체크

    MemberDto login(String username, String password);    // 로그인하기

    String findUsernameByEmail(String email);    //이메일로 유저아이디 찾기

	String findPassword(String username, String email) throws NoSuchAlgorithmException; // 비밀번호 찾기 메서드 추가
	
	boolean updatePasswordWithTemporary(String username, String temporaryPassword, String newPassword) throws NoSuchAlgorithmException;
	
	boolean updateMember(MemberDto member) throws NoSuchAlgorithmException;	// 회원 정보 업데이트 메서드
}
