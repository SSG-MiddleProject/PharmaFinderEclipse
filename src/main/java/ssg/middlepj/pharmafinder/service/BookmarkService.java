package ssg.middlepj.pharmafinder.service;

import ssg.middlepj.pharmafinder.dto.BookmarkDto;

public interface BookmarkService {
    Boolean insertProductBookmark(BookmarkDto bookmarkDto);

    Boolean deleteProductBookmark(BookmarkDto bookmarkDto);

    Boolean insertPharmacyBookmark(BookmarkDto bookmarkDto);

    Boolean deletePharmacyBookmark(BookmarkDto bookmarkDto);
}
