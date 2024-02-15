package ssg.middlepj.pharmafinder.dao;

import java.util.List;

import ssg.middlepj.pharmafinder.dto.PharmaProductManagementDto;
import ssg.middlepj.pharmafinder.dto.PharmaProductSearchParam;
import ssg.middlepj.pharmafinder.dto.ProductDto;

public interface PharmaProductCommonDao {
	List<ProductDto> selectProducts(PharmaProductSearchParam param);
	
	int insertPharmaProduct(PharmaProductManagementDto dto);
}
