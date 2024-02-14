package ssg.middlepj.pharmafinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ssg.middlepj.pharmafinder.dto.*;
import ssg.middlepj.pharmafinder.service.PharmacyService;
import ssg.middlepj.pharmafinder.service.ProductService;

import javax.servlet.http.HttpServletRequest;
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
    public String landing(HttpServletRequest request, Model model, PaginationParam paginationParam) {
        MemberDto member = (MemberDto) request.getSession().getAttribute("member");

        if (member != null) {
            paginationParam.setUserId(member.getId());
        }

        model.addAttribute("products", productService.selectProducts(paginationParam));
        model.addAttribute("pagination", new Pagination(productService.selectProductsCnt(paginationParam), paginationParam));

        return "main.tiles";
    }

    @RequestMapping(value = "/pharmacy.do", method = RequestMethod.GET)
    public String pharmacyT(HttpServletRequest request, Model model, PharmacyParam paginationParam) {
        MemberDto member = (MemberDto) request.getSession().getAttribute("member");

        if (member != null) {
            paginationParam.setUserId(member.getId());
        }

        List<PharmacyResDto> pharmacies = pharmacyService.selectPharmaciesByDB(paginationParam);
        model.addAttribute("pharmacies", pharmacies);
        model.addAttribute("pagination", new Pagination(pharmacyService.countPharmacyList(paginationParam), paginationParam));

        return "pharmacySearch.tiles";
    }
}