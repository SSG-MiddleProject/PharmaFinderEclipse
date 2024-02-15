
package ssg.middlepj.pharmafinder.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import message.ResultMsg;
import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.dto.PharmaProductManagementDto;
import ssg.middlepj.pharmafinder.dto.PharmaProductManagementParam;
import ssg.middlepj.pharmafinder.dto.PharmaProductWithProductDto;
import ssg.middlepj.pharmafinder.service.PharmaProductManagementService;

@Controller
public class PharmaProductManagementController {

	@Autowired
	private PharmaProductManagementService service;

	@GetMapping(value = "/pharma-product-management.do")
	public String productManagement(Model model, HttpServletRequest request) {
		System.out.println("PharmaProductManagementController productManagement()");

		int storeId = ((MemberDto)request.getSession().getAttribute("member")).getStoreId();
		List<PharmaProductWithProductDto> list = service.selectPharmaProducts(new PharmaProductManagementParam(storeId));

		model.addAttribute("list", list);

		return "pharma/product/management.tiles";
	}

	@ResponseBody
	@GetMapping(value = "/select-pharma-products.do")
	public List<PharmaProductWithProductDto> selectPharmaProducts(HttpServletRequest request) {
		System.out.println("PharmaProductManagementController selectPharmaProducts()");

		int storeId = ((MemberDto)request.getSession().getAttribute("member")).getStoreId();
		List<PharmaProductWithProductDto> list = service.selectPharmaProducts(new PharmaProductManagementParam(storeId));

		return list;
	}

	@ResponseBody
	@GetMapping(value = "/select-specific-pharma-products.do")
	public List<PharmaProductWithProductDto> selectSpecificPharmaProducts(PharmaProductManagementParam param ,HttpServletRequest request) {
		System.out.println("PharmaProductManagementController selectSpecificPharmaProducts()");

		int storeId = ((MemberDto)request.getSession().getAttribute("member")).getStoreId();

		param.setStoreId(storeId);
		List<PharmaProductWithProductDto> list = service.selectPharmaProducts(param);

		return list;
	}



	@ResponseBody
	@GetMapping(value = "/pharma-product-register.do")
	public ResultMsg pharmaProductRegister(PharmaProductManagementDto dto, HttpServletRequest request) {
		System.out.println("PharmaProductManagementController pharmaProductRegister()");

		int storeId = ((MemberDto)request.getSession().getAttribute("member")).getStoreId();
		PharmaProductManagementParam param = new PharmaProductManagementParam(storeId, dto.getProductId(), null);

		boolean isDuplication = service.isDuplicationPharmaProduct(param);

		if(isDuplication) {
			return new ResultMsg(!isDuplication, "제품이 중복되었습니다.");
		}

		boolean isS = service.registerPharmaProduct(dto);

		if(isS) {
			return new ResultMsg(isS);
		} else {
			return new ResultMsg(false, null);
		}
	}

	//pharmaProductId
	@ResponseBody
	@GetMapping(value = "/delete-pharma-product.do")
	public ResultMsg deletePharmaProduct(int pharmaProductId) {
		System.out.println("PharmaProductManagementController deletePharmaProduct()");

		PharmaProductManagementDto dto = new PharmaProductManagementDto();
		dto.setId(pharmaProductId);

		int deletedRows = service.deletePharmaProduct(dto);

		if(deletedRows > 0) {
			return new ResultMsg(true);
		} else {
			return new ResultMsg(false, "삭제된 행이 0개 입니다.");
		}
	}

	@GetMapping(value = "/pharma-product-update.do")
	public String updatePharmaProduct(int id, Model model) {
		System.out.println("PharmaProductManagementController updatePharmaProduct()");

		PharmaProductWithProductDto dto = service.selectPharmaProduct(id);
		model.addAttribute("dto", dto);

		return "pharma/product/update.tiles";
	}

	@GetMapping(value = "/pharma-product-updateAf.do")
	public String updateAfPharmaProduct(PharmaProductManagementDto dto, Model model, HttpServletRequest request) {
		System.out.println("PharmaProductManagementController updateAfPharmaProduct()");

		service.updatePharmaProduct(dto);

		return "redirect:/pharma-product-management.do";
	}


}