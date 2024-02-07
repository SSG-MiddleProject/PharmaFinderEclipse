package ssg.middlepj.pharmafinder.dto;

import java.io.Serializable;

public class PharmaProductManagementParam implements Serializable {

	private int storeId;
	private Integer productId;
	private String itemName;
	
	public PharmaProductManagementParam() {
		
	}
	
	public PharmaProductManagementParam(int storeId, Integer productId, String itemName) {
		this.itemName = itemName;
		this.storeId = storeId;
		this.productId = productId;
	}



	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
}
