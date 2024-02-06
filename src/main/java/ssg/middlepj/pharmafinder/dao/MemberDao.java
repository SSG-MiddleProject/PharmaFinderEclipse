package ssg.middlepj.pharmafinder.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ssg.middlepj.pharmafinder.dto.MemberDto;

public interface MemberDao {
	
	/* 아이디 중복 체크 */
	int usernamecheck(String username);
	
	/* 회원 추가(회원가입) */
	int addmember(MemberDto mem);
}
