package ssg.middlepj.pharmafinder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssg.middlepj.pharmafinder.dao.MemberDao;

@Service
public class MemberService implements ssg.middlepj.pharmafinder.service.MemberService {

	@Autowired
	MemberDao dao;
}
