package br.senai.sc.ti2014n1.daniel.dwgames.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {
	private Connection conn;

	public Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");

				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost/dwGames", "root", "");
				System.out.println("Conexão com o banco estabelecida");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

}
