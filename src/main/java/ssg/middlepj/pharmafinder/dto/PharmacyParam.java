package ssg.middlepj.pharmafinder.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PharmacyParam extends PaginationParam {
    private String Q0;
    private String Q1;
    private String QN;
    private String ORD;

    public PharmacyParam() {
        this.Q0 = "부산";
        this.Q1 = "";
        this.QN = "";
        this.ORD = "NAME";
    }

}
