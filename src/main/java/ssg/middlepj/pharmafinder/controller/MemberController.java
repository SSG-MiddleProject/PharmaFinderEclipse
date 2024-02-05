package ssg.middlepj.pharmafinder.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ssg.middlepj.pharmafinder.service.impl.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@RequestMapping(value="login.do", method = RequestMethod.GET)
	public String login() {
		System.out.println("MemberController login" + new Date());
		return "member/login";
	}
}
