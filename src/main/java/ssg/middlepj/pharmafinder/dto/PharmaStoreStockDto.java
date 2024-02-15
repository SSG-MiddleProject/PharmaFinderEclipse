package ssg.middlepj.pharmafinder.dto;

public class PharmaStoreStockDto {

	private int id;
	private int storeId;
	private int productId;
	private int quantity;

	public PharmaStoreStockDto() {
		// TODO Auto-generated constructor stub
	}

	public PharmaStoreStockDto(int storeId, int productId) {
		this.storeId = storeId;
		this.productId = productId;
	}


	public PharmaStoreStockDto(int id, int storeId, int productId, int quantity) {
		super();
		this.id = id;
		this.storeId = storeId;
		this.productId = productId;
		this.quantity = quantity;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "PharmaStoreStockDto [id=" + id + ", storeId=" + storeId + ", productId=" + productId + ", quantity="
				+ quantity + "]";
	}



}
