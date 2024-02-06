package ssg.middlepj.pharmafinder.service;

import ssg.middlepj.pharmafinder.dto.MemberDto;

public interface MemberService {

	
	boolean idcheck(String id);	
	boolean addmember(MemberDto dto);
}
