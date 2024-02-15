package ssg.middlepj.pharmafinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ssg.middlepj.pharmafinder.dto.BookmarkResDto;
import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.service.BookmarkService;
import ssg.middlepj.pharmafinder.service.MemberService;
import ssg.middlepj.pharmafinder.service.MyPageService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class MyPageController {

    private final BookmarkService bookmarkService;
    private final MyPageService myPageService; // 추가된 MyPageService

    @Autowired
    public MyPageController(BookmarkService bookmarkService, MyPageService myPageService) {
        this.bookmarkService = bookmarkService;
        this.myPageService = myPageService; // 초기화
    }
    @GetMapping("/mypage.do")
    public String myPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        if (memberDto == null) {
            return "redirect:/login.do"; // 로그인 페이지로 리다이렉션
        } else {
            model.addAttribute("memberDto", memberDto);
            List<BookmarkResDto> bookmarks = bookmarkService.getStoreBookmarksByUserId(memberDto.getId());
            model.addAttribute("bookmarks", bookmarks);
            // 여기서 추가적인 정보를 model에 추가할 수 있습니다.
            return "mypages/mypage.tiles"; // mypage.jsp로 이동 (mypage.jsp는 마이 페이지를 위한 실제 뷰 파일명으로 교체해야 함)
        }
        }
    
    
    @GetMapping("/setting.do")
    public String mypage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        MemberDto memberDto = (MemberDto) session.getAttribute("member");
        if (memberDto == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("memberDto", memberDto);
            List<BookmarkResDto> bookmarks = bookmarkService.getStoreBookmarksByUserId(memberDto.getId());
            model.addAttribute("bookmarks", bookmarks);
            System.out.println("MemberController mypage " + new Date());
            return "mypages/userupdatepage.tiles";
        }
    }
}
