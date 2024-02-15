package ssg.middlepj.pharmafinder.dto;

import java.io.Serializable;

public class PharmaOutProductDto implements Serializable{

	private int id;
	private int dailyOutputId;
	private int storeProductId;
	private int outputCnt;

	public PharmaOutProductDto() {
	}

	public PharmaOutProductDto(int id, int dailyOutputId, int storeProductId, int outputCnt) {
		super();
		this.id = id;
		this.dailyOutputId = dailyOutputId;
		this.storeProductId = storeProductId;
		this.outputCnt = outputCnt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDailyOutputId() {
		return dailyOutputId;
	}

	public void setDailyOutputId(int dailyOutputId) {
		this.dailyOutputId = dailyOutputId;
	}

	public int getStoreProductId() {
		return storeProductId;
	}

	public void setStoreProductId(int storeProductId) {
		this.storeProductId = storeProductId;
	}

	public int getOutputCnt() {
		return outputCnt;
	}

	public void setOutputCnt(int outputCnt) {
		this.outputCnt = outputCnt;
	}

	@Override
	public String toString() {
		return "PharmaOutProductDto [id=" + id + ", dailyOutputId=" + dailyOutputId + ", storeProductId="
				+ storeProductId + ", outputCnt=" + outputCnt + "]";
	}


}
