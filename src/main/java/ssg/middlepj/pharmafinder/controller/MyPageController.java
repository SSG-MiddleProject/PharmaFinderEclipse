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

    @Autowired
    public MyPageController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
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
