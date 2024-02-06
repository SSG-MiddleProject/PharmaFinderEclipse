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

@Controller // 컨트롤러 사용
public class MemberController {
	
	@Autowired
	MemberService service;
	
	// username 체크 (HTTP get 요청)
	/*
	 	`usernamecheck.do`에 대한 요청을 메소드로 매핑
	 	요청 처리 결과를 HTTP 응답 본문에 직접 쓰도록 구성
	 */
	@ResponseBody // 메서드에서 반환하는 값을 HTTP 응답 본문에 직접 쓰도록 지시, 별도의 뷰를 거치지 않고 HTTP 응답으로 데이터를 직접 반환
	@RequestMapping(value = "/usernamecheck.do",
						produces = "text/plain; charset=utf-8")
	public String usernamecheck(String username) {
		System.out.println("MemberController usernamecheck " + new Date());
		System.out.println("username:" + username);
		
		boolean b = service.usernamecheck(username);
		String r = "YES";
		if(b == true) {
			r = "NO";
		}
		
		return r;
	}
	
	@GetMapping("/regi.do") // 메서드가 처리할 요청 경로 지정
	public String regi() {
		System.out.println("MemberController regi " + new Date());		
		return "regi"; // 컨트롤러 처리 결과를 보여줄 뷰 이름
	}
}
