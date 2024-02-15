
package message;

public class ResultMsg {
	
	public static final String SUCCESS_MSG = "SUCCESS";
	public static final String FAIL_MSG = "FAIL";
	
	private boolean isSuccess;
	private String msg;
	private String errorMsg;
	
	public ResultMsg() {
		
	}
	
	public ResultMsg(boolean isSuccess) {
		setIsSuccess(isSuccess);
	}
	
	public ResultMsg(boolean isSuccess, String errorMsg) {
		setIsSuccess(isSuccess);
		this.errorMsg = errorMsg;
	}



	public boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
		
		if(isSuccess) {
			msg = SUCCESS_MSG;
		}else {
			msg = FAIL_MSG;
		}
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String getMsg() {
		return msg;
	}
}