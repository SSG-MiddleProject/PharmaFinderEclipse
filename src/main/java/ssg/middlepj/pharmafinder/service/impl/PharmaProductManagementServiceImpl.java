package ssg.middlepj.pharmafinder.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssg.middlepj.pharmafinder.dao.PharmaProductManagementDao;
import ssg.middlepj.pharmafinder.dto.PharmaProductManagementDto;
import ssg.middlepj.pharmafinder.dto.PharmaProductManagementParam;
import ssg.middlepj.pharmafinder.dto.PharmaProductSearchParam;
import ssg.middlepj.pharmafinder.dto.PharmaProductWithProductDto;
import ssg.middlepj.pharmafinder.service.PharmaProductManagementService;

@Service
public class PharmaProductManagementServiceImpl implements PharmaProductManagementService{

	@Autowired
	PharmaProductManagementDao dao;

	@Override
	public boolean insertPharmaProduct(PharmaProductManagementDto dto) {
		return dao.insertPharmaProduct(dto) > 0;
	}

	@Override
	public boolean isDuplicationPharmaProduct(PharmaProductManagementParam param) {
		int resultCount = dao.selectDuplicationProductsCount(param);
		return resultCount > 0;
	}

	@Override
	public List<PharmaProductWithProductDto> searchPharmaProducts(PharmaProductManagementParam param) {
		return dao.selectPharmaProducts(param);
	}

	@Override
	public int deletePharmaProduct(PharmaProductManagementDto dto) {
		return dao.deletePharmaProduct(dto);
	}
}
