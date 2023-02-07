package br.caio303.users_auth_control.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.caio303.users_auth_control.dtos.UserDto;

@Entity(name = "user")
public class UserModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 70)
	private String nome;

	@Column(nullable = false, unique = true, length = 11)
	private String cpf;
	
	@Column(nullable = false, length = 70)
	private String email;
	
	@Column(nullable = true, length = 300)
	private String biografia;
	
	@Column(nullable = false, length = 70)
	@JsonIgnore
	private String senha;
	
	@Column(nullable = false, length = 10)
	@Temporal(TemporalType.DATE)
	private Date dataNasc;
		
	public UserModel(Long id, String nome, String cpf, String email, String biografia, String senha, Date dataNasc) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.biografia = biografia;
		this.senha = senha;
		this.dataNasc = dataNasc;
	}
	
	public UserModel(UserDto userDto) {
		this.nome = userDto.getNome();
		this.cpf = userDto.getCpf();
		this.email = userDto.getEmail();
		this.biografia = userDto.getBiografia();
		this.senha = userDto.getSenha();
		this.dataNasc = userDto.getDataNasc();
	}
	
	public UserModel() {}

	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public String getEmail() {
		return email;
	}
	public String getDescricao() {
		return biografia;
	}
	public String getSenha() {
		return senha;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setId(Long id_user) {
		this.id = id_user;
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
	public void setDescricao(String descricao) {
		this.biografia = descricao;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
}