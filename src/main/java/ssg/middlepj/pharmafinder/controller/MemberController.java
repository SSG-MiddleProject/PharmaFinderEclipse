package ssg.middlepj.pharmafinder.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ssg.middlepj.pharmafinder.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@ResponseBody
	@RequestMapping(value = "usernameCheck.do",
						method = RequestMethod.GET,
							produces = "application/String; charset=utf-8")
	public String idcheck(String id) {
		System.out.println("MemberController idcheck " + new Date());
		System.out.println("");
	return "aa";
	}
	
	@GetMapping("/regi.do")
	public String regi() {
		System.out.println("MemberController regi " + new Date());		
		return "member/regi";}


		@RequestMapping(value="login.do", method = RequestMethod.GET)
		public String login() {
			System.out.println("MemberController login" + new Date());
			return "member/login";
	}
}
