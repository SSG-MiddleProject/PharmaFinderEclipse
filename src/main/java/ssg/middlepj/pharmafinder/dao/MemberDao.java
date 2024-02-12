package ssg.middlepj.pharmafinder.dao;
import org.springframework.stereotype.Repository;
import ssg.middlepj.pharmafinder.dto.MemberDto;

public interface MemberDao {
	
	
	// 아이디 중복체크
	int idcheck(String username);
	
	// 회원가입
	int addmember(MemberDto mem);
	
	// 로그인
	boolean login(String userString, String password);// 인자를 두 개로 변경
	
	// 비밀번호찾기
	String findPassword(String username, String email); // 비밀번호 찾기 메서드 추가
	
	// 아이디찾기
	String findUsernameByEmail(String email);
}
	
	

