package ssg.middlepj.pharmafinder.service;

import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.dto.StoreDto;

public interface MemberService {
	
	boolean usernamecheck(String username);	
	boolean addmember(MemberDto dto);
	boolean addstore(StoreDto dto);

}
