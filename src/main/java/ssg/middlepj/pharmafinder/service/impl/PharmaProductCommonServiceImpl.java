package ssg.middlepj.pharmafinder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssg.middlepj.pharmafinder.dao.PharmaProductCommonDao;
import ssg.middlepj.pharmafinder.dto.PharmaProductSearchParam;
import ssg.middlepj.pharmafinder.dto.ProductDto;
import ssg.middlepj.pharmafinder.service.PharmaProductCommonService;

@Service
public class PharmaProductCommonServiceImpl implements PharmaProductCommonService {

	@Autowired
	PharmaProductCommonDao dao;
	
	@Override
	public List<ProductDto> selectProducts(PharmaProductSearchParam param) {
		return dao.selectProducts(param);
	}

}
