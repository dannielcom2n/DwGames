package br.senai.sc.ti2014n1.daniel.dwgames.model;

import java.util.List;

import br.senai.sc.ti2014n1.daniel.dwgames.dao.ProdutoDao;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Produto;

public class ProdutoRn {

	private ProdutoDao dao;

	public ProdutoRn() {
		dao = new ProdutoDao();
	}

	public void salvar(Produto produto) throws Exception {

		
		dao.salvar(produto);
	}

	public List<Produto> listar() {
		return dao.listarTodos();
	}

	public Produto buscarPorId(Long id) {
		return dao.buscarPorId(id);
	}

	public void excluir(Long id) throws Exception {
		dao.excluir(id);
	}

	public boolean validar(Produto produto) throws Exception {
		if (produto.getNome().trim().isEmpty()) {
			throw new Exception("O nome é obrigatorio");

		}
		return true;
	}
}
