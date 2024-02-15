package ssg.middlepj.pharmafinder.service;

import java.util.List;

import ssg.middlepj.pharmafinder.dto.PharmaDailyInOutSearchByPeriodParam;
import ssg.middlepj.pharmafinder.dto.PharmaOutDetailDto;
import ssg.middlepj.pharmafinder.dto.PharmaOutDto;


public interface PharmaOutService {

	List<PharmaOutDto> selectDailyOutputs(PharmaDailyInOutSearchByPeriodParam param);

	List<PharmaOutDetailDto> selectDailyOutputProducts(int id);

	PharmaOutDto selectDailyOutputWithProducts(int id);

	boolean insertDailyOutput(PharmaOutDto dto);

	boolean deleteDailyOutput(int id);

}
