
package ssg.middlepj.pharmafinder.service;

import java.util.List;

import ssg.middlepj.pharmafinder.dto.PharmaProductManagementDto;
import ssg.middlepj.pharmafinder.dto.PharmaProductManagementParam;
import ssg.middlepj.pharmafinder.dto.PharmaProductSearchParam;
import ssg.middlepj.pharmafinder.dto.PharmaProductWithProductDto;

public interface PharmaProductManagementService {

	boolean isDuplicationPharmaProduct(PharmaProductManagementParam param);

	List<PharmaProductWithProductDto> selectPharmaProducts(PharmaProductManagementParam param);

	PharmaProductWithProductDto selectPharmaProduct(int id);

	boolean registerPharmaProduct(PharmaProductManagementDto dto);

	boolean updatePharmaProduct(PharmaProductManagementDto dto);

	int deletePharmaProduct(PharmaProductManagementDto dto);

}
