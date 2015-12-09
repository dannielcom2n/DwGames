package br.senai.sc.ti2014n1.daniel.dwgames.model;

import java.sql.SQLException;
import java.util.List;

import com.sun.javafx.collections.SetAdapterChange;

import br.senai.sc.ti2014n1.daniel.dwgames.dao.UserDao;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.User;
import br.senai.sc.tii2014n1.pw4.daniel.dwgames.mb.SessaoMB;

public class UserRn {
	private UserDao dao;

	public UserRn() {
		dao = new UserDao();
	}

	public User buscaPorEmail(String email) {
		return dao.buscaPorEmail(email);
	}

	public void salvar(User user) throws Exception {

		if (validar(user)) {
			dao.salvar(user);
		}

	}

	public boolean validar(User user) throws Exception {

		if (user.getNome().trim().isEmpty()) {
			throw new Exception("O nome é obrigatorio");

		}

		if (user.getEmail().trim().isEmpty()) {
			throw new Exception("O email é obrigatorio");

		}

		if (user.getSenha().trim().isEmpty()) {
			throw new Exception("A senha é obrigatoria");

		}
		
		return true;

	}

	public List<User> listar() {
		return dao.listarTodos();
	}

	public User buscarPorId(Long id) throws SQLException {
		return dao.buscarPorId(id);
	}

	public void excluir(Long id) throws Exception {
		dao.excluir(id);
	}

}
