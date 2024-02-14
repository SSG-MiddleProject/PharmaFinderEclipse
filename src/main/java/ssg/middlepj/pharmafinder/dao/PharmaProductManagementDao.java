package ssg.middlepj.pharmafinder.dao;

import java.util.List;

import ssg.middlepj.pharmafinder.dto.PharmaProductManagementDto;
import ssg.middlepj.pharmafinder.dto.PharmaProductManagementParam;
import ssg.middlepj.pharmafinder.dto.PharmaProductWithProductDto;
import ssg.middlepj.pharmafinder.dto.PharmaStoreStockDto;

public interface PharmaProductManagementDao {

	int selectDuplicationProductsCount(PharmaProductManagementParam param);
	PharmaProductWithProductDto selectPharmaProduct(int id);
	List<PharmaProductWithProductDto> selectPharmaProducts(PharmaProductManagementParam param);
	int insertStoreStock(PharmaProductManagementDto dto);
	int insertPharmaProduct(PharmaProductManagementDto dto);
	int updatePharmaProduct(PharmaProductManagementDto dto);
	int deletePharmaProduct(PharmaProductManagementDto dto);
	int updateStoreStockOutputPrice(PharmaProductManagementDto dto);

}
