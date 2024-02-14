package ssg.middlepj.pharmafinder.dao;

import java.util.List;

import ssg.middlepj.pharmafinder.dto.PharmaDailyInOutSearchByPeriodParam;
import ssg.middlepj.pharmafinder.dto.PharmaOutDetailDto;
import ssg.middlepj.pharmafinder.dto.PharmaOutDto;
import ssg.middlepj.pharmafinder.dto.PharmaOutProductDto;

public interface PharmaOutDao {

	List<PharmaOutDto> selectDailyOutputs(PharmaDailyInOutSearchByPeriodParam param);

	List<PharmaOutDetailDto> selectDailyOutputProducts(int id);

	PharmaOutDto selectDailyOutputWithProducts(int id);

	int selectDailyOutputLastId();

	int insertDailyOutput(PharmaOutDto dto);

	int insertDailyOutputProduct(PharmaOutProductDto dto);

	int updateStoreStockOutput(PharmaOutProductDto dto);

	int deleteDailyOutput(int id);
}
