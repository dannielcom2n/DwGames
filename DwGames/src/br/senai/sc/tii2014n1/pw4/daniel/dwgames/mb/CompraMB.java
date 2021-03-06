package br.senai.sc.tii2014n1.pw4.daniel.dwgames.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.senai.sc.ti2014n1.daniel.dwgames.model.CompraRn;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Cliente;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Compra;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Produto;

@ManagedBean
public class CompraMB {
	private List<Compra> compras;
	private Compra compra;
	private CompraRn rn;

	@ManagedProperty(value = "#{sessaoMB}")
	private SessaoMB sessaoMB;

	@PostConstruct
	public void init() {
		rn = new CompraRn();
		compra = new Compra();
		compra.setProduto(new Produto());
		compra.setCliente(new Cliente());
	}

	public List<Compra> getCompras() {
		if (compras == null) {
			compras = rn.listar();
		}
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public SessaoMB getSessaoMB() {
		return sessaoMB;
	}

	public void setSessaoMB(SessaoMB sessaoMB) {
		this.sessaoMB = sessaoMB;
	}

	public String salvar() {
		try {
			System.out.println("Usu�rio Logado: " + sessaoMB.getNomeUsuarioLogado());
			compra.getProduto();
			compra.getCliente();
			rn.salvar(compra);

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "/index";
	}

	public String excluir(String idParam) {
		Long id = Long.parseLong(idParam);
		try {
			rn.excluir(id);
			compras = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String editar(String idParam) {
		Long id = Long.parseLong(idParam);
		compra = rn.buscarPorId(id);
		return "compraform";
	}

}
