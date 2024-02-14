package ssg.middlepj.pharmafinder.dto;

import java.io.Serializable;

public class PharmaOutDetailDto implements Serializable{

	private int id;
	private int dailyOutputId;
	private int storeProductId;
	private int outputCnt;
	private String itemName;
	private int outputPrice;

	public PharmaOutDetailDto() {
		// TODO Auto-generated constructor stub
	}

	public PharmaOutDetailDto(int id, int dailyOutputId, int storeProductId, int outputCnt, String itemName,
			int outputPrice) {
		super();
		this.id = id;
		this.dailyOutputId = dailyOutputId;
		this.storeProductId = storeProductId;
		this.outputCnt = outputCnt;
		this.itemName = itemName;
		this.outputPrice = outputPrice;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getOutputPrice() {
		return outputPrice;
	}

	public void setOutputPrice(int outputPrice) {
		this.outputPrice = outputPrice;
	}

	@Override
	public String toString() {
		return "PharmaOutDetailDto [id=" + id + ", dailyOutputId=" + dailyOutputId + ", storeProductId="
				+ storeProductId + ", outputCnt=" + outputCnt + ", itemName=" + itemName + ", outputPrice="
				+ outputPrice + "]";
	}


}
