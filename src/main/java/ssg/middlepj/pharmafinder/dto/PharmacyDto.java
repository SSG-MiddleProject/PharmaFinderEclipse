package ssg.middlepj.pharmafinder.dto;

import java.io.Serializable;

public class PharmacyDto implements Serializable {
    private String dutyAddr; // 주소
    private String dutyName; // 약국명
    private String dutyTel1; // 대표번호 1
    private Integer dutyTime1c; // 진료시간(mon) Close
    private Integer dutyTime1s; // 진료시간(mon) Start
    private Integer dutyTime2c; // 진료시간(tue) Close
    private Integer dutyTime2s; // 진료시간(tue) Start
    private Integer dutyTime3c; // 진료시간(wed) Close
    private Integer dutyTime3s; // 진료시간(wed) Start
    private Integer dutyTime4c; // 진료시간(thu) Close
    private Integer dutyTime4s; // 진료시간(thu) Start
    private Integer dutyTime5c; // 진료시간(fri) Close
    private Integer dutyTime5s; // 진료시간(fri) Start
    private Integer dutyTime6c; // 진료시간(sat) Close
    private Integer dutyTime6s; // 진료시간(sat) Start
    private Integer dutyTime7c; // 진료시간(sun) Close
    private Integer dutyTime7s; // 진료시간(sun) Start
    private Integer dutyTime8c; // 진료시간(holi) Close
    private Integer dutyTime8s; // 진료시간(holi) Start
//    private Integer[] dutyTimes = new Integer[16]; // 8쌍의 시간이므로 총 16개
    private String hpid; // 약국 ID
    private float wgs84Lon; // 경도
    private float wgs84Lat; // 위도

//    public Integer[] getDutyTimes() {
//        return dutyTimes;
//    }
//
//    public void setDutyTimes(Integer[] dutyTimes) {
//        this.dutyTimes = dutyTimes;
//    }


//    public String getDutyTimesAsJson() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        ObjectNode jsonNode = objectMapper.createObjectNode();
//
//        // 운영시간 데이터를 JSON 객체에 매핑
//        for (int i = 0; i < dutyTimes.length; i += 2) {
//            String dayOfWeek = getDayOfWeekFromIndex(i);
//            String keyClose = "dutyTime" + dayOfWeek + "c";
//            String keyStart = "dutyTime" + dayOfWeek + "s";
//
//            jsonNode.put(keyClose, dutyTimes[i]);
//            jsonNode.put(keyStart, dutyTimes[i + 1]);
//        }
//
//        // JSON 문자열 반환
//        return jsonNode.toString();
//
//    }
//
//    private String getDayOfWeekFromIndex(int index) {
//        switch (index / 2) {
//            case 0:
//                return "mon";
//            case 1:
//                return "tue";
//            case 2:
//                return "wed";
//            case 3:
//                return "thu";
//            case 4:
//                return "fri";
//            case 5:
//                return "sat";
//            case 6:
//                return "sun";
//            case 7:
//                return "holi";
//            default:
//                throw new IllegalArgumentException("Invalid index for day of week");
//        }
//    }


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

    public int getDutyTime1c() {
        return dutyTime1c;
    }

    public void setDutyTime1c(int dutyTime1c) {
        this.dutyTime1c = dutyTime1c;
    }

    public int getDutyTime1s() {
        return dutyTime1s;
    }

    public void setDutyTime1s(int dutyTime1s) {
        this.dutyTime1s = dutyTime1s;
    }

    public int getDutyTime2c() {
        return dutyTime2c;
    }

    public void setDutyTime2c(int dutyTime2c) {
        this.dutyTime2c = dutyTime2c;
    }

    public int getDutyTime2s() {
        return dutyTime2s;
    }

    public void setDutyTime2s(int dutyTime2s) {
        this.dutyTime2s = dutyTime2s;
    }

    public int getDutyTime3c() {
        return dutyTime3c;
    }

    public void setDutyTime3c(int dutyTime3c) {
        this.dutyTime3c = dutyTime3c;
    }

    public int getDutyTime3s() {
        return dutyTime3s;
    }

    public void setDutyTime3s(int dutyTime3s) {
        this.dutyTime3s = dutyTime3s;
    }

    public int getDutyTime4c() {
        return dutyTime4c;
    }

    public void setDutyTime4c(int dutyTime4c) {
        this.dutyTime4c = dutyTime4c;
    }

    public int getDutyTime4s() {
        return dutyTime4s;
    }

    public void setDutyTime4s(int dutyTime4s) {
        this.dutyTime4s = dutyTime4s;
    }

    public int getDutyTime5c() {
        return dutyTime5c;
    }

    public void setDutyTime5c(int dutyTime5c) {
        this.dutyTime5c = dutyTime5c;
    }

    public int getDutyTime5s() {
        return dutyTime5s;
    }

    public void setDutyTime5s(int dutyTime5s) {
        this.dutyTime5s = dutyTime5s;
    }

    public int getDutyTime6c() {
        return dutyTime6c;
    }

    public void setDutyTime6c(int dutyTime6c) {
        this.dutyTime6c = dutyTime6c;
    }

    public int getDutyTime6s() {
        return dutyTime6s;
    }

    public void setDutyTime6s(int dutyTime6s) {
        this.dutyTime6s = dutyTime6s;
    }

    public int getDutyTime7c() {
        return dutyTime7c;
    }

    public void setDutyTime7c(Integer dutyTime7c) {
        this.dutyTime7c = dutyTime7c;
    }

    public int getDutyTime7s() {
        return dutyTime7s;
    }

    public void setDutyTime7s(Integer dutyTime7s) {
        this.dutyTime7s = dutyTime7s;
    }

    public int getDutyTime8c() {
        return dutyTime8c;
    }

    public void setDutyTime8c(Integer dutyTime8c) {
        this.dutyTime8c = dutyTime8c;
    }

    public int getDutyTime8s() {
        return dutyTime8s;
    }

    public void setDutyTime8s(Integer dutyTime8s) {
        this.dutyTime8s = dutyTime8s;
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
//                ", dutyTimes=" + dutyTimes +
                ", dutyTime1c=" + dutyTime1c +
                ", dutyTime1s=" + dutyTime1s +
                ", dutyTime2c=" + dutyTime2c +
                ", dutyTime2s=" + dutyTime2s +
                ", dutyTime3c=" + dutyTime3c +
                ", dutyTime3s=" + dutyTime3s +
                ", dutyTime4c=" + dutyTime4c +
                ", dutyTime4s=" + dutyTime4s +
                ", dutyTime5c=" + dutyTime5c +
                ", dutyTime5s=" + dutyTime5s +
                ", dutyTime6c=" + dutyTime6c +
                ", dutyTime6s=" + dutyTime6s +
                ", dutyTime7c=" + dutyTime7c +
                ", dutyTime7s=" + dutyTime7s +
                ", dutyTime8c=" + dutyTime8c +


                ", hpid='" + hpid + '\'' +
                ", wgs84Lon=" + wgs84Lon +
                ", wgs84Lat=" + wgs84Lat +
                '}';
    }




}
