package ssg.middlepj.pharmafinder.service;

import java.util.List;

import ssg.middlepj.pharmafinder.dto.PharmaProductManagementDto;
import ssg.middlepj.pharmafinder.dto.PharmaProductManagementParam;
import ssg.middlepj.pharmafinder.dto.PharmaProductSearchParam;
import ssg.middlepj.pharmafinder.dto.PharmaProductWithProductDto;

public interface PharmaProductManagementService {
	
	boolean insertPharmaProduct(PharmaProductManagementDto dto);
	
	boolean isDuplicationPharmaProduct(PharmaProductManagementParam param);
	
	List<PharmaProductWithProductDto> searchPharmaProducts(PharmaProductManagementParam param);

	int deletePharmaProduct(PharmaProductManagementDto dto);
}
