package ssg.middlepj.pharmafinder.service.impl;

import java.util.List;

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

    @Override
    public Boolean insertPharmacyBookmark(BookmarkDto bookmarkDto) {
        return bookmarkDao.insertPharmacyBookmark(bookmarkDto);
    }

    @Override
    public Boolean deletePharmacyBookmark(BookmarkDto bookmarkDto) {
        return bookmarkDao.deletePharmacyBookmark(bookmarkDto);
    }

	@Override
	public List<BookmarkDto> getBookmarksByUserId(Integer userId) {
		// DAO를 통해 북마크 목록 조회
		return bookmarkDao.getBookmarksByUserId(userId);
	}
}
