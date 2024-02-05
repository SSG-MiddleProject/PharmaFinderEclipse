package ssg.middlepj.pharmafinder.service;

import org.springframework.stereotype.Service;

import ssg.middlepj.pharmafinder.dto.MemberDto;

public interface MemberService {
	
	boolean addmember(MemberDto dto);

}
