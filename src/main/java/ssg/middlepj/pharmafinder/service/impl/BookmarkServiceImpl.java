package ssg.middlepj.pharmafinder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssg.middlepj.pharmafinder.dao.impl.BookmarkDaoImpl;
import ssg.middlepj.pharmafinder.dto.BookmarkDto;
import ssg.middlepj.pharmafinder.service.BookmarkService;

@Service
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkDaoImpl bookmarkDao;

    @Autowired
    public BookmarkServiceImpl(BookmarkDaoImpl bookmarkDao) {
        this.bookmarkDao = bookmarkDao;
    }

    @Override
    public Boolean insertProductBookmark(BookmarkDto bookmarkDto) {
        return bookmarkDao.insertProductBookmark(bookmarkDto);
    }

    @Override
    public Boolean deleteProductBookmark(BookmarkDto bookmarkDto) {
        return bookmarkDao.deleteProductBookmark(bookmarkDto);
    }
}
