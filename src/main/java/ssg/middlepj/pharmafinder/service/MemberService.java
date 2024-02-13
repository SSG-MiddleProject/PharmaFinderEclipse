package ssg.middlepj.pharmafinder.service;

import ssg.middlepj.pharmafinder.dto.MemberDto;

public interface MemberService {

	
	boolean idcheck(String username);	
	boolean addmember(MemberDto dto);
	boolean login(String username, String password);	// 로그인하기
	
	String findPassword(String username, String email); // 비밀번호 찾기 메서드 추가
	String findUsernameByEmail(String email);	//이메일로 유저아이디 찾기
}
