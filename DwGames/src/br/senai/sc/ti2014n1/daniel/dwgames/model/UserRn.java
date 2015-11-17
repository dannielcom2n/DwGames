package br.senai.sc.ti2014n1.daniel.dwgames.model;

import br.senai.sc.ti2014n1.daniel.dwgames.dao.UserDao;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.User;



public class UserRn {
	private UserDao dao;

	public UserRn() {
		dao = new UserDao();
	}

	public User buscaPorEmail(String email) {
		return dao.buscaPorEmail(email);
	}

}
