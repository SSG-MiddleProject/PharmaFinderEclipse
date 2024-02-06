package ssg.middlepj.pharmafinder.dto;


import org.springframework.stereotype.Component;

@Component
public class PharmacyParam {
    public String Q0 = "서울특별시"; // 주소(시도)
    public String Q1 = "강남구"; // 주소(시군구)
//    public int QT = 1; // 진료요일
    public String QN = "삼성"; // 약국명
    public String ORD = "NAME"; // 정렬순서
    public int pageNo = 1; // 페이지 번호
    public int numOfRows = 10; // 한 페이지 결과 수

    public PharmacyParam() {
    }

    public PharmacyParam(String Q0, String Q1, String QN, String ORD, int pageNo, int numOfRows) {
        this.Q0 = Q0;
        this.Q1 = Q1;
//        this.QT = QT;
        this.QN = QN;
        this.ORD = ORD;
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;
    }

    public String getQ0() {
        return Q0;
    }

    public void setQ0(String q0) {
        Q0 = q0;
    }

    public String getQ1() {
        return Q1;
    }

    public void setQ1(String q1) {
        Q1 = q1;
    }

//    public int getQT() {
//        return QT;
//    }
//
//    public void setQT(int QT) {
//        this.QT = QT;
//    }

    public String getQN() {
        return QN;
    }

    public void setQN(String QN) {
        this.QN = QN;
    }

    public String getORD() {
        return ORD;
    }

    public void setORD(String ORD) {
        this.ORD = ORD;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    @Override
    public String toString() {
        return "PharmacyParam{" +
                "Q0='" + Q0 + '\'' +
                ", Q1='" + Q1 + '\'' +
//                ", QT=" + QT +
                ", QN='" + QN + '\'' +
                ", ORD='" + ORD + '\'' +
                ", pageNo=" + pageNo +
                ", numOfRows=" + numOfRows +
                '}';
    }


}
