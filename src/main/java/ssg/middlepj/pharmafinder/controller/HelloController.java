package ssg.middlepj.pharmafinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ssg.middlepj.pharmafinder.service.HelloService;

@Controller
public class HelloController {
	@Autowired
	HelloService helloService;
	
	@GetMapping("/hello.do")
	public String hello(Model model) {
		System.out.println("HelloController hello");
		model.addAttribute("hello", helloService.selectHello());
		return "hello";
	}
}
