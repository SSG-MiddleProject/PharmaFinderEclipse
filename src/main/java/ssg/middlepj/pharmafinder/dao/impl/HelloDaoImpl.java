package ssg.middlepj.pharmafinder.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssg.middlepj.pharmafinder.dao.HelloDao;

@Repository
public class HelloDaoImpl implements HelloDao {
	@Autowired
	SqlSession session;
	
	String ns = "Hello.";
	
	@Override
	public String selectHello() {
		return session.selectOne(ns+"selectHello");
	}
}
