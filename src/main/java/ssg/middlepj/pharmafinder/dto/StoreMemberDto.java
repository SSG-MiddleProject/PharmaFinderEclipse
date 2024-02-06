package ssg.middlepj.pharmafinder.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class StoreMemberDto implements Serializable {
	
	private int id;
	private String dutyName;	// 기관명
	private String dutyAddr;	// 주소
	private String dutyTel1;	// 대표전화
	private String dutyEt;		// 비고
	private int bn_reg_num;		// 사업자등록번호
	private int dutyTime1s;		// 영업시작시간(월요일)
	private int dutyTime1c; 	// 영업종료시간(월요일)
	private int dutyTime2s; 	// 영업시작시간(화요일)
	private int dutyTime2c;		// 영업종료시간(화요일)
	private int dutyTime3s; 	// 영업시작시간(수요일)
	private int dutyTime3c; 	// 영업종료시간(수요일)
	private int dutyTime4s; 	// 영업시작시간(목요일)
	private int dutyTime4c; 	// 영업종료시간(목요일)
	private int dutyTime5s; 	// 영업시작시간(금요일)
	private int dutyTime5c; 	// 영업종료시간(금요일)
	private int dutyTime6s; 	// 영업시작시간(토요일)
	private int dutyTime6c; 	// 영업종료시간(토요일)
	private int dutyTime7s; 	// 영업시작시간(일요일)
	private int dutyTime7c; 	// 영업종료시간(일요일)
	private int dutyTime8s; 	// 영업시작시간(공휴일)
	private int dutyTime8c; 	// 영업종료시간(공휴일)
	private String hpid;		// 기관ID
	private float wgs84Lon;		// 병원경도
	private float wgs84Lat;		// 병원위도
	
	public StoreMemberDto() {
	}

	public StoreMemberDto(int id, String dutyName, String dutyAddr, String dutyTel1, String dutyEt, int bn_reg_num,
			int dutyTime1s, int dutyTime1c, int dutyTime2s, int dutyTime2c, int dutyTime3s, int dutyTime3c,
			int dutyTime4s, int dutyTime4c, int dutyTime5s, int dutyTime5c, int dutyTime6s, int dutyTime6c,
			int dutyTime7s, int dutyTime7c, int dutyTime8s, int dutyTime8c, String hpid, float wgs84Lon,
			float wgs84Lat) {
		super();
		this.id = id;
		this.dutyName = dutyName;
		this.dutyAddr = dutyAddr;
		this.dutyTel1 = dutyTel1;
		this.dutyEt = dutyEt;
		this.bn_reg_num = bn_reg_num;
		this.dutyTime1s = dutyTime1s;
		this.dutyTime1c = dutyTime1c;
		this.dutyTime2s = dutyTime2s;
		this.dutyTime2c = dutyTime2c;
		this.dutyTime3s = dutyTime3s;
		this.dutyTime3c = dutyTime3c;
		this.dutyTime4s = dutyTime4s;
		this.dutyTime4c = dutyTime4c;
		this.dutyTime5s = dutyTime5s;
		this.dutyTime5c = dutyTime5c;
		this.dutyTime6s = dutyTime6s;
		this.dutyTime6c = dutyTime6c;
		this.dutyTime7s = dutyTime7s;
		this.dutyTime7c = dutyTime7c;
		this.dutyTime8s = dutyTime8s;
		this.dutyTime8c = dutyTime8c;
		this.hpid = hpid;
		this.wgs84Lon = wgs84Lon;
		this.wgs84Lat = wgs84Lat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public String getDutyAddr() {
		return dutyAddr;
	}

	public void setDutyAddr(String dutyAddr) {
		this.dutyAddr = dutyAddr;
	}

	public String getDutyTel1() {
		return dutyTel1;
	}

	public void setDutyTel1(String dutyTel1) {
		this.dutyTel1 = dutyTel1;
	}

	public String getDutyEt() {
		return dutyEt;
	}

	public void setDutyEt(String dutyEt) {
		this.dutyEt = dutyEt;
	}

	public int getBn_reg_num() {
		return bn_reg_num;
	}

	public void setBn_reg_num(int bn_reg_num) {
		this.bn_reg_num = bn_reg_num;
	}

	public int getDutyTime1s() {
		return dutyTime1s;
	}

	public void setDutyTime1s(int dutyTime1s) {
		this.dutyTime1s = dutyTime1s;
	}

	public int getDutyTime1c() {
		return dutyTime1c;
	}

	public void setDutyTime1c(int dutyTime1c) {
		this.dutyTime1c = dutyTime1c;
	}

	public int getDutyTime2s() {
		return dutyTime2s;
	}

	public void setDutyTime2s(int dutyTime2s) {
		this.dutyTime2s = dutyTime2s;
	}

	public int getDutyTime2c() {
		return dutyTime2c;
	}

	public void setDutyTime2c(int dutyTime2c) {
		this.dutyTime2c = dutyTime2c;
	}

	public int getDutyTime3s() {
		return dutyTime3s;
	}

	public void setDutyTime3s(int dutyTime3s) {
		this.dutyTime3s = dutyTime3s;
	}

	public int getDutyTime3c() {
		return dutyTime3c;
	}

	public void setDutyTime3c(int dutyTime3c) {
		this.dutyTime3c = dutyTime3c;
	}

	public int getDutyTime4s() {
		return dutyTime4s;
	}

	public void setDutyTime4s(int dutyTime4s) {
		this.dutyTime4s = dutyTime4s;
	}

	public int getDutyTime4c() {
		return dutyTime4c;
	}

	public void setDutyTime4c(int dutyTime4c) {
		this.dutyTime4c = dutyTime4c;
	}

	public int getDutyTime5s() {
		return dutyTime5s;
	}

	public void setDutyTime5s(int dutyTime5s) {
		this.dutyTime5s = dutyTime5s;
	}

	public int getDutyTime5c() {
		return dutyTime5c;
	}

	public void setDutyTime5c(int dutyTime5c) {
		this.dutyTime5c = dutyTime5c;
	}

	public int getDutyTime6s() {
		return dutyTime6s;
	}

	public void setDutyTime6s(int dutyTime6s) {
		this.dutyTime6s = dutyTime6s;
	}

	public int getDutyTime6c() {
		return dutyTime6c;
	}

	public void setDutyTime6c(int dutyTime6c) {
		this.dutyTime6c = dutyTime6c;
	}

	public int getDutyTime7s() {
		return dutyTime7s;
	}

	public void setDutyTime7s(int dutyTime7s) {
		this.dutyTime7s = dutyTime7s;
	}

	public int getDutyTime7c() {
		return dutyTime7c;
	}

	public void setDutyTime7c(int dutyTime7c) {
		this.dutyTime7c = dutyTime7c;
	}

	public int getDutyTime8s() {
		return dutyTime8s;
	}

	public void setDutyTime8s(int dutyTime8s) {
		this.dutyTime8s = dutyTime8s;
	}

	public int getDutyTime8c() {
		return dutyTime8c;
	}

	public void setDutyTime8c(int dutyTime8c) {
		this.dutyTime8c = dutyTime8c;
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

	@Override
	public String toString() {
		return "StoreMemberDto [id=" + id + ", dutyName=" + dutyName + ", dutyAddr=" + dutyAddr + ", dutyTel1="
				+ dutyTel1 + ", dutyEt=" + dutyEt + ", bn_reg_num=" + bn_reg_num + ", dutyTime1s=" + dutyTime1s
				+ ", dutyTime1c=" + dutyTime1c + ", dutyTime2s=" + dutyTime2s + ", dutyTime2c=" + dutyTime2c
				+ ", dutyTime3s=" + dutyTime3s + ", dutyTime3c=" + dutyTime3c + ", dutyTime4s=" + dutyTime4s
				+ ", dutyTime4c=" + dutyTime4c + ", dutyTime5s=" + dutyTime5s + ", dutyTime5c=" + dutyTime5c
				+ ", dutyTime6s=" + dutyTime6s + ", dutyTime6c=" + dutyTime6c + ", dutyTime7s=" + dutyTime7s
				+ ", dutyTime7c=" + dutyTime7c + ", dutyTime8s=" + dutyTime8s + ", dutyTime8c=" + dutyTime8c + ", hpid="
				+ hpid + ", wgs84Lon=" + wgs84Lon + ", wgs84Lat=" + wgs84Lat + "]";
	}
	
}
