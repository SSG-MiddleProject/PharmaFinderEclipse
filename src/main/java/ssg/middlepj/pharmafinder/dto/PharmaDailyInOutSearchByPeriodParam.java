package ssg.middlepj.pharmafinder.dto;

public class PharmaDailyInOutSearchByPeriodParam{

	private int storeId;
	private String startDate;
	private String endDate;

	public PharmaDailyInOutSearchByPeriodParam() {
		// TODO Auto-generated constructor stub
	}

	public PharmaDailyInOutSearchByPeriodParam(int storeId, String startDate, String endDate) {
		super();
		this.storeId = storeId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "PharmaDailyInOutSearchByPeriodParam [storeId=" + storeId + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}


}
