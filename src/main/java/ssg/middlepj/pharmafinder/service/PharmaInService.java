package ssg.middlepj.pharmafinder.service;

import java.util.List;

import ssg.middlepj.pharmafinder.dto.PharmaDailyInOutSearchByPeriodParam;
import ssg.middlepj.pharmafinder.dto.PharmaInDetailDto;
import ssg.middlepj.pharmafinder.dto.PharmaInDto;

public interface PharmaInService {
	List<PharmaInDto> selectDailyInputs(PharmaDailyInOutSearchByPeriodParam param);

	List<PharmaInDetailDto> selectDailyInputProducts(int id);

	PharmaInDto selectDailyInputWithProducts(int id);

	boolean insertDailyInput(PharmaInDto dto);

	boolean deleteDailyInput(int id);
}
