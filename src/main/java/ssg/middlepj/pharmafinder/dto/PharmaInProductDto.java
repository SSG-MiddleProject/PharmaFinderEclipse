package ssg.middlepj.pharmafinder.dto;

import java.io.Serializable;

public class PharmaInProductDto implements Serializable{

	private int id;
	private int dailyInputId;
	private int storeProductId;
	private int inputCnt;
	
	public PharmaInProductDto() {
	}

	public PharmaInProductDto(int id, int dailyInputId, int storeProductId, int inputCnt) {
		this.id = id;
		this.dailyInputId = dailyInputId;
		this.storeProductId = storeProductId;
		this.inputCnt = inputCnt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDailyInputId() {
		return dailyInputId;
	}

	public void setDailyInputId(int dailyInputId) {
		this.dailyInputId = dailyInputId;
	}

	public int getStoreProductId() {
		return storeProductId;
	}

	public void setStoreProductId(int storeProductId) {
		this.storeProductId = storeProductId;
	}

	public int getInputCnt() {
		return inputCnt;
	}

	public void setInputCnt(int inputCnt) {
		this.inputCnt = inputCnt;
	}

	@Override
	public String toString() {
		return "PharmaInProductDto [id=" + id + ", dailyInputId=" + dailyInputId + ", storeProductId=" + storeProductId
				+ ", inputCnt=" + inputCnt + "]";
	}
	
	
}
