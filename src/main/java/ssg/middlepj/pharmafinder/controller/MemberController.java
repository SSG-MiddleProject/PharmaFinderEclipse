package ssg.middlepj.pharmafinder.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
import ssg.middlepj.pharmafinder.service.EmailService;
import ssg.middlepj.pharmafinder.service.MemberService;

@Controller // 컨트롤러 사용
public class MemberController {

	@Autowired
	MemberService service;
	
    @Autowired
    private EmailService emailService;

	// 아이디 중복 확인 메서드
	@ResponseBody
	@RequestMapping(value = "usernamecheck.do", method = RequestMethod.POST, produces = "application/String; charset=utf-8")
	public String usernamecheck(@RequestParam("username") String username) {
		// DAO를 통해 아이디 중복 체크 수행
		boolean isIdDuplicate = service.usernamecheck(username);
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

	// 비밀번호 암호화,복호화 처리 메서드 추가
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

	
	// 회원가입(일반 유저) 페이지 이동 메서드
	@GetMapping("/userRegi.do")
	public String registerUser() {
		// System.out.println("MemberController userRegi " + new Date());
		return "member/userRegi";
	}

	// 회원가입 처리 메서드
	@PostMapping("/userRegiAf.do")
	public String registerAfUser(HttpServletRequest request, @ModelAttribute MemberDto mem, RedirectAttributes redirectAttributes) throws NoSuchAlgorithmException {
	    try {
	        mem.setState(1); // 활성화 상태로 설정
	        mem.setRoll(2); // 일반 유저로 설정

	        String rawPassword = mem.getPassword();
	        String encryptedPassword = encryptStringBySHA256(rawPassword);
	        mem.setPassword(encryptedPassword);

	        // 회원가입 처리
	        service.addmember(mem);
	        redirectAttributes.addFlashAttribute("success", "등록되었습니다");
	        return "redirect:/login.do"; // 회원가입 성공 시 로그인 페이지로 리다이렉트
	    } catch (DuplicateKeyException e) {
	        // 중복 이메일이나 다른 키 관련 예외 처리
	        redirectAttributes.addFlashAttribute("error", "이미 사용 중인 이메일입니다");
	        return "redirect:/userRegi.do"; // 에러 메시지와 함께 회원가입 페이지로 리다이렉트
	    } catch (Exception e) {
	        // 기타 예외 처리
	        redirectAttributes.addFlashAttribute("error", "회원가입에 실패하였습니다. 다시 시도해주세요");
	        return "redirect:/userRegi.do";
	    }
	}

        /*
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
		*/

	// 회원가입(약국) 페이지 이동 메서드
	@GetMapping("/pharmacyRegi.do")
	public String registerPharmacy() {
		System.out.println("MemberController pharmacyRegi " + new Date());
		return "member/pharmacyRegi";
	}

	@PostMapping("/pharmacyRegiAf.do") // 메서드가 처리할 요청 경로 지정
	public String registerAfPharmacy(HttpServletRequest request, RedirectAttributes redirectAttributes,
			@ModelAttribute MemberDto mem,
			@ModelAttribute PharmacyDto pharmacy) throws NoSuchAlgorithmException {

		System.out.println("MemberController pharmacyRegiAf " + new Date());

		try {
			mem.setState(1); // 활성화 상태로 설정
			mem.setRoll(1); // 약국 유저로 설정

			String rawPassword = mem.getPassword();
		    String encryptedPassword = encryptStringBySHA256(rawPassword);
		    mem.setPassword(encryptedPassword);

		    // 회원 정보와 약국 정보를 동시에 저장
	        boolean registrationResult = service.registerPharmacy(mem, pharmacy);

//	        if (registrationResult) {
	        	redirectAttributes.addFlashAttribute("successMessage", "회원가입되었습니다.");
	            return "redirect:/login.do";
//	        } else {
//                redirectAttributes.addFlashAttribute("errorMessage", "회원가입에 실패하였습니다.");
//	            return "redirect:/pharmacyRegi.do";
//	        }
	    } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "회원가입 과정에서 오류가 발생했습니다. 다시 시도해주세요.");
	        return "redirect:/pharmacyRegi.do";
	    }
	}

		/*
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
    */

	// 로그인 페이지 이동 메서드
	@GetMapping(value = "login.do")
	public String login() {
		System.out.println("MemberController login");
		return "member/login";
	}

	// 로그인 처리 메서드
	@PostMapping("/loginAf.do")
	public String loginProcess(HttpServletRequest request, Model model) throws NoSuchAlgorithmException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String encryptedPassword = encryptStringBySHA256(password);
		System.out.println(encryptedPassword);

		// 로그인 시도 후 MemberDto 객체 반환
		MemberDto loginResult = service.login(username, encryptedPassword);
		
		if (loginResult != null) {
			HttpSession session = request.getSession();
			
			// 사용자 기본 정보 세션에 저장
			session.setAttribute("member", loginResult);			
			
	        // 메인 페이지로 이동
			return "redirect:/main.do";
		} else {
			model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
			return "member/login";
		}
	}

	// 아이디 찾기 페이지로 이동
	@GetMapping("/findUsername.do")
	public String findUsernameByEmail() {
		return "member/findUsername";
	}

	// 비밀번호 찾기 페이지로 이동
	@GetMapping("/findPassword.do")
	public String findPasswordPage() {
		return "member/findPassword";
	}

	// 아이디 찾기 처리
	@PostMapping("/findUsernameAf.do")
	public String findUsernameProcess(HttpServletRequest request, Model model) {
		// HttpServletRequest를 사용하여 이메일 파라미터를 직접 추출
	    String email = request.getParameter("email");
	    if (email == null || email.trim().isEmpty()) {
	        model.addAttribute("error", "이메일 주소를 입력해주세요.");
	        return "member/findUsername"; // 입력이 잘못된 경우, 아이디 찾기 페이지로 다시 이동
	    }
	    
	    // 이메일을 통해 아이디를 조회하여 모델에 추가
	    String username = service.findUsernameByEmail(email.trim());
		if (username != null) {
	        model.addAttribute("username", username);
	        return "member/showUsername"; // 아이디 보여주는 페이지로 이동
	    } else {
	        model.addAttribute("error", "해당 이메일로 등록된 아이디가 없습니다.");
	        return "member/findUsername"; // 아이디 찾기 페이지로 이동
	    }
	}

	// 비밀번호 찾기 처리
	@PostMapping("/findPasswordAf.do")
	public String findPasswordProcess(HttpServletRequest request, Model model) throws NoSuchAlgorithmException {
		// HttpServletRequest를 사용하여 아이디(username)와 이메일(email) 파라미터를 직접 추출
	    String username = request.getParameter("username");
	    String email = request.getParameter("email");
	    
	    // 입력값 검증
	    if (username == null || username.trim().isEmpty() || email == null || email.trim().isEmpty()) {
	        model.addAttribute("error", "아이디와 이메일 주소를 모두 입력해주세요.");
	        return "member/findPassword"; // 입력이 잘못된 경우, 비밀번호 찾기 페이지로 다시 이동
	    }
	    
	    // 아이디와 이메일을 통해 비밀번호 재설정 및 임시 비밀번호 이메일 전송 시도
	    String temporaryPassword = service.findPassword(username.trim(), email.trim());
	    if (temporaryPassword != null) {
	        // 임시 비밀번호 발급 및 이메일 전송 성공
	    	emailService.sendSimpleMessage(email, "임시 비밀번호 발급", "귀하의 임시 비밀번호는 " + temporaryPassword + " 입니다");
            model.addAttribute("message", "임시 비밀번호가 이메일로 전송되었습니다");
	        return "member/updateOriginPassword";
	    } else {
	        // 아이디 또는 이메일 주소가 일치하지 않음
	        model.addAttribute("error", "제공된 정보와 일치하는 계정이 없습니다.");
	        return "member/findPassword";
	    }
	}
	
	@PostMapping("/updatePasswordProcess.do")
	public String updatePasswordProcess(HttpServletRequest request, Model model) throws NoSuchAlgorithmException {
	    String username = request.getParameter("username");
	    String temporaryPassword = request.getParameter("temporaryPassword");
	    String newPassword = request.getParameter("newPassword");
	    String confirmPassword = request.getParameter("confirmPassword");

	    // 입력값 검증 (생략)
	    // 새로운 비밀번호와 비밀번호 확인이 일치하는지 검사
	    if (!newPassword.equals(confirmPassword)) {
	        model.addAttribute("error", "새로운 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
	        return "member/updateOriginPassword";
	    }

	    // 임시 비밀번호와 새로운 비밀번호를 처리하는 서비스 메서드 호출
        boolean updateResult = service.updatePasswordWithTemporary(username, temporaryPassword, newPassword);
        
	    if (updateResult) {
	        // 비밀번호 변경 성공
	        model.addAttribute("message", "비밀번호가 성공적으로 변경되었습니다.");
	        return "member/login";
	    } else {
	        // 비밀번호 변경 실패
	        model.addAttribute("error", "비밀번호 변경에 실패하였습니다.");
	        return "member/updateOriginPassword";
	    }
	}

	//로그아웃 처리 메서드
	@GetMapping("/logout.do")
	public String logout(HttpServletRequest request) {
	 // 현재 세션을 가져와서 세션을 무효화
	 HttpSession session = request.getSession(false); // 현재 세션을 가져오되, 없으면 새로 생성하지 않음
	 if (session != null) {
	     session.invalidate(); // 세션 무효화
	 }
	 return "redirect:/main.do"; // 메인 페이지로 리다이렉트
	}

    @GetMapping("/userupdate.do")
    public String userupdate(HttpSession session, HttpServletResponse response) throws IOException {
        // 세션에서 사용자 정보 확인
        MemberDto loginedMember = (MemberDto) session.getAttribute("member");

        if (loginedMember == null) {
            // 로그인된 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/login.do";

        }
        System.out.println("MemberController userupdate " + new Date());

        //

        // 로그인한 사용자라면 회원정보수정 페이지로 이동
        return "mypages/userupdatepage";
    }

    @PostMapping("/userUpdateAf.do")
    public String updateUser(@ModelAttribute MemberDto member, HttpSession session, RedirectAttributes redirectAttributes) throws NoSuchAlgorithmException {
        MemberDto loginedMember = (MemberDto) session.getAttribute("member");
        if (!service.emailcheck(member.getEmail()) || member.getEmail().equals(loginedMember.getEmail())) {
			member.setUsername(loginedMember.getUsername());
            // 이메일 중복 체크: 새 이메일이 기존 이메일과 같거나 중복되지 않는 경우 업데이트 진행
            if (member.getPassword() != null && !member.getPassword().isEmpty()) {

                // 비밀번호 암호화
                String encryptedPassword = encryptStringBySHA256(member.getPassword());
                member.setPassword(encryptedPassword);
                System.out.println(encryptedPassword);
                // 회원 정보 업데이트 처리
                boolean updateSuccess = service.updateMember(member);
                if (updateSuccess) {
                    // 업데이트 성공 메시지
                    redirectAttributes.addFlashAttribute("message", "회원 정보가 성공적으로 업데이트되었습니다.");
                    
                    // 세션에 저장된 사용자 정보 업데이트
                    loginedMember.setPassword(encryptedPassword); // 새로운 비밀번호로 업데이트
                    session.setAttribute("member", loginedMember); // 세션 업데이트
                } else {
                    // 업데이트 실패 에러 메시지
                    redirectAttributes.addFlashAttribute("error", "회원 정보 업데이트에 실패하였습니다.");
                }
            }
        } else {
            // 이메일 중복 에러 메시지
            redirectAttributes.addFlashAttribute("error", "이미 사용 중인 이메일 주소입니다.");
        }
        return "redirect:/mypage.do";
    }
}
