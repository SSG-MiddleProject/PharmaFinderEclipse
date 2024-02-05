package ssg.middlepj.pharmafinder.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ssg.middlepj.pharmafinder.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@GetMapping("/regi.do")
	public String regi() {
		System.out.println("MemberController regi " + new Date());		
		return "regi";
	}
}
