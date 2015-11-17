package br.senai.sc.tii2014n1.pw4.daniel.dwgames.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.senai.sc.ti2014n1.daniel.dwgames.model.ProdutoRn;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Produto;

@ManagedBean
public class ProdutoMB {
	private List<Produto> produtos;
	private Produto produto;
	private ProdutoRn rn;

	@PostConstruct
	public void init() {
		rn = new ProdutoRn();
		produto = new Produto();
	}

	public List<Produto> getProdutos() {
		if (produtos == null) {
			produtos = rn.listar();
		}
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String salvar() {
		try {
			rn.salvar(produto);
		} catch (Exception e) {
			return "";
		}
		return "produtolist";
	}

	public String excluir(String idParam) {
		Long id = Long.parseLong(idParam);
		try {
			rn.excluir(id);
			produtos = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String editar(String idParam) {
		Long id = Long.parseLong(idParam);
		produto = rn.buscarPorId(id);
		return "produtoform";
	}

}
