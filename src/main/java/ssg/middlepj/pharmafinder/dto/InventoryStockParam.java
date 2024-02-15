package ssg.middlepj.pharmafinder.dto;

public class InventoryStockParam {

	private int storeProductId;
	private String currentDate;

	public InventoryStockParam() {
		// TODO Auto-generated constructor stub
	}

	public InventoryStockParam(int storeProductId, String currentDate) {
		super();
		this.storeProductId = storeProductId;
		this.currentDate = currentDate;
	}

	public int getStoreProductId() {
		return storeProductId;
	}

	public void setStoreProductId(int storeProductId) {
		this.storeProductId = storeProductId;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	@Override
	public String toString() {
		return "InventoryStockParam [storeProductId=" + storeProductId + ", currentDate=" + currentDate + "]";
	}

}
