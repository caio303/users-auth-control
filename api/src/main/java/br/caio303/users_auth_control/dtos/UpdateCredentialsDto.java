package br.caio303.users_auth_control.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateCredentialsDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max = 70)
	private String nome;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(max = 300)
	private String biografia;
	
	@NotBlank
	private String senha;
	
	@NotBlank
	@Size(min = 10, max = 10)
	private Date dataNasc;

	public UpdateCredentialsDto(String nome, String email, 
			String biografia, String senha, Date dataNasc) {
		this.nome = nome;
		this.email = email;
		this.biografia = biografia;
		this.senha = senha;
		this.dataNasc = dataNasc;
	}
	
	public UpdateCredentialsDto() {}

	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public String getBiografia() {
		return biografia;
	}
	public String getSenha() {
		return senha;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setBiografia(String descricao) {
		this.biografia = descricao;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
}