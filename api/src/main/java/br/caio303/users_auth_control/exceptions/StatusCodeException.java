package br.caio303.users_auth_control.exceptions;

import org.springframework.http.HttpStatus;

public abstract class StatusCodeException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	
	public StatusCodeException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}

	public StatusCodeException(HttpStatus status, String message, Exception cause) {
		super(message, cause);
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}