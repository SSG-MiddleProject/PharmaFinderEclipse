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

            NodeList dutyTime1cList = ((Element) itemNode).getElementsByTagName("dutyTime1c");
            if (dutyTime1cList.getLength() > 0) {
                Node dutyTime1cNode = dutyTime1cList.item(0);
                pharmacyDto.setDutyTime1c(Integer.parseInt(dutyTime1cNode.getTextContent().trim()));
            }

            NodeList dutyTime1sList = ((Element) itemNode).getElementsByTagName("dutyTime1s");
            if (dutyTime1sList.getLength() > 0) {
                Node dutyTime1sNode = dutyTime1sList.item(0);
                pharmacyDto.setDutyTime1s(Integer.parseInt(dutyTime1sNode.getTextContent().trim()));
            }

            NodeList dutyTime2cList = ((Element) itemNode).getElementsByTagName("dutyTime2c");
            if (dutyTime2cList.getLength() > 0) {
                Node dutyTime2cNode = dutyTime2cList.item(0);
                pharmacyDto.setDutyTime2c(Integer.parseInt(dutyTime2cNode.getTextContent().trim()));
            }

            NodeList dutyTime2sList = ((Element) itemNode).getElementsByTagName("dutyTime2s");
            if (dutyTime2sList.getLength() > 0) {
                Node dutyTime2sNode = dutyTime2sList.item(0);
                pharmacyDto.setDutyTime2s(Integer.parseInt(dutyTime2sNode.getTextContent().trim()));
            }

            NodeList dutyTime3cList = ((Element) itemNode).getElementsByTagName("dutyTime3c");
            if (dutyTime3cList.getLength() > 0) {
                Node dutyTime3cNode = dutyTime3cList.item(0);
                pharmacyDto.setDutyTime3c(Integer.parseInt(dutyTime3cNode.getTextContent().trim()));
            }

            NodeList dutyTime3sList = ((Element) itemNode).getElementsByTagName("dutyTime3s");
            if (dutyTime3sList.getLength() > 0) {
                Node dutyTime3sNode = dutyTime3sList.item(0);
                pharmacyDto.setDutyTime3s(Integer.parseInt(dutyTime3sNode.getTextContent().trim()));
            }

            NodeList dutyTime4cList = ((Element) itemNode).getElementsByTagName("dutyTime4c");
            if (dutyTime4cList.getLength() > 0) {
                Node dutyTime4cNode = dutyTime4cList.item(0);
                pharmacyDto.setDutyTime4c(Integer.parseInt(dutyTime4cNode.getTextContent().trim()));
            }

            NodeList dutyTime4sList = ((Element) itemNode).getElementsByTagName("dutyTime4s");
            if (dutyTime4sList.getLength() > 0) {
                Node dutyTime4sNode = dutyTime4sList.item(0);
                pharmacyDto.setDutyTime4s(Integer.parseInt(dutyTime4sNode.getTextContent().trim()));
            }

            NodeList dutyTime5cList = ((Element) itemNode).getElementsByTagName("dutyTime5c");
            if (dutyTime5cList.getLength() > 0) {
                Node dutyTime5cNode = dutyTime5cList.item(0);
                pharmacyDto.setDutyTime5c(Integer.parseInt(dutyTime5cNode.getTextContent().trim()));
            }

            NodeList dutyTime5sList = ((Element) itemNode).getElementsByTagName("dutyTime5s");
            if (dutyTime5sList.getLength() > 0) {
                Node dutyTime5sNode = dutyTime5sList.item(0);
                pharmacyDto.setDutyTime5s(Integer.parseInt(dutyTime5sNode.getTextContent().trim()));
            }

            NodeList dutyTime6cList = ((Element) itemNode).getElementsByTagName("dutyTime6c");
            if (dutyTime6cList.getLength() > 0) {
                Node dutyTime6cNode = dutyTime6cList.item(0);
                pharmacyDto.setDutyTime6c(Integer.parseInt(dutyTime6cNode.getTextContent().trim()));
            }

            NodeList dutyTime6sList = ((Element) itemNode).getElementsByTagName("dutyTime6s");
            if (dutyTime6sList.getLength() > 0) {
                Node dutyTime6sNode = dutyTime6sList.item(0);
                pharmacyDto.setDutyTime6s(Integer.parseInt(dutyTime6sNode.getTextContent().trim()));
            }

            // 7,8이 있을때만 작동
            if (dutyTime6sList.getLength() > 0) {
                NodeList dutyTime7cList = ((Element) itemNode).getElementsByTagName("dutyTime7c");
                if (dutyTime7cList.getLength() > 0) {
                    Node dutyTime7cNode = dutyTime7cList.item(0);
                    pharmacyDto.setDutyTime7c(null);
                }

                NodeList dutyTime7sList = ((Element) itemNode).getElementsByTagName("dutyTime7s");
                if (dutyTime7sList.getLength() > 0) {
                    Node dutyTime7sNode = dutyTime7sList.item(0);
                    pharmacyDto.setDutyTime7s(null);
                }

                NodeList dutyTime8cList = ((Element) itemNode).getElementsByTagName("dutyTime8c");
                if (dutyTime8cList.getLength() > 0) {
                    Node dutyTime8cNode = dutyTime8cList.item(0);
                    pharmacyDto.setDutyTime8c(null);
                }

                NodeList dutyTime8sList = ((Element) itemNode).getElementsByTagName("dutyTime8s");
                if (dutyTime8sList.getLength() > 0) {
                    Node dutyTime8sNode = dutyTime8sList.item(0);
                    pharmacyDto.setDutyTime8s(null);
                }
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

        for (PharmacyDto pharmacyDto : pharmacyItemList) {
            System.out.println(pharmacyDto);
        }
        return pharmacyItemList;
    }
}
