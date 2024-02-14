package ssg.middlepj.pharmafinder.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;
import ssg.middlepj.pharmafinder.service.MemberService;

@Controller // 컨트롤러 사용
public class MemberController {

	@Autowired
	MemberService service;

	// 아이디 중복 확인 메서드
	@ResponseBody
	@RequestMapping(value = "idcheck.do", method = RequestMethod.POST, produces = "application/String; charset=utf-8")
	public String idcheck(@RequestParam("username") String username) {
		// System.out.println("MemberController idcheck " + new Date());
		// DAO를 통해 아이디 중복 체크 수행
		boolean isIdDuplicate = service.idcheck(username);
		// 결과에 따라 YES 또는 NO를 반환
		return isIdDuplicate ? "NO" : "YES";
	}
	
	// 이메일 중복 확인 메서드
	@ResponseBody
	@RequestMapping(value = "emailcheck.do", method = RequestMethod.POST, produces = "application/String; charset=utf-8")
	public String emailcheck(@RequestParam("email") String email) {
		// System.out.println("MemberController emailcheck " + new Date());
		// DAO를 통해 아이디 중복 체크 수행
		boolean isEmailDuplicate = service.emailcheck(email);
		// 결과에 따라 YES 또는 NO를 반환
		return isEmailDuplicate ? "NO" : "YES";
	}

	// 로그인페이지에서 회원가입 누르면 이동되는 회원가입선택하는페이지로 이동 메서드
	@GetMapping("/regiSelect.do")
	public String regiSelect() {
		// System.out.println("MemberController regiSelect " + new Date());
		return "member/regiSelect";
	}

	// 회원가입(일반 유저) 페이지 이동 메서드
	@GetMapping("/userRegi.do")
	public String registerUser() {
		System.out.println("MemberController userregi ");
		return "member/userRegi";
	}

	// 회원가입 처리 메서드
	@PostMapping("/userRegiAf.do")
	public String registerAfUser(HttpServletRequest request, @ModelAttribute MemberDto mem) {
		mem.setState(1); // 활성화 상태로 설정
		mem.setRoll(2); // 일반 유저로 설정

		// 회원가입 처리
		boolean registerResult = service.addmember(mem);
		if (registerResult) {
			// 회원가입이 성공한 경우
			return "redirect:/login.do";
		} else {
			// 회원가입이 실패한 경우
			request.setAttribute("error", "회원가입에 실패하였습니다. 다시 시도해주세요.");
			return "member/userRegi"; // 회원가입 페이지로 이동
		}
	}

	// 회원가입(약국) 페이지 이동 메서드
	@GetMapping("/pharmacyRegi.do")
	public String registerPharmacy() {
		// System.out.println("MemberController pharmacyRegi " + new Date());
		return "member/pharmacyRegi";
	}

	@PostMapping("/pharmacyRegiAf.do") // 메서드가 처리할 요청 경로 지정
	public String registerAfPharmacy(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@ModelAttribute MemberDto mem,
			@ModelAttribute PharmacyDto pharmacy) throws NoSuchAlgorithmException {
		
		String rawPassword = mem.getPassword();
		String encryptedPassword = encryptStringBySHA256(rawPassword);
		
		mem.setPassword(encryptedPassword);

		mem.setState(1); // 활성화 상태로 설정
		mem.setRoll(1); // 약국 유저로 설정

		boolean memberAdded = service.addmember(mem);
		if (!memberAdded) {
			redirectAttributes.addFlashAttribute("error", "회원 정보 저장에 실패하였습니다.");
            return "redirect:/member/pharmacyRegi";
        }
		
		// 생성된 사용자 ID를 PharmacyDto에 설정
        pharmacy.setId(mem.getId());
        
        // 약국정보저장
		boolean pharmacyAdded = service.addpharmacy(pharmacy);
		if (!pharmacyAdded) {
            redirectAttributes.addFlashAttribute("error", "약국 정보 저장에 실패하였습니다.");
            return "redirect:/member/pharmacyRegi";
        }

        return "redirect:/login.do";
    }

	// 로그인 페이지 이동 메서드
	@GetMapping(value = "login.do")
	public String login() {
		 System.out.println("MemberController login");
		return "member/login";
	}

