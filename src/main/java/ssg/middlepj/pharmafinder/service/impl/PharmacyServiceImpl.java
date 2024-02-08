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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import static util.XMLParser.xmlParser;

@Service
public class PharmacyServiceImpl implements PharmacyService {
    @Value("${SERVICE_KEY}")
    private String SERVICE_KEY;

    @Override
    public List<PharmacyDto> selectPharmacies(PharmacyParam pharmacyParam) throws IOException, JDOMException, ParserConfigurationException, SAXException {
        /*URL*/
        String urlBuilder = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyListInfoInqire" + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + SERVICE_KEY + /*Service Key*/
                "&" + URLEncoder.encode("Q0", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pharmacyParam.Q0, StandardCharsets.UTF_8) + /*주소(시도)*/
                "&" + URLEncoder.encode("Q1", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pharmacyParam.Q1, StandardCharsets.UTF_8) + /*주소(시군구)*/
                "&" + URLEncoder.encode("QT", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(String.valueOf(pharmacyParam.QT), StandardCharsets.UTF_8) + /*월~일요일, 공휴일: 1~8*/
                "&" + URLEncoder.encode("QN", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pharmacyParam.QN, StandardCharsets.UTF_8) + /*기관명*/
                "&" + URLEncoder.encode("ORD", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(pharmacyParam.ORD, StandardCharsets.UTF_8) + /*순서*/
                "&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(String.valueOf(pharmacyParam.pageNo), StandardCharsets.UTF_8) + /*페이지 번호*/
                "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(String.valueOf(pharmacyParam.numOfRows), StandardCharsets.UTF_8); /*목록 건수*/
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
}