package ssg.middlepj.pharmafinder.dto;

import java.io.Serializable;

public class PharmaProductManagementDto implements Serializable{

	private int id;
	private int storeId;
	private int productId;
	private int inputPrice;
	private int outputPrice;
	private String createdAt;

	public PharmaProductManagementDto() {

	}

	public PharmaProductManagementDto(int id, int storeId, int productId, int inputPrice, int outputPrice,
			String createdAt) {
		this.id = id;
		this.storeId = storeId;
		this.productId = productId;
		this.inputPrice = inputPrice;
		this.outputPrice = outputPrice;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "PharmaProductManagementDto [id=" + id + ", storeId=" + storeId + ", productId=" + productId
				+ ", inputPrice=" + inputPrice + ", outputPrice=" + outputPrice + ", createdAt=" + createdAt + "]";
	}



}
