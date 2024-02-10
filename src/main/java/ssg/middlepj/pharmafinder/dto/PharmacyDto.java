package ssg.middlepj.pharmafinder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PharmacyDto {
    private String dutyAddr; // 주소
    private String dutyName; // 약국명
    private String dutyTel1; // 대표번호 1
    private String dutyTime1c; // 진료시간(mon) Close
    private String dutyTime1s; // 진료시간(mon) Start
    private String dutyTime2c; // 진료시간(tue) Close
    private String dutyTime2s; // 진료시간(tue) Start
    private String dutyTime3c; // 진료시간(wed) Close
    private String dutyTime3s; // 진료시간(wed) Start
    private String dutyTime4c; // 진료시간(thu) Close
    private String dutyTime4s; // 진료시간(thu) Start
    private String dutyTime5c; // 진료시간(fri) Close
    private String dutyTime5s; // 진료시간(fri) Start
    private String dutyTime6c; // 진료시간(sat) Close
    private String dutyTime6s; // 진료시간(sat) Start
    private String dutyTime7c; // 진료시간(sun) Close
    private String dutyTime7s; // 진료시간(sun) Start
    private String dutyTime8c; // 진료시간(holi) Close
    private String dutyTime8s; // 진료시간(holi) Start
    private String hpid; // 약국 ID
    private float wgs84Lon; // 경도
    private float wgs84Lat; // 위도
}


