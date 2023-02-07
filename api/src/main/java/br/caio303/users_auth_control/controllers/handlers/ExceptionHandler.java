package br.caio303.users_auth_control.controllers.handlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import br.caio303.users_auth_control.exceptions.Error;
import br.caio303.users_auth_control.exceptions.StatusCodeException;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(StatusCodeException.class)
	public ResponseEntity<Error> handleStatusCodeException(StatusCodeException e, HttpServletRequest req) {
		return ResponseEntity.status(e.getStatus()).body(new Error(e, req));
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<Error> handleException(Exception e, HttpServletRequest req) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e, req));
	}
}