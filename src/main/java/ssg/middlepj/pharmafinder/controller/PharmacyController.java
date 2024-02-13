package ssg.middlepj.pharmafinder.controller;

import org.jdom.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;
import ssg.middlepj.pharmafinder.dto.PharmacyParam;
import ssg.middlepj.pharmafinder.service.PharmacyService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class PharmacyController {
    private final PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping("/pharmacydetail.do")
    @ResponseBody
    public PharmacyDto pharmacyDetail(PharmacyParam pharmacyParam) throws IOException, ParserConfigurationException, SAXException {
        if (pharmacyService.selectPharmacybyDB(pharmacyParam) != null) {
            return pharmacyService.selectPharmacybyDB(pharmacyParam);
        } else {
            return pharmacyService.selectPharmacy(pharmacyParam);
        }
    }

    @RequestMapping("/insertpharmacy.do")
    public void insertPharmacy(PharmacyParam pharmacyParam) throws IOException, ParserConfigurationException, SAXException, JDOMException {
        pharmacyService.insertPharmacy(pharmacyParam);
    }
}
