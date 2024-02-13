package ssg.middlepj.pharmafinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ssg.middlepj.pharmafinder.dto.Pagination;
import ssg.middlepj.pharmafinder.dto.PaginationParam;
import ssg.middlepj.pharmafinder.dto.PharmacyParam;
import ssg.middlepj.pharmafinder.dto.PharmacyResDto;
import ssg.middlepj.pharmafinder.service.PharmacyService;
import ssg.middlepj.pharmafinder.service.ProductService;

import java.util.List;

@Controller
public class MainController {
    private final ProductService productService;
    private final PharmacyService pharmacyService;

    @Autowired
    public MainController(ProductService productService, PharmacyService pharmacyService) {
        this.productService = productService;
        this.pharmacyService = pharmacyService;
    }

    @RequestMapping(value = "/main.do", method = RequestMethod.GET)
    public String landing(Model model, PaginationParam paginationParam) {
        paginationParam.setUserId(1);
        model.addAttribute("products", productService.selectProducts(paginationParam));
        model.addAttribute("pagination", new Pagination(productService.selectProductsCnt(paginationParam), paginationParam));
        return "main.tiles";
    }

    @RequestMapping(value = "/pharmacy.do", method = RequestMethod.GET)
    public String pharmacyT(Model model, PharmacyParam paginationParam) {
        paginationParam.setUserId(1);
        List<PharmacyResDto> pharmacies = pharmacyService.selectPharmaciesByDB(paginationParam);
        model.addAttribute("pharmacies", pharmacies);
        model.addAttribute("pagination", new Pagination(pharmacyService.countPharmacyList(paginationParam), paginationParam));

        return "pharmacySearch.tiles";
    }
}