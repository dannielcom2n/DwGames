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

	public void salvar(User user) throws Exception {

		if (user.getNome().trim().isEmpty()) {
			throw new Exception("O nome é obrigatorio");

		}

		if (user.getEmail().trim().isEmpty()) {
			throw new Exception("O email é obrigatorio");

		}

		if (user.getSenha().trim().isEmpty()) {
			throw new Exception("A senha é obrigatoria");

		}
		
		dao.salvar(user);
	}
	
	public User buscarPorId(Long id) {
		return dao.buscarPorId(id);
	}

	public void excluir(Long id) throws Exception {
		dao.excluir(id);
	}

}
