package ssg.middlepj.pharmafinder.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssg.middlepj.pharmafinder.dao.MemberDao;

@Repository
class MemberImpl implements MemberDao {

	@Autowired
	SqlSession session;
	
	String ns = "Member";
}
