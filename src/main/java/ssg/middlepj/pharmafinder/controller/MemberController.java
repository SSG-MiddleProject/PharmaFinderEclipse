package ssg.middlepj.pharmafinder.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;

	// 아이디 중복 확인 메서드
	@ResponseBody
	@RequestMapping(value = "idcheck.do", method = RequestMethod.POST, produces = "application/String; charset=utf-8")
	public String idcheck(@RequestParam("username") String username) {
		System.out.println("MemberController idcheck " + new Date());
		// DAO를 통해 아이디 중복 체크 수행
		boolean isIdDuplicate = service.idcheck(username);
		// 결과에 따라 YES 또는 NO를 반환
		return isIdDuplicate ? "NO" : "YES";
	}

	// 회원가입(약국) 페이지 이동 메서드
	@GetMapping("/regi.do")
	public String regi() {
		System.out.println("MemberController regi " + new Date());
		return "member/regi";
	}

	// 회원가입(일반 유저) 페이지 이동 메서드
	@GetMapping("/userregi.do")
	public String userregi() {
		System.out.println("MemberController userregi " + new Date());
		return "member/userregi";
	}
	
	// 회원가입 처리 메서드
    @PostMapping("/regiAf.do")
    public String registerProcess(HttpServletRequest request, @RequestParam("username") String username,
            @RequestParam("email") String email, @RequestParam("password") String password) {
        // 회원 정보를 DTO에 설정
        MemberDto memberDto = new MemberDto();
        memberDto.setUsername(username);
        memberDto.setEmail(email);
        memberDto.setPassword(password);
        memberDto.setState(1); // 활성화 상태로 설정
        memberDto.setRoll(2); // 일반 유저로 설정

        // 회원가입 처리
        boolean registerResult = service.addmember(memberDto);
        if (registerResult) {
            // 회원가입이 성공한 경우
            // 로그인 페이지로 리다이렉션 또는 다른 작업 수행
            return "redirect:/login.do";
        } else {
            // 회원가입이 실패한 경우
            // 에러 메시지 등을 설정하여 다시 회원가입 페이지로 이동
            request.setAttribute("error", "회원가입에 실패하였습니다. 다시 시도해주세요.");
            return "member/regi"; // 회원가입 페이지로 이동
        }}

	// 로그인 페이지 이동 메서드
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login() {
		System.out.println("MemberController login" + new Date());
		return "member/login";
	}

	// 로그인 처리 메서드
    @PostMapping("/loginAf.do")
    public String loginProcess(HttpServletRequest request, Model model, @RequestParam("id") String username,
            @RequestParam("password") String password) {
        // 로그인 쿼리문 접속 (Service를 통해 데이터베이스에서 아이디와 비밀번호 확인)
        boolean loginResult = service.login(username, password);
        if (loginResult) {
            // 로그인이 성공한 경우
            // 세션에 로그인 정보 저장
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            // 메인 페이지로 이동
            return "redirect:/main.do";
        } else {
            // 로그인이 실패한 경우
            // 에러 메시지를 모델에 추가하여 로그인 페이지로 이동
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "member/login";
		}
	}



	// 로그인 쿼리문 접속

	// 로그인 눌값인지 확인 => 비밀번호 일치 여부

	// 로그인이 완료되면 세션에 로그인값을 저장

	// 리턴값으로 메인.do로 보내주기

	 // 아이디 찾기 페이지로 이동
    @GetMapping("/findUsernamePage")
    public String findUsernamePage() {
        return "findUsername";
    }

    // 비밀번호 찾기 페이지로 이동
    @GetMapping("/findPasswordPage")
    public String findPasswordPage() {
        return "findPassword";
    }

    // 아이디 찾기 처리
    @PostMapping("/findUsername")
    public String findUsername(@RequestParam("email") String email, Model model) {
        // 이메일을 통해 아이디를 조회하여 모델에 추가
        String username = service.findUsernameByEmail(email);
        model.addAttribute("username", username);
        return "showUsername";
    }

    // 비밀번호 찾기 처리
    @PostMapping("/findPassword")
    public String findPassword(@RequestParam("username") String username, @RequestParam("email") String email,
            Model model) {
        // 아이디와 이메일을 통해 비밀번호를 재설정하고, 임시 비밀번호를 이메일로 전송하거나, 새로운 비밀번호를 설정할 수 있는 페이지로 이동
        String newPassword = service.findPassword(username, email);
        model.addAttribute("newPassword", newPassword);
        return "showPassword";
    }
}
