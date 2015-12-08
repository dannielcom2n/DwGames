package br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio;

import java.io.Serializable;

public class Cliente implements Serializable {
	private static final long serialVersionUID = -2888395855507609855L; 
	private long id;
	private int telefone;
	private String sexo;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
