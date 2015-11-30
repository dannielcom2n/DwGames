package br.senai.sc.tii2014n1.pw4.daniel.dwgames.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import br.senai.sc.ti2014n1.daniel.dwgames.model.UserRn;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.User;

@ManagedBean
public class UserMB {
	private List<User> users;
	private User user;
	private UserRn rn;

	@PostConstruct
	public void init() {
		rn = new UserRn();
		user = new User();
	}

	public List<User> getUsers() {
		if (users == null) {
			users = rn.listar();
		}
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setCliente(User user) {
		this.user = user;
	}

	public String salvar() {
		try {
			rn.salvar(user);
		} catch (Exception e) {
			return "";
		}
		return "/login";
	}

	public String excluir(String idParam) {
		Long id = Long.parseLong(idParam);
		try {
			rn.excluir(id);
			users = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String editar(String idParam) {
		Long id = Long.parseLong(idParam);
		user = rn.buscarPorId(id);
		return "userform";
	}

}
