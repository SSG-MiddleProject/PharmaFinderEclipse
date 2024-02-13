package ssg.middlepj.pharmafinder.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

import javax.annotation.Nullable;
import java.io.Serializable;

public class PharmacyDto implements Serializable {
    private String dutyAddr; // 주소
    private String dutyName; // 약국명
    private String dutyTel1; // 대표번호 1
    private Integer[] dutyTimes = new Integer[16]; // 8쌍의 시간이므로 총 16개
    private String hpid; // 약국 ID
    private float wgs84Lon; // 경도
    private float wgs84Lat; // 위도

    public Integer[] getDutyTimes() {
        return dutyTimes;
    }

    public void setDutyTimes(Integer[] dutyTimes) {
        this.dutyTimes = dutyTimes;
    }


    public String getDutyTimesAsJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNode = objectMapper.createObjectNode();

        // 운영시간 데이터를 JSON 객체에 매핑
        for (int i = 0; i < dutyTimes.length; i += 2) {
            String dayOfWeek = getDayOfWeekFromIndex(i);
            String keyClose = "dutyTime" + dayOfWeek + "c";
            String keyStart = "dutyTime" + dayOfWeek + "s";

            jsonNode.put(keyClose, dutyTimes[i]);
            jsonNode.put(keyStart, dutyTimes[i + 1]);
        }

        // JSON 문자열 반환
        return jsonNode.toString();

    }

    private String getDayOfWeekFromIndex(int index) {
        switch (index / 2) {
            case 0:
                return "mon";
            case 1:
                return "tue";
            case 2:
                return "wed";
            case 3:
                return "thu";
            case 4:
                return "fri";
            case 5:
                return "sat";
            case 6:
                return "sun";
            case 7:
                return "holi";
            default:
                throw new IllegalArgumentException("Invalid index for day of week");
        }
    }


    public String getDutyAddr() {
        return dutyAddr;
    }

    public void setDutyAddr(String dutyAddr) {
        this.dutyAddr = dutyAddr;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getDutyTel1() {
        return dutyTel1;
    }

    public void setDutyTel1(String dutyTel1) {
        this.dutyTel1 = dutyTel1;
    }

    public String getHpid() {
        return hpid;
    }

    public void setHpid(String hpid) {
        this.hpid = hpid;
    }


    public float getWgs84Lon() {
        return wgs84Lon;
    }

    public void setWgs84Lon(float wgs84Lon) {
        this.wgs84Lon = wgs84Lon;
    }

    public float getWgs84Lat() {
        return wgs84Lat;
    }

    public void setWgs84Lat(float wgs84Lat) {
        this.wgs84Lat = wgs84Lat;
    }

//    @Override
//    public String toString() {
//        return "PharmacyDto [dutyAddr=" + dutyAddr + ", dutyName=" + dutyName + ", dutyTel1=" + dutyTel1 + ", dutyTime1c="
//                + dutyTime1c + ", dutyTime1s=" + dutyTime1s + ", dutyTime2c=" + dutyTime2c + ", dutyTime2s=" + dutyTime2s
//                + ", dutyTime3c=" + dutyTime3c + ", dutyTime3s=" + dutyTime3s + ", dutyTime4c=" + dutyTime4c
//                + ", dutyTime4s=" + dutyTime4s + ", dutyTime5c=" + dutyTime5c + ", dutyTime5s=" + dutyTime5s
//                + ", dutyTime6c=" + dutyTime6c + ", dutyTime6s=" + dutyTime6s + ", dutyTime7c=" + dutyTime7c
//                + ", dutyTime7s=" + dutyTime7s + ", dutyTime8c=" + dutyTime8c + ", dutyTime8s=" + dutyTime8s + ", hpid="
//                + hpid + ",  wgs84Lon=" + wgs84Lon + ", wgs84Lat=" + wgs84Lat + "]";
//    }

    @Override
    public String toString() {
        return "PharmacyDto{" +
                "dutyAddr='" + dutyAddr + '\'' +
                ", dutyName='" + dutyName + '\'' +
                ", dutyTel1='" + dutyTel1 + '\'' +
                ", dutyTimes=" + dutyTimes +
                ", hpid='" + hpid + '\'' +
                ", wgs84Lon=" + wgs84Lon +
                ", wgs84Lat=" + wgs84Lat +
                '}';
    }




}