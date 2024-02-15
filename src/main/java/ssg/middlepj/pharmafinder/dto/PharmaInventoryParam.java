package ssg.middlepj.pharmafinder.dto;

public class PharmaInventoryParam {

	private int storeId;
	private int productId;
	private String currentDate;
	private String itemName;

	public PharmaInventoryParam() {
		// TODO Auto-generated constructor stub
	}

	public PharmaInventoryParam(int storeId, int productId, String currentDate, String itemName) {
		super();
		this.storeId = storeId;
		this.productId = productId;
		this.currentDate = currentDate;
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

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "PharmaInventoryParam [storeId=" + storeId + ", productId=" + productId + ", currentDate=" + currentDate
				+ ", itemName=" + itemName + "]";
	}

}
