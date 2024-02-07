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
	@GetMapping(value = "/pharma-product-common-search.do")
	public List<ProductDto> pharmaProductCommonSearch(PharmaProductSearchParam param) {
		System.out.println("PharmaProductCommonService pharmaProductCommonSearch()");
		
		return service.selectProducts(param);
	}
	
}
