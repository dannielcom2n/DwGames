package br.senai.sc.ti2014n1.daniel.dwgames.dao;

public class DAOFactory {

	private static ClienteDao clienteDao;
	

	public static ClienteDao getClienteDao() {
		if (clienteDao == null) {
			clienteDao = new ClienteDao();
		}
		return clienteDao;
	}

}
