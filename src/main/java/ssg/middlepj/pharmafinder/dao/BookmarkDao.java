package ssg.middlepj.pharmafinder.dao;

import ssg.middlepj.pharmafinder.dto.BookmarkDto;

public interface BookmarkDao {
    Boolean insertProductBookmark(BookmarkDto bookmarkDto);

    Boolean deleteProductBookmark(BookmarkDto bookmarkDto);
}
