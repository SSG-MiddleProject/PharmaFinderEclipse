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
    @PostMapping("/product")
    public Boolean insertProductBookmark(BookmarkDto bookmarkDto) {
        return bookmarkService.insertProductBookmark(bookmarkDto);
    }

    @ResponseBody
    @DeleteMapping("/product")
    public Boolean deleteProductBookmark(BookmarkDto bookmarkDto) {
        return bookmarkService.deleteProductBookmark(bookmarkDto);
    }
}
