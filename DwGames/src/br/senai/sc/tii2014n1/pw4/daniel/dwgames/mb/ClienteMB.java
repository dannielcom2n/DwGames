package br.senai.sc.tii2014n1.pw4.daniel.dwgames.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.senai.sc.ti2014n1.daniel.dwgames.model.ClienteRn;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Cliente;

@ManagedBean
public class ClienteMB {
	private List<Cliente> clientes;
	private Cliente cliente;
	private ClienteRn rn;

	@PostConstruct
	public void init() {
		rn = new ClienteRn();
		cliente = new Cliente();
	}

	public List<Cliente> getClientes() {
		if (clientes == null) {
			clientes = rn.listar();
		}
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String salvar() {
		try {
			rn.salvar(cliente);
		}	 catch (Exception e) {
			return "";
		}
		return "/login";
	}

	public String excluir(String idParam) {
		Long id = Long.parseLong(idParam);
		try {
			rn.excluir(id);
			clientes = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String editar(String idParam) {
		Long id = Long.parseLong(idParam);
		cliente = rn.buscarPorId(id);
		return "/clienteform";
	}

}
