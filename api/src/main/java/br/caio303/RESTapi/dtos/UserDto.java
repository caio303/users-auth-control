package br.caio303.RESTapi.dtos;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
	
	@NotBlank(message = "O 'nome' deve ser informado")
	@Size(max = 70)
	private String nome;
	
	@NotBlank(message = "O 'cpf' deve ser informado")
	@Pattern(regexp = "^[0-9]{11}$",message = "O 'cpf' deve estar no formato: 00000000000")
	private String cpf;
	
	@NotBlank(message = "O 'email' deve ser informado")
	@Email
	@Size(max = 70)
	private String email;
	
	@NotBlank(message = "A 'senha' deve ser informada")
	private String senha;
	
	@NotNull(message = "A 'dataNasc' deve ser informada")
	private Date dataNasc;

	@Size(max = 300)
	private String biografia;
	

	public UserDto(String nome, String cpf, String email, 
			String senha, String biografia, Date dataNasc) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.dataNasc = dataNasc;
		this.biografia = biografia;
	}
		
	public UserDto() {}

	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}
	public String getBiografia() {
		return biografia;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setBiografia(String descricao) {
		this.biografia = descricao;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
}