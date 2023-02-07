package br.caio303.RESTapi.dtos;

import br.caio303.RESTapi.models.UserModel;

public class LoginResponseDto {
	private UserModel authenticatedUser;
	private String token;
	
	
	public LoginResponseDto(UserModel user) {
		this.authenticatedUser = user;
	}
	
	public LoginResponseDto(UserModel authenticatedUser, String token) {
		this.authenticatedUser = authenticatedUser;
		this.token = token;
	}

	public LoginResponseDto() {}

	public UserModel getAuthenticatedUser() {
		return authenticatedUser;
	}
	public String getToken() {
		return token;
	}
	public void setAuthenticatedUser(UserModel authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}
	public void setToken(String token) {
		this.token = token;
	}
}
