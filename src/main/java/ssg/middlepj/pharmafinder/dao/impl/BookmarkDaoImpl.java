package ssg.middlepj.pharmafinder.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ssg.middlepj.pharmafinder.dao.BookmarkDao;
import ssg.middlepj.pharmafinder.dto.BookmarkDto;

@Repository
public class BookmarkDaoImpl implements BookmarkDao {
    private final SqlSession sqlSession;

    @Autowired
    public BookmarkDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    String ns = "Bookmark.";

    @Override
    public Boolean insertProductBookmark(BookmarkDto bookmarkDto) {
        return sqlSession.insert(ns + "insertProductBookmark", bookmarkDto) > 0;
    }

    @Override
    public Boolean deleteProductBookmark(BookmarkDto bookmarkDto) {
        return sqlSession.delete(ns + "deleteProductBookmark", bookmarkDto) > 0;
    }
}
