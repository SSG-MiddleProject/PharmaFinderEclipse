package ssg.middlepj.pharmafinder.dto;

import java.io.Serializable;
import java.util.List;

public class PharmaOutDto extends PharmaInOutDto {
	
	private int id;
	private int storeId;
	private String currentOutputDate;
	private List<PharmaOutProductDto> products;
	
	public PharmaOutDto() {
	}

	public PharmaOutDto(int id, int storeId, String currentOutputDate, List<PharmaOutProductDto> products) {
		this.id = id;
		this.storeId = storeId;
		this.currentOutputDate = currentOutputDate;
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

	public String getCurrentOutputDate() {
		return currentOutputDate;
	}

	public void setCurrentOutputDate(String currentOutputDate) {
		this.currentOutputDate = currentOutputDate;
	}

	public List<PharmaOutProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<PharmaOutProductDto> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "PharmaOutDto [id=" + id + ", storeId=" + storeId + ", currentOutputDate=" + currentOutputDate
				+ ", products=" + products + "]";
	}
	
	
}
