package util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    public static List<PharmacyDto> xmlParser(String xml) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new InputSource(new StringReader(xml.trim())));
        NodeList nodeList = doc.getElementsByTagName("item");
        List<PharmacyDto> pharmacyItemList = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node itemNode = nodeList.item(i);

            PharmacyDto pharmacyDto = new PharmacyDto();

            NodeList dutyAddrList = ((Element) itemNode).getElementsByTagName("dutyAddr");
            if (dutyAddrList.getLength() > 0) {
                Node dutyAddrNode = dutyAddrList.item(0);
                pharmacyDto.setDutyAddr(dutyAddrNode.getTextContent().trim());
            }

            NodeList dutyNameList = ((Element) itemNode).getElementsByTagName("dutyName");
            if (dutyNameList.getLength() > 0) {
                Node dutyNameNode = dutyNameList.item(0);
                pharmacyDto.setDutyName(dutyNameNode.getTextContent().trim());
            }

            NodeList dutyTel1List = ((Element) itemNode).getElementsByTagName("dutyTel1");
            if (dutyTel1List.getLength() > 0) {
                Node dutyTel1Node = dutyTel1List.item(0);
                pharmacyDto.setDutyTel1(dutyTel1Node.getTextContent().trim());
            }

            for (int j = 1; j <= 6; j++) {
                String dutyTimeCode = "dutyTime" + j + "c";
                String dutyTimeStart = "dutyTime" + j + "s";

                int dutyTimeCodeValue = Integer.parseInt(((Element) itemNode).getElementsByTagName(dutyTimeCode).item(0).getTextContent().trim());
                int dutyTimeStartValue = Integer.parseInt(((Element) itemNode).getElementsByTagName(dutyTimeStart).item(0).getTextContent().trim());

                pharmacyDto.getDutyTimes()[2 * (j - 1)] = dutyTimeCodeValue;
                pharmacyDto.getDutyTimes()[2 * (j - 1) + 1] = dutyTimeStartValue;
            }

            // dutyTime 7, 8 이 존재할때만 작동
            if (((Element) itemNode).getElementsByTagName("dutyTime7c").getLength() > 0) {
                int dutyTimeCodeValue = Integer.parseInt(((Element) itemNode).getElementsByTagName("dutyTime7c").item(0).getTextContent().trim());
                int dutyTimeStartValue = Integer.parseInt(((Element) itemNode).getElementsByTagName("dutyTime7s").item(0).getTextContent().trim());

                pharmacyDto.getDutyTimes()[12] = dutyTimeCodeValue;
                pharmacyDto.getDutyTimes()[13] = dutyTimeStartValue;
            }
            if (((Element) itemNode).getElementsByTagName("dutyTime8c").getLength() > 0) {
                int dutyTimeCodeValue = Integer.parseInt(((Element) itemNode).getElementsByTagName("dutyTime8c").item(0).getTextContent().trim());
                int dutyTimeStartValue = Integer.parseInt(((Element) itemNode).getElementsByTagName("dutyTime8s").item(0).getTextContent().trim());

                pharmacyDto.getDutyTimes()[14] = dutyTimeCodeValue;
                pharmacyDto.getDutyTimes()[15] = dutyTimeStartValue;
            }


            NodeList hpidList = ((Element) itemNode).getElementsByTagName("hpid");
            if (hpidList.getLength() > 0) {
                Node hpidNode = hpidList.item(0);
                pharmacyDto.setHpid(hpidNode.getTextContent().trim());
            }

            NodeList wgs84LonList = ((Element) itemNode).getElementsByTagName("wgs84Lon");
            if (wgs84LonList.getLength() > 0) {
                Node wgs84LonNode = wgs84LonList.item(0);
                pharmacyDto.setWgs84Lon(Float.parseFloat(wgs84LonNode.getTextContent().trim()));
            }

            NodeList wgs84LatList = ((Element) itemNode).getElementsByTagName("wgs84Lat");
            if (wgs84LatList.getLength() > 0) {
                Node wgs84LatNode = wgs84LatList.item(0);
                pharmacyDto.setWgs84Lat(Float.parseFloat(wgs84LatNode.getTextContent().trim()));
            }

            pharmacyItemList.add(pharmacyDto);
        }

        for(PharmacyDto pharmacyDto : pharmacyItemList){
            System.out.println(pharmacyDto);
        }
        return pharmacyItemList;
    }
}
