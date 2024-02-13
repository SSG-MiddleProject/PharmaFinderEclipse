package ssg.middlepj.pharmafinder.controller;

import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import ssg.middlepj.pharmafinder.dto.Pagination;
import ssg.middlepj.pharmafinder.dto.PaginationParam;
import ssg.middlepj.pharmafinder.dto.PharmacyParam;
import ssg.middlepj.pharmafinder.service.PharmacyService;
import ssg.middlepj.pharmafinder.service.ProductService;


import javax.annotation.Nullable;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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
    public String pharmacy(Model model, PharmacyParam pharmacyParam) throws IOException, ParserConfigurationException, JDOMException, SAXException {
        model.addAttribute("pharmacies", pharmacyService.selectPharmacies(pharmacyParam));
//        model.addAttribute("pagination", pharmacyService.selectPharmacies(pharmacyParam));
        return "pharmacySearch.tiles";
    }
    
    

}