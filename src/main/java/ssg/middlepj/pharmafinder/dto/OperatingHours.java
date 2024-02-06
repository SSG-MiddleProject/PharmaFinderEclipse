package ssg.middlepj.pharmafinder.dto;

public class OperatingHours {
	
	private String start;	// 운영 시작시간
    private String end;		// 운영 종료시간
    
    public OperatingHours() {
		// TODO Auto-generated constructor stub
	}

	public OperatingHours(String start, String end) {
		super();
		this.start = start;
		this.end = end;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "OperatingHours [start=" + start + ", end=" + end + "]";
	}   
    
}
