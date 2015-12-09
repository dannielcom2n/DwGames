package br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -2888395855507609855L; 
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private Boolean admin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

}