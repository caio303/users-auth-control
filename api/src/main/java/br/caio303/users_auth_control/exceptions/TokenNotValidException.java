package br.caio303.users_auth_control.exceptions;

import org.springframework.http.HttpStatus;

public class TokenNotValidException extends StatusCodeException {
	private static final long serialVersionUID = 1L;

	public TokenNotValidException() {
		super(HttpStatus.UNAUTHORIZED, "The token used is not valid, you don't have the necessary permissions.");
	}
}
