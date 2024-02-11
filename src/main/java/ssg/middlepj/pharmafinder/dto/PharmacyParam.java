package ssg.middlepj.pharmafinder.dto;


import lombok.Data;

@Data
public class PharmacyParam {
    private String Q0;
    private String Q1;
    private String QN;
    private String ORD;
    private int pageNo;
    private int numOfRows;

    public PharmacyParam() {
        this.Q0 = "부산";
        this.Q1 = "해운대";
        this.QN = "";
        this.ORD = "NAME";
        this.pageNo = 1;
        this.numOfRows = 10;
    }

}
