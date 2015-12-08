package br.senai.sc.ti2014n1.daniel.dwgames.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Cliente;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.User;

public class ClienteDao extends Dao {

	private final String SELECT_EMAIL = "SELECT * FROM cliente WHERE email = ?";
	private final String INSERT = "INSERT INTO cliente (telefone,sexo, userid) VALUES (?,?,?)";
	private final String UPDATE = "UPDATE cliente SET telefone = ?, sexo = ?, userid = ? WHERE id = ?";
	private final String DELETE = "DELETE FROM cliente WHERE id = ?";
	private final String SELECT = "SELECT * FROM cliente";
	private final String SELECT_ID = "SELECT * FROM cliente WHERE id = ?";

	private UserDao userDao;

	public ClienteDao() {
		userDao = new UserDao();
	}

	public Cliente salvar(Cliente cliente) throws Exception {
		try {
			getConnection().setAutoCommit(false);
			User user = userDao.salvar(cliente.getUser());
			cliente.setUser(user);

			if (cliente.getId() == 0) {
				inserir(cliente);
			} else {
				alterar(cliente);
			}

			getConnection().commit();
		} catch (Exception e) {
			getConnection().rollback();
			throw e;
		} finally {
			getConnection().setAutoCommit(true);
		}
		return cliente;
	}

	private void inserir(Cliente cliente) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(INSERT,
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, cliente.getTelefone());
			ps.setString(2, cliente.getSexo());
			ps.setLong(3, cliente.getUser().getId());
			//ps.setLong(4, cliente.getId());

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				Long novoId = rs.getLong(1);
				cliente.setId(novoId);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar cadastrar o cliente");
		}
	}

	public void alterar(Cliente cliente) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(UPDATE);
			ps.setInt(1, cliente.getTelefone());
			ps.setString(2, cliente.getSexo());
			ps.setLong(3, cliente.getUser().getId());
			ps.setLong(4, cliente.getId());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o update: do cliente" + e);
		}

	}

	public void excluir(Long id) throws Exception {
		Cliente cliente = buscarPorId(id);
		if (cliente == null) {
			return;
		}

		try {
			getConnection().setAutoCommit(false);

			PreparedStatement ps = getConnection().prepareStatement(DELETE);
			ps.setLong(1, id);
			ps.executeUpdate();

			if (cliente.getUser() != null) {
				userDao.excluir(cliente.getUser().getId());
			}

			getConnection().commit();
		} catch (SQLException e) {
			getConnection().rollback();
			e.printStackTrace();
			System.out.println("Erro a executar o delete: " + e);
			throw new Exception("Erro ao tentar excluir");
		} finally {
			getConnection().setAutoCommit(true);
		}
	}

	public List<Cliente> listarTodos() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cliente cliente = parseCliente(rs);
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select de user: " + e);
		}
		return clientes;
	}

	private Cliente parseCliente(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setId(rs.getLong("id"));
		cliente.setTelefone(rs.getInt("telefone"));
		cliente.setSexo(rs.getString("sexo"));
		cliente.setUser(getUser(rs));
		return cliente;

	}

	private User getUser(ResultSet rs) throws SQLException {
		Long idUser = rs.getLong("userid");
		return userDao.buscarPorId(idUser);
	}

	public Cliente buscarPorId(Long id) {
		Cliente cliente = null;
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT_ID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				cliente = parseCliente(rs);
			}

			ps.close();
			return cliente;
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
