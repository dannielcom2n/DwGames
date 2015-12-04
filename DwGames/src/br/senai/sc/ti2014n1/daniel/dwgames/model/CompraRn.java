package br.senai.sc.ti2014n1.daniel.dwgames.model;

import java.util.List;

import br.senai.sc.ti2014n1.daniel.dwgames.dao.CompraDao;

import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Compra;

public class CompraRn {
	private CompraDao dao;

	public CompraRn() {
		dao = new CompraDao();
	}

	public void salvar(Compra compra) throws Exception {
		if (compra.getQuantidade() == 0) {
			throw new Exception("A quantidade é obrigatorio");

		}
		dao.salvar(compra);
	}

	public List<Compra> listar() {
		return dao.listarTodos();
	}

	public Compra buscarPorId(Long id) {
		return dao.buscarPorId(id);
	}

	public void excluir(Long id) throws Exception {
		dao.excluir(id);
	}
}
