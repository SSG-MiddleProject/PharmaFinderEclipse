package ssg.middlepj.pharmafinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;
import ssg.middlepj.pharmafinder.dto.PharmacyParam;
import ssg.middlepj.pharmafinder.service.PharmacyService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Controller
public class PharmacyController {
    private final PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping("/pharmacydetail.do")
    @ResponseBody
    public PharmacyDto pharmacyDetail(PharmacyParam pharmacyParam) throws IOException, ParserConfigurationException, SAXException {
        return pharmacyService.selectPharmacy(pharmacyParam);
    }
}
