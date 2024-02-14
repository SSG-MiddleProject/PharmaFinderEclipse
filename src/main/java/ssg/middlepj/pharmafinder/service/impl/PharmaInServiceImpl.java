package ssg.middlepj.pharmafinder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssg.middlepj.pharmafinder.dao.PharmaInDao;
import ssg.middlepj.pharmafinder.dto.PharmaDailyInOutSearchByPeriodParam;
import ssg.middlepj.pharmafinder.dto.PharmaInDetailDto;
import ssg.middlepj.pharmafinder.dto.PharmaInDto;
import ssg.middlepj.pharmafinder.dto.PharmaInProductDto;
import ssg.middlepj.pharmafinder.service.PharmaInService;

@Service
public class PharmaInServiceImpl implements PharmaInService {

	@Autowired
	private PharmaInDao dao;

	@Override
	public boolean insertDailyInput(PharmaInDto dto) {
		boolean isSuccess = true;

		int inputInserted = dao.insertDailyInput(dto);

		if(inputInserted < 1) {
			isSuccess = false;
		}

		int lastId = dao.selectDailyInputLastId();

		for (PharmaInProductDto product : dto.getProducts()) {
			product.setDailyInputId(lastId);

			int inputProductInseted = dao.insertDailyInputProduct(product);
			dao.updateStoreStockInput(product);

			if(inputProductInseted < 1 && isSuccess) {
				isSuccess = false;
			}
		}

		return isSuccess;
	}

	@Override
	public PharmaInDto selectDailyInputWithProducts(int id) {
		return dao.selectDailyInputWithProducts(id);
	}

	@Override
	public List<PharmaInDto> selectDailyInputs(PharmaDailyInOutSearchByPeriodParam param) {

		return dao.selectDailyInputs(param);
	}

	@Override
	public List<PharmaInDetailDto> selectDailyInputProducts(int id) {
		return dao.selectDailyInputProducts(id);
	}

	@Override
	public boolean deleteDailyInput(int id) {

		List<PharmaInDetailDto> dtoes = dao.selectDailyInputProducts(id);

		for (PharmaInDetailDto pharmaInDetailDto : dtoes) {

			PharmaInProductDto paramDto = new PharmaInProductDto();
			paramDto.setDailyInputId(pharmaInDetailDto.getDailyInputId());
			paramDto.setStoreProductId(pharmaInDetailDto.getStoreProductId());
			paramDto.setInputCnt(-1 * pharmaInDetailDto.getInputCnt());

			dao.updateStoreStockInput(paramDto);
		}

		return dao.deleteDailyInput(id) > 0;
	}

}
