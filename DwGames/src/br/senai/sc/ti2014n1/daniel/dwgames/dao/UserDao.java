package br.senai.sc.ti2014n1.daniel.dwgames.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.User;


public class UserDao extends Dao {

	private static final String SELECT_EMAIL = "SELECT * FROM user WHERE email = ?";
	private final String INSERT = "INSERT INTO user (nome, email, senha) values (?,?,?)";
	private final String SELECT_ID = "SELECT * FROM user WHERE id = ?";
	private final String UPDATE = "UPDATE user SET nome = ?,  email = ?, senha = ? WHERE id = ?";
	private final String DELETE = "DELETE FROM user WHERE id = ?";
	private final String SELECT = "SELECT * FROM user";
	
	public void salvar(User user) throws Exception {
		if (user.getId() == 0) {
			inserir(user);
		} else {
			alterar(user);
		}
	}
	
	private void inserir(User user) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(INSERT);
			ps.setString(1, user.getNome());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getSenha());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar salvar o usu�rio");
		}
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

	private User parseUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setEmail(rs.getString("email"));
		user.setSenha(rs.getString("senha"));
		user.setAdmin(rs.getBoolean("admin"));

		return user;

	}
	
	public User buscarPorId(Long id) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT_ID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setNome(rs.getString("nome"));
				user.setEmail(rs.getString("email"));
				user.setSenha(rs.getString("senha"));
				user.setId(rs.getLong("id"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select de user: " + e);
		}
		return null;
	}
	
	public void alterar(User user) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(UPDATE);
			ps.setString(1, user.getNome());
			ps.setString(2, user.getSenha());
			ps.setString(3, user.getEmail());
			ps.setLong(4, user.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
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
	
	public List<User> listarTodos() {
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setNome(rs.getString("nome"));
				user.setEmail(rs.getString("email"));
				user.setSenha(rs.getString("senha"));
				user.setId(rs.getLong("id"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select de user: " + e);
		}
		return users;
	}
	
}
