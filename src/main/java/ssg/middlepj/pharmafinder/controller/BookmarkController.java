package ssg.middlepj.pharmafinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssg.middlepj.pharmafinder.dto.BookmarkDto;
import ssg.middlepj.pharmafinder.service.BookmarkService;

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
    public Boolean insertProductBookmark(Integer targetId) {
        // TODO: Get userId from session
        Integer userId = 1;
        if (userId == null || targetId == null) return false;
        return bookmarkService.insertProductBookmark(new BookmarkDto(userId, targetId));
    }

    @ResponseBody
    @DeleteMapping("/product.do")
    public Boolean deleteProductBookmark(Integer targetId) {
        // TODO: Get userId from session
        Integer userId = 1;
        if (userId == null || targetId == null) return false;
        return bookmarkService.deleteProductBookmark(new BookmarkDto(userId, targetId));
    }
}
