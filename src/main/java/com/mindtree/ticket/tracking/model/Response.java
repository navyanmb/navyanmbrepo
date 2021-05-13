package com.mindtree.ticket.tracking.model;

public class Response {

	private int status_code;
	private String message;

	public Response() {
	}

	public Response(int status_code, String message) {
		super();
		this.status_code = status_code;
		this.message = message;
	}

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response [status_code=" + status_code + ", message=" + message + "]";
	}
    
}
