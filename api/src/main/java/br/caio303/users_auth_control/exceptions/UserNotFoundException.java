package br.caio303.users_auth_control.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends StatusCodeException {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String searchedCpf) {
		super(HttpStatus.NOT_FOUND, "User not found by cpf '"+searchedCpf+"'");
	}
}