package ssg.middlepj.pharmafinder.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssg.middlepj.pharmafinder.dao.MemberDao;
import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.dto.StoreDto;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Autowired
	SqlSession session;
	private static final String namespace = "ssg.middlepj.pharmafinder.dao.MemberDao";
	
	String ns = "Member.";

	@Override
	public int usernamecheck(String username) {
	    Integer count = session.selectOne(namespace + ".usernamecheck", username);
	    return (count != null) ? count : 0; // Returns the count, with a null check
	}
	
	@Override
	public int addmember(MemberDto mem) {
		return session.insert(ns + "addmember", mem);
	}
	
	@Override
	public int addstore(StoreDto store) {
		return session.insert(ns + "addstore", store);
	}
}
