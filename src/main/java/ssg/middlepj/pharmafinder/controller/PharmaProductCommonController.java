package ssg.middlepj.pharmafinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ssg.middlepj.pharmafinder.dto.PharmaProductSearchParam;
import ssg.middlepj.pharmafinder.dto.ProductDto;
import ssg.middlepj.pharmafinder.service.PharmaProductCommonService;

@Controller 
public class PharmaProductCommonController {
	
	@Autowired
	private PharmaProductCommonService service;
	
	@ResponseBody
	@GetMapping(value = "/pharmaProductSearch.do")
	public List<ProductDto> searchProducts(PharmaProductSearchParam param) {
		System.out.println("PharmaProductCommonService searchProducts()");
		List<ProductDto> list = null;
		
		list = service.selectProducts(param);
		
		return list;
	}
	
}
