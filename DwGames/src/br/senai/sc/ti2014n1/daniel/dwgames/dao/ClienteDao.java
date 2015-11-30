package br.senai.sc.ti2014n1.daniel.dwgames.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Cliente;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.User;

public class ClienteDao extends Dao {

	private final String SELECT_EMAIL = "SELECT * FROM user WHERE email = ?";
	private final String INSERT = "INSERT INTO cliente (nome,telefone,sexo) VALUES (?,?,?)";
	private final String UPDATE = "UPDATE cliente SET nome = ?, telefone = ?, sexo = ? WHERE id = ?";
	private final String DELETE = "DELETE FROM cliente WHERE id = ?";
	private final String SELECT = "SELECT * FROM cliente";
	private final String SELECT_ID = "SELECT * FROM cliente WHERE id = ?";

	public void salvar(Cliente cliente) throws Exception {
		if (cliente.getId() == 0) {
			inserir(cliente);
		} else {
			alterar(cliente);
		}

	}

	private void inserir(Cliente cliente) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(INSERT);
			ps.setString(1, cliente.getNome());
			ps.setInt(2, cliente.getTelefone());
			ps.setString(3, cliente.getSexo());
			ps.setLong(4, cliente.getUser().getId());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar cadastrar o cliente");
		}
	}

	public void alterar(Cliente cliente) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(UPDATE);
			ps.setString(1, cliente.getNome());
			ps.setInt(2, cliente.getTelefone());
			ps.setString(3, cliente.getSexo());
			ps.setLong(4, cliente.getUser().getId());
			ps.setLong(5, cliente.getId());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o update: " + e);
		}

	}

	public void excluir(Long id) throws Exception {
		try {

			PreparedStatement ps = getConnection().prepareStatement(DELETE);
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro a executar o delete: " + e);
			throw new Exception("Erro ao tentar excluir");
		}
	}

	public List<Cliente> listarTodos() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cliente cliente1 = new Cliente();
				Cliente cliente = parseCliente(rs);
				
				ClienteDao clienteDao = DAOFactory.getClienteDao();
				cliente1.setUser(clienteDao.buscarPorId(rs.getLong("id")));
				
				clientes.add(cliente);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select do cliente: " + e);
		}
		return clientes;
	}

	private Cliente parseCliente(ResultSet rs) throws SQLException {
		User user = new User();
		UserDao user = new UserDao(); 
		Cliente cliente = new Cliente();
		cliente.setNome(rs.getString("nome"));
		cliente.setTelefone(rs.getInt("telefone"));
		cliente.setSexo(rs.getString("sexo"));
		cliente.setId(rs.getLong("id"));
		return cliente;

	}

	public Cliente buscarPorId(Long id) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT_ID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Cliente cliente = parseCliente(rs);
				return cliente;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select do cliente: " + e);
		}
		return null;
	}

	public Cliente buscaPorEmail(String email) {
		try {
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(SELECT_EMAIL);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return parseCliente(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
