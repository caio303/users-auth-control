package br.caio303.users_auth_control.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

public class Error {
	private String message;
	private Integer statusCode;
	private String path;
	private Instant timestamp;
	
	public Error(String message, Integer statusCode, String path, Instant timestamp) {
		this.message = message;
		this.statusCode = statusCode;
		this.path = path;
		this.timestamp = timestamp;
	}
	
	public Error(StatusCodeException e, HttpServletRequest req) {
		this.message = e.getMessage();
		this.path = req.getRequestURI();
		this.timestamp = Instant.now();
		this.statusCode = e.getStatus().value();
	}

	public Error(Exception e, HttpServletRequest req) {
		this.message = e.getMessage();
		this.path = req.getRequestURI();
		this.timestamp = Instant.now();
		this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

	public String getMessage() {
		return message;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public String getPath() {
		return path;
	}
	public Instant getTimestamp() {
		return timestamp;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
}