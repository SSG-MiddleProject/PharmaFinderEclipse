package ssg.middlepj.pharmafinder.dao;

import java.util.List;

import ssg.middlepj.pharmafinder.dto.PharmaProductManagementDto;
import ssg.middlepj.pharmafinder.dto.PharmaProductManagementParam;
import ssg.middlepj.pharmafinder.dto.PharmaProductSearchParam;
import ssg.middlepj.pharmafinder.dto.PharmaProductWithProductDto;

public interface PharmaProductManagementDao {

	int insertPharmaProduct(PharmaProductManagementDto dto);
	int selectDuplicationProductsCount(PharmaProductManagementParam param);
	List<PharmaProductWithProductDto> selectPharmaProducts(PharmaProductManagementParam param);
	int deletePharmaProduct(PharmaProductManagementDto dto);
}
