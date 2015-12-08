package br.senai.sc.ti2014n1.daniel.dwgames.model;

import java.util.List;

import br.senai.sc.ti2014n1.daniel.dwgames.dao.CompraDao;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Compra;

public class CompraRn {
	private CompraDao dao;

	ProdutoRn produtoRn = new ProdutoRn();
	ClienteRn clienteRn = new ClienteRn();

	public CompraRn() {
		dao = new CompraDao();
	}

	public void salvar(Compra compra) throws Exception {
		if (produtoRn.validar(compra.getProduto())) {

		}
		if (clienteRn.validar(compra.getCliente())) {

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
