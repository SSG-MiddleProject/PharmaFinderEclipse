package ssg.middlepj.pharmafinder.service;

import ssg.middlepj.pharmafinder.dto.MemberDto;

public interface MemberService {
	
	boolean usernamecheck(String username);	
	boolean addmember(MemberDto dto);

}