	// 로그인 처리 메서드
	@PostMapping("/loginAf.do")
	public String loginProcess(HttpServletRequest request, Model model) throws NoSuchAlgorithmException {
		System.out.println("Controller login");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//비밀번호 암호화
		String encryptedPassword = encryptStringBySHA256(password);
		
		System.out.println("input-password: " + password);
		System.out.println("encryted-password: " + encryptedPassword);

		// 로그인 시도 후 MemberDto 객체 반환
		MemberDto loginResult = service.login(username, encryptedPassword);
		
		if (loginResult != null) {
			HttpSession session = request.getSession();
			
			// 사용자 기본 정보 세션에 저장
	        session.setAttribute("id", loginResult.getId());
	        session.setAttribute("username", loginResult.getUsername());
	        session.setAttribute("roll", loginResult.getRoll());

	        // 약국 유저의 경우 storeId도 세션에 저장
	        if (loginResult.getRoll() == 1) {
	            session.setAttribute("storeId", loginResult.getStoreId());
	        }
	        
	        System.out.println("test" + session.getAttribute("id"));
	        
	        // 메인 페이지로 이동
			return "redirect:/main.do";
		} else {
			model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
			return "member/login";
		}
	}


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
	
	 // 입력 문자열을 SHA-256으로 암호화하여 해시 값을 반환하는 메서드
    private String encryptStringBySHA256(String text) throws NoSuchAlgorithmException {
       // SHA-256 알고리즘의 인스턴스 생성
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // 입력 문자열의 바이트 배열을 업데이트하여 해시 값을 계산
        md.update(text.getBytes());
      // 계산된 해시 값을 16진수 문자열로 변환하여 반환
        return bytesToHex(md.digest());
    }

    // 바이트 배열을 16진수 문자열로 변환하는 메서드
    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
           // 바이트 값을 16진수 문자열로 변환하여 StringBuilder에 추가
            builder.append(String.format("%02x", b));
        }
        // StringBuilder의 내용을 문자열로 반환
        return builder.toString();
    }

	
}






/*
private void convertPharmacyOperatingTimes(PharmacyDto pharmacy) {
	pharmacy.setDutyTime1s(convertTimeFormat(pharmacy.getDutyTime1s()));
	pharmacy.setDutyTime1c(convertTimeFormat(pharmacy.getDutyTime1c()));
	pharmacy.setDutyTime2s(convertTimeFormat(pharmacy.getDutyTime2s()));
	pharmacy.setDutyTime2c(convertTimeFormat(pharmacy.getDutyTime2c()));
	pharmacy.setDutyTime3s(convertTimeFormat(pharmacy.getDutyTime3s()));
	pharmacy.setDutyTime3c(convertTimeFormat(pharmacy.getDutyTime3c()));
	pharmacy.setDutyTime4s(convertTimeFormat(pharmacy.getDutyTime4s()));
	pharmacy.setDutyTime4c(convertTimeFormat(pharmacy.getDutyTime4c()));
	pharmacy.setDutyTime5s(convertTimeFormat(pharmacy.getDutyTime5s()));
	pharmacy.setDutyTime5c(convertTimeFormat(pharmacy.getDutyTime5c()));
	pharmacy.setDutyTime6s(convertTimeFormat(pharmacy.getDutyTime6s()));
	pharmacy.setDutyTime6c(convertTimeFormat(pharmacy.getDutyTime6c()));
	pharmacy.setDutyTime7s(convertTimeFormat(pharmacy.getDutyTime7s()));
	pharmacy.setDutyTime7c(convertTimeFormat(pharmacy.getDutyTime7c()));
	pharmacy.setDutyTime8s(convertTimeFormat(pharmacy.getDutyTime8s()));
	pharmacy.setDutyTime8c(convertTimeFormat(pharmacy.getDutyTime8c()));
}

private String convertTimeFormat(String timeStr) {
	if (timeStr == null || timeStr.isEmpty()) {
		return "0000"; // 기본값 혹은 예외 처리에 따라 변경 가능
	}
	String[] parts = timeStr.split(":");
	if (parts.length < 2) {
		return "0000"; // 기본값 혹은 예외 처리에 따라 변경 가능
	}
	// 시간과 분을 각각 두 자리 숫자로 포맷
	return parts[0] + parts[1];
}
*/
