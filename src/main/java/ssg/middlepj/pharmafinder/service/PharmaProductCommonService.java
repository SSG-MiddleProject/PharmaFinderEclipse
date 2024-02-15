package ssg.middlepj.pharmafinder.service;

import java.util.List;

import ssg.middlepj.pharmafinder.dto.PharmaProductSearchParam;
import ssg.middlepj.pharmafinder.dto.ProductDto;


public interface PharmaProductCommonService {
	List<ProductDto> selectProducts(PharmaProductSearchParam param);
}
