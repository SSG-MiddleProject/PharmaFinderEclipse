
package ssg.middlepj.pharmafinder.dto;

import java.io.Serializable;

public class PharmaProductManagementParam implements Serializable {

	private int storeId;
	private int productId;
	private String itemName;
	
	public PharmaProductManagementParam() {
		
	}
	
	public PharmaProductManagementParam(int storeId) {
		this.storeId = storeId;
	}
	
	public PharmaProductManagementParam(int storeId, int productId, String itemName) {
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	
}