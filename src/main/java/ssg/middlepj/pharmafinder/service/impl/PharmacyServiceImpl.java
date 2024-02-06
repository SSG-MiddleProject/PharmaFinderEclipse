package ssg.middlepj.pharmafinder.service.impl;

import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;
import ssg.middlepj.pharmafinder.dto.PharmacyParam;
import ssg.middlepj.pharmafinder.service.PharmacyService;
import javax.annotation.Nullable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import static util.XMLParser.xmlParser;

@Service
public class PharmacyServiceImpl implements PharmacyService {
    @Value("${SERVICE_KEY}")
    private String SERVICE_KEY;

    @Override
    public List<PharmacyDto> selectPharmacies(PharmacyParam pharmacyParam) throws IOException, JDOMException, ParserConfigurationException, SAXException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + SERVICE_KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("Q0", "UTF-8") + "=" + URLEncoder.encode(pharmacyParam.Q0, "UTF-8")); /*주소(시도)*/
        urlBuilder.append("&" + URLEncoder.encode("Q1", "UTF-8") + "=" + URLEncoder.encode(pharmacyParam.Q1, "UTF-8")); /*주소(시군구)*/
//        urlBuilder.append("&" + URLEncoder.encode("QT", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pharmacyParam.QT), "UTF-8")); /*월~일요일, 공휴일: 1~8*/
        urlBuilder.append("&" + URLEncoder.encode("QN", "UTF-8") + "=" + URLEncoder.encode(pharmacyParam.QN, "UTF-8")); /*기관명*/
        urlBuilder.append("&" + URLEncoder.encode("ORD", "UTF-8") + "=" + URLEncoder.encode(pharmacyParam.ORD, "UTF-8")); /*순서*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pharmacyParam.pageNo), "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(pharmacyParam.numOfRows), "UTF-8")); /*목록 건수*/
        URL url = new URL(urlBuilder.toString());
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
}