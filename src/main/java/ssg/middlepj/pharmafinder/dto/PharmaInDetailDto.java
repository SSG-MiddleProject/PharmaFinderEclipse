package ssg.middlepj.pharmafinder.dto;

import java.io.Serializable;

public class PharmaInDetailDto implements Serializable{

	private int id;
	private int dailyInputId;
	private int storeProductId;
	private int inputCnt;
	private String itemName;
	private int inputPrice;
	private int productId;

	public PharmaInDetailDto() {

	}

	public PharmaInDetailDto(int id, int dailyInputId, int storeProductId, int inputCnt, String itemName,
			int inputPrice, int productId) {
		super();
		this.id = id;
		this.dailyInputId = dailyInputId;
		this.storeProductId = storeProductId;
		this.inputCnt = inputCnt;
		this.itemName = itemName;
		this.inputPrice = inputPrice;
		this.productId = productId;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getInputPrice() {
		return inputPrice;
	}

	public void setInputPrice(int inputPrice) {
		this.inputPrice = inputPrice;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "PharmaInDetailDto [id=" + id + ", dailyInputId=" + dailyInputId + ", storeProductId=" + storeProductId
				+ ", inputCnt=" + inputCnt + ", itemName=" + itemName + ", inputPrice=" + inputPrice +", productId="+productId +"]";
	}

}
