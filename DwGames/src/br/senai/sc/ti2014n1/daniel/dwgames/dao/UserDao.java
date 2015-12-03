package br.senai.sc.ti2014n1.daniel.dwgames.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.User;

public class UserDao extends Dao {

	private final String SELECT_EMAIL = "SELECT * FROM user WHERE email = ?";
	private final String INSERT = "INSERT INTO user (nome, email, senha) values (?,?,?)";
	private final String UPDATE = "UPDATE user SET nome = ?,  email = ?, senha = ? WHERE id = ?";
	private final String SELECT_ID = "SELECT * FROM user WHERE id = ?";
	private final String DELETE = "DELETE FROM user WHERE id = ?";
	private final String SELECT = "SELECT * FROM user";

	public User salvar(User user) throws Exception {
		try {
			if (user.getId() != null && user.getId() > 0) {
				alterar(user);
			} else {
				inserir(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao salvar o user");
		}
		return user;
	}

	public void alterar(User user) throws SQLException {

		PreparedStatement ps = getConnection().prepareStatement(UPDATE);
		ps.setString(1, user.getNome());
		ps.setString(2, user.getSenha());
		ps.setString(3, user.getEmail());
		ps.setLong(4, user.getId());

		ps.executeUpdate();

	}

	private void inserir(User user) throws Exception {

		PreparedStatement ps = getConnection().prepareStatement(INSERT,
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, user.getNome());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getSenha());
		ps.executeUpdate();

		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			Long novoId = rs.getLong(1);
			user.setId(novoId);
		}

	}

	public User buscarPorId(Long id) throws SQLException {

		PreparedStatement ps = getConnection().prepareStatement(SELECT_ID);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return parseUser(rs);
		}
		return null;
	}

	private User parseUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setEmail(rs.getString("email"));
		user.setSenha(rs.getString("senha"));
		user.setAdmin(rs.getBoolean("admin"));

		return user;

	}

	public void excluir(Long id) throws SQLException {
		PreparedStatement ps = getConnection().prepareStatement(DELETE);
		ps.setLong(1, id);
		ps.executeUpdate();
	}

	public User buscaPorEmail(String email) {
		try {
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(SELECT_EMAIL);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return parseUser(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<User> listarTodos() {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = parseUser(rs);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select de user: " + e);
		}
		return users;
	}

}
