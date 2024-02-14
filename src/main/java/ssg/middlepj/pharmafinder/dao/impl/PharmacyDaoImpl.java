package ssg.middlepj.pharmafinder.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ssg.middlepj.pharmafinder.dao.PharmacyDao;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;
import ssg.middlepj.pharmafinder.dto.PharmacyParam;
import ssg.middlepj.pharmafinder.dto.PharmacyResDto;
import ssg.middlepj.pharmafinder.dto.ProductPharmacyQtyDto;

import java.util.List;

@Repository
public class PharmacyDaoImpl implements PharmacyDao {
    private final SqlSession sqlSession;

    @Autowired
    public PharmacyDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    String ns = "Pharmacy.";

    @Override
    public int countPharmacyList(PharmacyParam paginationParam) {
        return sqlSession.selectOne(ns + "countPharmacyList", paginationParam);
    }

    @Override
    public List<PharmacyResDto> selectPharmaciesByDB(PharmacyParam paginationParam) {
        return sqlSession.selectList(ns + "selectPharmaciesByDB", paginationParam);
    }

    @Override
    public PharmacyDto selectPharmacybyDB(int storeId) {
        return sqlSession.selectOne(ns + "selectPharmacybyDB", storeId);
    }

    @Override
    public void insertPharmacy(PharmacyDto pharmacyDto) {
        sqlSession.insert(ns + "insertPharmacy", pharmacyDto);
    }

    @Override
    public List<ProductPharmacyQtyDto> selectProductByPharmacyId(Integer storeId) {
        return sqlSession.selectList(ns + "selectProductByPharmacyId", storeId);
    }

}
