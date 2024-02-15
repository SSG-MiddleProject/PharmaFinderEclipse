package ssg.middlepj.pharmafinder.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssg.middlepj.pharmafinder.dao.PharmaInventoryDao;
import ssg.middlepj.pharmafinder.dao.PharmaProductManagementDao;
import ssg.middlepj.pharmafinder.dto.InventoryDto;
import ssg.middlepj.pharmafinder.dto.InventoryStockParam;
import ssg.middlepj.pharmafinder.dto.PharmaInventoryParam;
import ssg.middlepj.pharmafinder.dto.PharmaProductManagementParam;
import ssg.middlepj.pharmafinder.dto.PharmaProductWithProductDto;
import ssg.middlepj.pharmafinder.service.PharmaInventoryService;

@Service
public class PharmaInventoryServiceImpl implements PharmaInventoryService {

	@Autowired
	private PharmaProductManagementDao productDao;

	@Autowired
	private PharmaInventoryDao dao;

	@Override
	public List<InventoryDto> selectInventory(PharmaInventoryParam param) {
		List<PharmaProductWithProductDto> productDtos = productDao.selectPharmaProducts(new PharmaProductManagementParam(param.getStoreId(), param.getProductId(), param.getItemName()));
		List<InventoryDto> list = new ArrayList<InventoryDto>();

		for (PharmaProductWithProductDto dto : productDtos) {
			int currentStockCnt = dao.selectcurrentStockCnt(new InventoryStockParam(dto.getId(), param.getCurrentDate()));
			int inputCnt = dao.selectInputCnt(new InventoryStockParam(dto.getId(), param.getCurrentDate()));
			int outputCnt = dao.selectOutputCnt(new InventoryStockParam(dto.getId(), param.getCurrentDate()));

			InventoryDto addDto = new InventoryDto(dto.getItemName(), dto.getProductId(), inputCnt, outputCnt, dto.getInputPrice(), dto.getOutputPrice(), currentStockCnt);

			list.add(addDto);
		}

		return list;
	}


}
