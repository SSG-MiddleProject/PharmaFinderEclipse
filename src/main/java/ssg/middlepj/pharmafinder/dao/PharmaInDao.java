package ssg.middlepj.pharmafinder.dao;

import java.util.List;

import ssg.middlepj.pharmafinder.dto.PharmaDailyInOutSearchByPeriodParam;
import ssg.middlepj.pharmafinder.dto.PharmaInDetailDto;
import ssg.middlepj.pharmafinder.dto.PharmaInDto;
import ssg.middlepj.pharmafinder.dto.PharmaInProductDto;

public interface PharmaInDao {

	List<PharmaInDto> selectDailyInputs(PharmaDailyInOutSearchByPeriodParam param);

	List<PharmaInDetailDto> selectDailyInputProducts(int id);

	PharmaInDto selectDailyInputWithProducts(int id);

	int selectDailyInputLastId();

	int insertDailyInput(PharmaInDto dto);

	int insertDailyInputProduct(PharmaInProductDto dto);

	int updateStoreStockInput(PharmaInProductDto dto);

	int deleteDailyInput(int id);

}
