package ssg.middlepj.pharmafinder.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssg.middlepj.pharmafinder.dao.PharmaProductCommonDao;
import ssg.middlepj.pharmafinder.dto.PharmaProductManagementDto;
import ssg.middlepj.pharmafinder.dto.PharmaProductSearchParam;
import ssg.middlepj.pharmafinder.dto.ProductDto;

@Repository
public class PharmaProductCommonDaoImpl implements PharmaProductCommonDao{
	
	@Autowired
	SqlSession session;
	
	String ns = "PharmaProductCommon.";
	
	public List<ProductDto> selectProducts(PharmaProductSearchParam param){
		return session.selectList(ns + "select-products", param);
	}

	@Override
	public int insertPharmaProduct(PharmaProductManagementDto dto) {
		return session.insert(ns + "insert-Product", dto);
	}
}
