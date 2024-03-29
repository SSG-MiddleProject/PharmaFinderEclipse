package ssg.middlepj.pharmafinder.service;

import org.jdom.JDOMException;
import org.xml.sax.SAXException;
import ssg.middlepj.pharmafinder.dto.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface PharmacyService {

    List<PharmacyExtDto> selectPharmacies(PharmacyParam pharmacyParam) throws IOException, JDOMException, ParserConfigurationException, SAXException;

    PharmacyDto selectPharmacy(PharmacyParam pharmacyParam) throws IOException, ParserConfigurationException, SAXException;

    List<PharmacyResDto> selectPharmaciesByDB(PharmacyParam paiginationParam);

    int countPharmacyList(PharmacyParam paginationParam);

    PharmacyDto selectPharmacybyDB(int storeId);

    void insertPharmacy(PharmacyParam pharmacyParam) throws IOException, JDOMException, ParserConfigurationException, SAXException;

    List<ProductPharmacyQtyDto> selectProductByPharmacyId(Integer storeId);
}
