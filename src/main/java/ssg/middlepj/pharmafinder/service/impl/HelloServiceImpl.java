package ssg.middlepj.pharmafinder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssg.middlepj.pharmafinder.dao.HelloDao;
import ssg.middlepj.pharmafinder.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {
	@Autowired
	HelloDao helloDao;
	
	@Override
	public String selectHello() {
		return helloDao.selectHello();
	}

}
