package br.caio303.users_auth_control.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginCredentialsDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "O 'cpf' deve ser informado")
	@Pattern(regexp = "^[0-9]{11}$",message = "O 'cpf' deve estar no formato: 00000000000")
	private String cpf;
	
	@NotBlank(message = "A 'senha' deve ser informada")
	@Size(max = 70)
	private String senha;
	
	public LoginCredentialsDto(String cpf, String senha) {
		this.cpf = cpf;
		this.senha = senha;
	}
	
	public String getCpf() {
		return cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
