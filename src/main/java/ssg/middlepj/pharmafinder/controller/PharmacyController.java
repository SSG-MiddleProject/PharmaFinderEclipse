package ssg.middlepj.pharmafinder.controller;

import org.jdom.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;
import ssg.middlepj.pharmafinder.dto.PharmacyParam;
import ssg.middlepj.pharmafinder.dto.ProductPharmacyQtyDto;
import ssg.middlepj.pharmafinder.service.PharmacyService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PharmacyController {
    private final PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping("/pharmacydetail.do")
    @ResponseBody
    public Map<String, Object> pharmacyDetail(Integer storeId) {
        Map<String, Object> map = new HashMap<>();
        PharmacyDto pharmacy = pharmacyService.selectPharmacybyDB(storeId);
        List<ProductPharmacyQtyDto> productsWithQty = pharmacyService.selectProductByPharmacyId(storeId);
        map.put("pharmacy", pharmacy);
        map.put("productsWithQty", productsWithQty);
        return map;
    }

    @RequestMapping("/insertpharmacy.do")
    public void insertPharmacy(PharmacyParam pharmacyParam) throws IOException, ParserConfigurationException, SAXException, JDOMException {
        pharmacyService.insertPharmacy(pharmacyParam);
    }
}
