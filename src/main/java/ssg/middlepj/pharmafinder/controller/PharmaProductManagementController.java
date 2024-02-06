package ssg.middlepj.pharmafinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import message.ResultMsg;
import ssg.middlepj.pharmafinder.dto.PharmaProductManagementDto;
import ssg.middlepj.pharmafinder.dto.PharmaProductManagementParam;
import ssg.middlepj.pharmafinder.dto.PharmaProductSearchParam;
import ssg.middlepj.pharmafinder.dto.PharmaProductWithProductDto;
import ssg.middlepj.pharmafinder.service.PharmaProductManagementService;

@Controller
public class PharmaProductManagementController {

	@Autowired
	private PharmaProductManagementService service;
	
	@GetMapping(value = "/productManagement.do")
	public String list(String storeId, Model model) {
		System.out.println("PharmaProductManagementController list()");
		// 등록된 리스트를 forward 시킨다.
		List<PharmaProductWithProductDto> list = getPharmaProducts(storeId);
		
		model.addAttribute("list", list);
		
		return "pharma/product/management.tiles";
	}
	
	@ResponseBody
	@GetMapping(value = "/refreshPharmaProduct.do")
	public List<PharmaProductWithProductDto> refreshPharmaProduct(String storeId) {
		System.out.println("PharmaProductManagementController refreshPharmaProduct()");
		// 등록된 리스트를 forward 시킨다.
		List<PharmaProductWithProductDto> list = getPharmaProducts(storeId);
		
		return list; 
	}
	
	private List<PharmaProductWithProductDto> getPharmaProducts(String storeId) {
		PharmaProductManagementParam emptyParam = new PharmaProductManagementParam(9999,null,null);
		
		List<PharmaProductWithProductDto> list = service.searchPharmaProducts(emptyParam);
		
		return list;
	}
	
	@ResponseBody
	@GetMapping(value = "/pharmaProductInsert.do")
	public ResultMsg insertPharmaProduct(PharmaProductManagementDto dto) {
		System.out.println("PharmaProductManagementController insertPharmaProduct()");
		
		PharmaProductManagementParam param = new PharmaProductManagementParam(dto.getStoreId(), dto.getProductId(), null);
		
		boolean isDuplication = service.isDuplicationPharmaProduct(param);
				
		if(isDuplication) {
			return new ResultMsg(!isDuplication, "제품이 중복되었습니다.");
		}
		
		boolean isS = service.insertPharmaProduct(dto);
		
		if(isS) {
			return new ResultMsg(isS, null);
		} else {
			return new ResultMsg(false, null);
		}
	}
	
	//pharmaProductId
	@ResponseBody
	@GetMapping(value = "/deletePharmaProduct.do")
	public ResultMsg deletePharmaProduct(int pharmaProductId) {
		System.out.println("PharmaProductManagementController deletePharmaProduct()");
		
		ResultMsg resultMsg = new ResultMsg();
		
		PharmaProductManagementDto dto = new PharmaProductManagementDto();
		dto.setId(pharmaProductId);
		
		int deletedRows = service.deletePharmaProduct(dto);
		
		if(deletedRows > 0) {
			return new ResultMsg(true, null);
		} else {
			return new ResultMsg(false, "삭제된 행이 0개 입니다.");
		}
	}
}