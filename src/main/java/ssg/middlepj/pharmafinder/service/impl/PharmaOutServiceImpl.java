package ssg.middlepj.pharmafinder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssg.middlepj.pharmafinder.dao.PharmaOutDao;
import ssg.middlepj.pharmafinder.dto.PharmaDailyInOutSearchByPeriodParam;
import ssg.middlepj.pharmafinder.dto.PharmaOutDetailDto;
import ssg.middlepj.pharmafinder.dto.PharmaOutDto;
import ssg.middlepj.pharmafinder.dto.PharmaOutProductDto;
import ssg.middlepj.pharmafinder.service.PharmaOutService;

@Service
public class PharmaOutServiceImpl implements PharmaOutService {

	@Autowired
	private PharmaOutDao dao;

	@Override
	public boolean insertDailyOutput(PharmaOutDto dto) {
		boolean isSuccess = true;

		int inputInserted = dao.insertDailyOutput(dto);

		if(inputInserted < 1) {
			isSuccess = false;
		}

		int lastId = dao.selectDailyOutputLastId();

		for (PharmaOutProductDto product : dto.getProducts()) {
			product.setDailyOutputId(lastId);

			int inputProductInseted = dao.insertDailyOutputProduct(product);
			dao.updateStoreStockOutput(product);

			if(inputProductInseted < 1 && isSuccess) {
				isSuccess = false;
			}
		}

		return isSuccess;
	}

	@Override
	public PharmaOutDto selectDailyOutputWithProducts(int id) {
		return dao.selectDailyOutputWithProducts(id);
	}

	@Override
	public List<PharmaOutDto> selectDailyOutputs(PharmaDailyInOutSearchByPeriodParam param) {

		return dao.selectDailyOutputs(param);
	}

	@Override
	public List<PharmaOutDetailDto> selectDailyOutputProducts(int id) {
		return dao.selectDailyOutputProducts(id);
	}

	@Override
	public boolean deleteDailyOutput(int id) {

		List<PharmaOutDetailDto> dtoes = dao.selectDailyOutputProducts(id);

		for (PharmaOutDetailDto pharmaOutDetailDto : dtoes) {

			PharmaOutProductDto paramDto = new PharmaOutProductDto();
			paramDto.setDailyOutputId(pharmaOutDetailDto.getDailyOutputId());
			paramDto.setStoreProductId(pharmaOutDetailDto.getStoreProductId());
			paramDto.setOutputCnt(-1 * pharmaOutDetailDto.getOutputCnt());

			dao.updateStoreStockOutput(paramDto);
		}

		return dao.deleteDailyOutput(id) > 0;
	}
}
