package ssg.middlepj.pharmafinder.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ssg.middlepj.pharmafinder.dao.PharmacyDao;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;
import ssg.middlepj.pharmafinder.dto.PharmacyParam;

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
    public int countPharmacyList(PharmacyParam pharmacyParam) {
        return sqlSession.selectOne(ns + "countPharmacyList", pharmacyParam);
    }

    @Override
    public List<PharmacyDto> selectPharmaciesByDB(PharmacyParam pharmacyParam) {
        return sqlSession.selectList(ns + "selectPharmaciesByDB", pharmacyParam);
    }
}
