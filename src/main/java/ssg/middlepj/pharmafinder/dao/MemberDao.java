package ssg.middlepj.pharmafinder.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ssg.middlepj.pharmafinder.dto.MemberDto;	// user 테이블 정보를 다루는 DTO
import ssg.middlepj.pharmafinder.dto.StoreDto;	// store 테이블 정보를 다루는 DTO

public interface MemberDao {
	
	/* 아이디 중복 체크 */
	int usernamecheck(String username);
	
	/* 회원 추가(회원가입) */
	int addmember(MemberDto mem);	// 유저 공통
	int addstore(StoreDto store);	// 약국 추가정보
}
