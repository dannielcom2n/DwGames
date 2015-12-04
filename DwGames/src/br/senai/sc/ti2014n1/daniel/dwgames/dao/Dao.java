package br.senai.sc.ti2014n1.daniel.dwgames.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.faces.context.FacesContext;

public class Dao {
	private Connection conn;

	String servidor = FacesContext.getCurrentInstance().getExternalContext()
			.getInitParameter("servidor");
	String banco = FacesContext.getCurrentInstance().getExternalContext()
			.getInitParameter("banco");
	String usuario = FacesContext.getCurrentInstance().getExternalContext()
			.getInitParameter("usuario");
	String senha = FacesContext.getCurrentInstance().getExternalContext()
			.getInitParameter("senha");

	public Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");

				conn = DriverManager.getConnection("jdbc:mysql://" + servidor
						+ "/" + banco, usuario, senha);
				System.out.println("Conexão com o banco estabelecida");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

}
