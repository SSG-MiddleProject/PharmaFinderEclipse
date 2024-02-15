package ssg.middlepj.pharmafinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssg.middlepj.pharmafinder.dto.BookmarkDto;
import ssg.middlepj.pharmafinder.dto.BookmarkResDto;
import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.service.BookmarkService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/bookmark")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @Autowired
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @ResponseBody
    @PostMapping("/product.do")
    public Boolean insertProductBookmark(HttpServletRequest request, Integer targetId) {
        MemberDto member = (MemberDto) request.getSession().getAttribute("member");

        if (member == null || targetId == null) return false;
        return bookmarkService.insertProductBookmark(new BookmarkDto(member.getId(), targetId));
    }

    @ResponseBody
    @DeleteMapping("/product.do")
    public Boolean deleteProductBookmark(HttpServletRequest request, Integer targetId) {
        MemberDto member = (MemberDto) request.getSession().getAttribute("member");

        if (member == null || targetId == null) return false;
        return bookmarkService.deleteProductBookmark(new BookmarkDto(member.getId(), targetId));
    }

    @ResponseBody
    @PostMapping("/pharmacy.do")
    public Boolean insertPharmacyBookmark(HttpServletRequest request, Integer targetId) {
        MemberDto member = (MemberDto) request.getSession().getAttribute("member");

        if (member == null || targetId == null) return false;
        return bookmarkService.insertPharmacyBookmark(new BookmarkDto(member.getId(), targetId));
    }

    @ResponseBody
    @DeleteMapping("/pharmacy.do")
    public Boolean deletePharmacyBookmark(HttpServletRequest request, Integer targetId) {
        MemberDto member = (MemberDto) request.getSession().getAttribute("member");

        if (member == null || targetId == null) return false;
        return bookmarkService.deletePharmacyBookmark(new BookmarkDto(member.getId(), targetId));
    }
    
    @GetMapping("/list.do")
    public String showStoreBookmarkList(HttpSession session, Model model) {
        System.out.println("showBookmarkList : " + new Date()); // 맵핑접속 확인용
        MemberDto member = (MemberDto) session.getAttribute("member");
        if(member==null) return "error";
        else {
            List<BookmarkResDto> bookmarks = bookmarkService.getStoreBookmarksByUserId(member.getId());
            model.addAttribute("bookmarks", bookmarks);
            return "mypages/bookmarkList.tiles"; // 북마크 리스트를 보여줄 JSP 파일명
        }

    }
    @GetMapping("/bookmark.do")
    public String getBookmarks(HttpSession session) {
        // 세션에서 사용자 정보 가져오기
        MemberDto member = (MemberDto) session.getAttribute("member");
        if (member == null) {
            // 사용자 정보가 없으면 로그인 페이지로 리다이렉트
            return "redirect:/login.do";
        }

        // 마이페이지 즐겨찾기 JSP로 포워딩
        return "mypages/bookmarkpage.tiles";
    }
    
}
