package ssg.middlepj.pharmafinder.dao;

import java.util.List;

import ssg.middlepj.pharmafinder.dto.BookmarkDto;

public interface BookmarkDao {
    Boolean insertProductBookmark(BookmarkDto bookmarkDto);

    Boolean deleteProductBookmark(BookmarkDto bookmarkDto);

    Boolean insertPharmacyBookmark(BookmarkDto bookmarkDto);

    Boolean deletePharmacyBookmark(BookmarkDto bookmarkDto);
    
    //북마크 목록 조회 메서드 추가
    List<BookmarkDto> getBookmarksByUserId(Integer userId);
}
