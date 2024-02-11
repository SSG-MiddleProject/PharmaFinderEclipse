package ssg.middlepj.pharmafinder.service;

import org.jdom.JDOMException;
import org.xml.sax.SAXException;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;
import ssg.middlepj.pharmafinder.dto.PharmacyExtDto;
import ssg.middlepj.pharmafinder.dto.PharmacyParam;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface PharmacyService {

    List<PharmacyExtDto> selectPharmacies(PharmacyParam pharmacyParam) throws IOException, JDOMException, ParserConfigurationException, SAXException;

    PharmacyDto selectPharmacy(PharmacyParam pharmacyParam) throws IOException, ParserConfigurationException, SAXException;

    List<PharmacyExtDto> selectPharmaciesByDB(PharmacyParam pharmacyParam);

    PharmacyDto selectPharmacybyDB(PharmacyParam pharmacyParam);
}
