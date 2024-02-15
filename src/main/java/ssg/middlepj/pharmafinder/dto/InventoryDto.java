package ssg.middlepj.pharmafinder.dto;

import java.io.Serializable;

public class InventoryDto implements Serializable {

	private String itemName;
	private int productId;
	private int inputCnt;
	private int outputCnt;
	private int inputPrice;
	private int outputPrice;
	private int currentStockCnt;

	public InventoryDto() {
		// TODO Auto-generated constructor stub
	}

	public InventoryDto(String itemName, int productId, int inputCnt, int outputCnt, int inputPrice, int outputPrice,
			int currentStockCnt) {
		super();
		this.itemName = itemName;
		this.productId = productId;
		this.inputCnt = inputCnt;
		this.outputCnt = outputCnt;
		this.inputPrice = inputPrice;
		this.outputPrice = outputPrice;
		this.currentStockCnt = currentStockCnt;
	}



	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getInputCnt() {
		return inputCnt;
	}

	public void setInputCnt(int inputCnt) {
		this.inputCnt = inputCnt;
	}

	public int getOutputCnt() {
		return outputCnt;
	}

	public void setOutputCnt(int outputCnt) {
		this.outputCnt = outputCnt;
	}

	public int getInputPrice() {
		return inputPrice;
	}

	public void setInputPrice(int inputPrice) {
		this.inputPrice = inputPrice;
	}

	public int getOutputPrice() {
		return outputPrice;
	}

	public void setOutputPrice(int outputPrice) {
		this.outputPrice = outputPrice;
	}

	public int getCurrentStockCnt() {
		return currentStockCnt;
	}

	public void setCurrentStockCnt(int currentStockCnt) {
		this.currentStockCnt = currentStockCnt;
	}

	@Override
	public String toString() {
		return "InventoryDto [itemName=" + itemName + ", productId=" + productId + ", inputCnt=" + inputCnt
				+ ", outputCnt=" + outputCnt + ", inputPrice=" + inputPrice + ", outputPrice=" + outputPrice
				+ ", currentStockCnt=" + currentStockCnt + "]";
	}

}
