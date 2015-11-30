package br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio;

public class Cliente {
	private long id;
	private String nome;
	private int telefone;
	private String sexo;
	private User user;
	private Cliente cliente;

	public Cliente() {
		user = new User();
	}

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		
	}

}
