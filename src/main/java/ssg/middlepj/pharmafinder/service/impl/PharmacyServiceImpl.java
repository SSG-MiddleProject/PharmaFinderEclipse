package ssg.middlepj.pharmafinder.service.impl;

import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import ssg.middlepj.pharmafinder.dao.PharmacyDao;
import ssg.middlepj.pharmafinder.dto.*;
import ssg.middlepj.pharmafinder.service.PharmacyService;
import ssg.middlepj.pharmafinder.util.XMLParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static ssg.middlepj.pharmafinder.util.XMLParser.xmlParser;


@Service
public class PharmacyServiceImpl implements PharmacyService {
    private final PharmacyDao pharmacyDao;

    @Value("${SERVICE_KEY}")
    private String SERVICE_KEY;

    public PharmacyServiceImpl(PharmacyDao pharmacyDao) {
        this.pharmacyDao = pharmacyDao;
    }

    @Override
    public List<PharmacyExtDto> selectPharmacies(PharmacyParam pharmacyParam) throws IOException, JDOMException, ParserConfigurationException, SAXException {
        String urlBuilder = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire" + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + SERVICE_KEY + /*Service Key*/
                "&" + URLEncoder.encode("Q0", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pharmacyParam.getQ0(), StandardCharsets.UTF_8) + /*주소(시도)*/
                "&" + URLEncoder.encode("Q1", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pharmacyParam.getQ1(), StandardCharsets.UTF_8) + /*주소(시군구)*/
                "&" + URLEncoder.encode("QN", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pharmacyParam.getQN(), StandardCharsets.UTF_8) + /*기관명*/
                "&" + URLEncoder.encode("ORD", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pharmacyParam.getORD(), StandardCharsets.UTF_8); /*목록 건수*/

        URL url = new URL(urlBuilder);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return xmlParser(sb.toString());
    }

    @Override
    public PharmacyDto selectPharmacy(PharmacyParam pharmacyParam) throws IOException, ParserConfigurationException, SAXException {
        String urlBuilder = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire" + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + SERVICE_KEY + /*Service Key*/
                "&" + URLEncoder.encode("Q0", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pharmacyParam.getQ0(), StandardCharsets.UTF_8) + /*주소(시도)*/
                "&" + URLEncoder.encode("Q1", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pharmacyParam.getQ1(), StandardCharsets.UTF_8) + /*주소(시군구)*/
                "&" + URLEncoder.encode("QN", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pharmacyParam.getQN(), StandardCharsets.UTF_8) + /*기관명*/
                "&" + URLEncoder.encode("ORD", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pharmacyParam.getORD(), StandardCharsets.UTF_8); /*목록 건수*/

        URL url = new URL(urlBuilder);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return XMLParser.pharmaParser(sb.toString());
    }

    @Override
    public List<PharmacyResDto> selectPharmaciesByDB(PharmacyParam paiginationParam) {
        return pharmacyDao.selectPharmaciesByDB(paiginationParam);
    }

    @Override
    public int countPharmacyList(PharmacyParam paginationParam) {
        return pharmacyDao.countPharmacyList(paginationParam);
    }

    @Override
    public PharmacyDto selectPharmacybyDB(int storeId) {
        return pharmacyDao.selectPharmacybyDB(storeId);
    }

    @Override
    public void insertPharmacy(PharmacyParam pharmacyParam) throws IOException, JDOMException, ParserConfigurationException, SAXException {
        String urlBuilder = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire" + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + SERVICE_KEY + /*Service Key*/
                "&" + URLEncoder.encode("Q0", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pharmacyParam.getQ0(), StandardCharsets.UTF_8) + /*주소(시도)*/
                "&" + URLEncoder.encode("Q1", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pharmacyParam.getQ1(), StandardCharsets.UTF_8) + /*주소(시군구)*/
                "&" + URLEncoder.encode("QN", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pharmacyParam.getQN(), StandardCharsets.UTF_8) + /*기관명*/
                "&" + URLEncoder.encode("ORD", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pharmacyParam.getORD(), StandardCharsets.UTF_8); /*목록 건수*/

        URL url = new URL(urlBuilder);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        List<PharmacyDto> pharmacies = XMLParser.insertPharmacy(sb.toString());
        for (PharmacyDto pharmacy : pharmacies) {
            pharmacyDao.insertPharmacy(pharmacy);
        }
    }

    @Override
    public List<ProductPharmacyQtyDto> selectProductByPharmacyId(Integer storeId) {
        return pharmacyDao.selectProductByPharmacyId(storeId);
    }

}