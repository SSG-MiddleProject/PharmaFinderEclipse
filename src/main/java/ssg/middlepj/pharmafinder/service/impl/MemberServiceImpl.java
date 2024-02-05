package ssg.middlepj.pharmafinder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssg.middlepj.pharmafinder.dao.MemberDao;
import ssg.middlepj.pharmafinder.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDao dao;
	
	public boolean addmember(MemberDto mem) {
		int count = dao.addmember(mem);
		return count>0?true:false;
	}
}
