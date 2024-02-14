package ssg.middlepj.pharmafinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ssg.middlepj.pharmafinder.dto.BookmarkDto;
import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.service.BookmarkService;
import ssg.middlepj.pharmafinder.service.MemberService;
import ssg.middlepj.pharmafinder.service.MyPageService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MyPageController {

    @Autowired
    MyPageService service;

    @GetMapping("/mypage.do")
    public String mypage() {
        System.out.println("MemberController mypage " + new Date());
        return "mypages/mypage";
    }
//    @GetMapping("/mypage.do")
//    public String myPage(Model model, HttpSession session) {
//        // 세션에서 사용자 정보 가져오기
//        String username = (String) session.getAttribute("username");
//        if (username == null ) {
//            // 로그인되어 있지 않으면 로그인 페이지로 리다이렉트
//           
//        	// return "redirect:/login.do";
//        	return "redirect:/mypage.do";
//        }
//
//        // 사용자 정보 조회
//        MemberDto user = service.getUserByUsername(username);
//        if (user == null) {
//            // 사용자 정보가 없으면 로그인 페이지로 리다이렉트
//            return "redirect:/login.do";
//        }
//
//        // 사용자 정보를 모델에 추가
//        model.addAttribute("user", user);
//
//        // 마이페이지 JSP로 포워딩
//        return "mypages/mypage";
//    }
    
//    @GetMapping("/bookmark.do")
//    public String bookmarkPage() {
//    	System.out.println("MemberController bookmark " + new Date());
//        return "mypages/bookmarkpage"; // bookmarkPage.jsp로 포워딩
//    }
    
    @GetMapping("bookmark.do")
    public String getBookmarks(Model model, HttpSession session) {
        // 세션에서 사용자 정보 가져오기
        String username = (String) session.getAttribute("username");
        if (username == null) {
            // 로그인되어 있지 않으면 로그인 페이지로 리다이렉트
            return "redirect:/login.do";
        }

        // 사용자의 즐겨찾기 정보 조회
        List<BookmarkDto> bookmarks = BookmarkService.getBookmarksByUsername(username);

        // 즐겨찾기 정보를 모델에 추가
        model.addAttribute("bookmarks", bookmarks);

        // 마이페이지 즐겨찾기 JSP로 포워딩
        return "mypages/bookmarkpage";
    }
    
    @GetMapping("/userupdate.do")
    public String userupdate() {
    	System.out.println("MemberController userupdate " + new Date());
        return "mypages/userupdatepage"; // userupdate.jsp로 포워딩
    }
    
    @PostMapping("/userUpdateAf.do")
    public String updateUser(HttpServletRequest request, RedirectAttributes redirectAttributes, MemberDto member) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            // 로그인되어 있지 않은 경우, 로그인 페이지로 리다이렉트
            return "redirect:/login.do";
        }

        try {
            // 회원 정보 업데이트 처리
            MemberService.updateMember(member);
            // 성공 메시지 추가
            redirectAttributes.addFlashAttribute("message", "회원 정보가 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            // 실패 메시지 추가
            redirectAttributes.addFlashAttribute("message", "회원 정보 업데이트에 실패하였습니다.");
            e.printStackTrace();
        }

        // 마이 페이지로 리다이렉트
        return "redirect:/mypage.do";
    }
}
}
