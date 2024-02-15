package ssg.middlepj.pharmafinder.dto;

public class PharmaProductWithProductDto {
	// PharmaProduct
	private int id;
	private int storeId;
	private int productId;
	private int inputPrice;
	private int outputPrice;
	private String createdAt;
	
	// productId
    // private Integer id;
    private String itemName;
    private String itemImage;
    
    public PharmaProductWithProductDto() {
    	
    }
    
	public PharmaProductWithProductDto(int id, int storeId, int productId, int inputPrice, int outputPrice,
			String createdAt, String itemName, String itemImage) {
		this.id = id;
		this.storeId = storeId;
		this.productId = productId;
		this.inputPrice = inputPrice;
		this.outputPrice = outputPrice;
		this.createdAt = createdAt;
		this.itemName = itemName;
		this.itemImage = itemImage;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
    
	public String getItemImage() {
		return this.itemImage;
	}
	
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
}
