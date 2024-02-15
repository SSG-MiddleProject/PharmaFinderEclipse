package ssg.middlepj.pharmafinder.dto;

import java.io.Serializable;
import java.util.List;

public class PharmaInDto extends PharmaInOutDto  {
	
	private int id;
	private int storeId;
	private String currentInputDate;
	private List<PharmaInProductDto> products;
	
	public PharmaInDto() {
		
	}
	
	public PharmaInDto(int id, int storeId, String currentInputDate, List<PharmaInProductDto> products) {
		this.id = id;
		this.storeId = storeId;
		this.currentInputDate = currentInputDate;
		this.products = products;
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

	public String getCurrentInputDate() {
		return currentInputDate;
	}

	public void setCurrentInputDate(String currentInputDate) {
		this.currentInputDate = currentInputDate;
	}

	public List<PharmaInProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<PharmaInProductDto> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "PharmaInDto [id=" + id + ", storeId=" + storeId + ", currentInputDate=" + currentInputDate
				+ ", products=" + products + "]";
	}
	
}
