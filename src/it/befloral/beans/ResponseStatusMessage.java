package it.befloral.beans;

public class ResponseStatusMessage {
	private int statusCode;
	private String message;
	
	
	/**
	 * @param statusCode
	 * @param message
	 */
	public ResponseStatusMessage(int statusCode, String message) {
		super();
		this.setStatusCode(statusCode);
		this.message = message;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
