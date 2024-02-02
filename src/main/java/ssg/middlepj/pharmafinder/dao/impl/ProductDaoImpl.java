package ssg.middlepj.pharmafinder.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ssg.middlepj.pharmafinder.dao.ProductDao;
import ssg.middlepj.pharmafinder.dto.PaginationParam;
import ssg.middlepj.pharmafinder.dto.ProductDto;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    private final SqlSession sqlSession;

    @Autowired
    public ProductDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    String ns = "Product.";

    @Override
    public ProductDto selectProductById(Integer id) {
        return sqlSession.selectOne(ns + "selectProductById", id);
    }

    @Override
    public Integer selectProductsCnt(PaginationParam paginationParam) {
        return sqlSession.selectOne(ns + "selectProductsCnt", paginationParam);
    }

    @Override
    public List<ProductDto> selectProducts(PaginationParam paginationParam) {
        return sqlSession.selectList(ns + "selectProducts", paginationParam);
    }
}
