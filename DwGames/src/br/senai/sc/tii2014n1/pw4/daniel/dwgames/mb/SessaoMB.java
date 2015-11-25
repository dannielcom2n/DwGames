package br.senai.sc.tii2014n1.pw4.daniel.dwgames.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.senai.sc.ti2014n1.daniel.dwgames.model.UserRn;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.User;

@ManagedBean
@SessionScoped
public class SessaoMB {
	private User usuarioLogado;
	private User usuarioForm;

	@PostConstruct
	public void iniciar() {
		setUsuarioForm(new User());

	}

	public User getUsuarioForm() {
		return usuarioForm;
	}

	public void setUsuarioForm(User usuarioForm) {
		this.usuarioForm = usuarioForm;
	}

	public String entrar() {
		UserRn rn = new UserRn();
		User usuarioBanco = rn.buscaPorEmail(usuarioForm.getEmail());

		if (usuarioBanco != null
				&& usuarioForm.getEmail().equalsIgnoreCase(
						usuarioBanco.getEmail())
				&& usuarioForm.getSenha().equals(usuarioBanco.getSenha())) {
			usuarioLogado = usuarioBanco;
			return "/index";
		}
		return "";
	}

	public Boolean estaLogado() {
		return usuarioLogado != null;
	}
	
	public Boolean estaLogadoAdimin() {
		return usuarioLogado != null && usuarioLogado.getAdmin();
	}

	public String sair() {
		usuarioLogado = null;
		return "/index?faces-redirect=true";
	}

}
