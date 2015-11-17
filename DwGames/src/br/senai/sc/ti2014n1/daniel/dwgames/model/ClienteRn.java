package br.senai.sc.ti2014n1.daniel.dwgames.model;

import java.util.List;

import br.senai.sc.ti2014n1.daniel.dwgames.dao.ClienteDao;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Cliente;

public class ClienteRn {

	private ClienteDao dao;

	public ClienteRn() {
		dao = new ClienteDao();
	}

	public void salvar(Cliente cliente) throws Exception {
		if (cliente.getNome().trim().isEmpty()) {
			throw new Exception("O nome é obrigatorio");

		}
		dao.salvar(cliente);
	}

	public List<Cliente> listar() {
		return dao.listarTodos();
	}

	public Cliente buscarPorId(Long id) {
		return dao.buscarPorId(id);
	}

	public void excluir(Long id) throws Exception {
		dao.excluir(id);
	}
}
