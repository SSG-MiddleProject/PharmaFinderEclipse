package ssg.middlepj.pharmafinder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PharmacyDto {
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
    private String hpid; // 약국 ID
    private float wgs84Lon; // 경도
    private float wgs84Lat; // 위도
}


