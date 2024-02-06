package ssg.middlepj.pharmafinder.dao.impl;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssg.middlepj.pharmafinder.dao.PharmaProductManagementDao;
import ssg.middlepj.pharmafinder.dto.PharmaProductManagementDto;
import ssg.middlepj.pharmafinder.dto.PharmaProductManagementParam;
import ssg.middlepj.pharmafinder.dto.PharmaProductSearchParam;
import ssg.middlepj.pharmafinder.dto.PharmaProductWithProductDto;

@Repository
public class PharmaProductManagementDaoImpl implements PharmaProductManagementDao {

	@Autowired
	private SqlSession session;
	private String ns = "PharmaProductManagement.";
	
	@Override
	public int insertPharmaProduct(PharmaProductManagementDto dto) {
		
		return session.insert(ns +"insert-product", dto);
	}

	@Override
	public int selectDuplicationProductsCount(PharmaProductManagementParam param) {
		
		return session.selectOne(ns + "select-duplication-products-count", param);
	}

	@Override
	public List<PharmaProductWithProductDto> selectPharmaProducts(PharmaProductManagementParam param) {
		List<PharmaProductWithProductDto> list = session.selectList(ns + "select-pharma-products", param);
		return list;
	}

	@Override
	public int deletePharmaProduct(PharmaProductManagementDto dto) {
		return session.delete(ns+"delete-pharma-product",dto);
	}

}
