package ssg.middlepj.pharmafinder.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.dto.StoreDto;
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
	public String usernamecheck(@RequestParam("username") String username) {
		// System.out.println("MemberController usernamecheck " + new Date());
		// System.out.println("username:" + username);
		
		boolean b = service.usernamecheck(username);
		return b?"YES":"NO";
	}
	
	
	@PostMapping("/regi.do") // 메서드가 처리할 요청 경로 지정
	public String regi(MemberDto mem, StoreDto store) {

		boolean memberAdded = service.addmember(mem);
        if (mem.getRoll() == 0 || mem.getRoll() == 1) { // 약국 관리자 또는 직원일 경우
			boolean storeAdded = service.addstore(store);
            if (memberAdded && storeAdded) {
                return "redirect:/login";
        } else if (mem.getRoll() == 2) { // 고객일 경우
            if (memberAdded) {
            	return "redirect:/login";
            }
        }
        }
        System.out.println("MemberController regi " + new Date());		
		return "regi"; // 컨트롤러 처리 결과를 보여줄 뷰 이름

    }
}