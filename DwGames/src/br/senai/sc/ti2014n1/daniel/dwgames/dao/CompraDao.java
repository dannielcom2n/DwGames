package br.senai.sc.ti2014n1.daniel.dwgames.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Compra;

public class CompraDao extends Dao {

	private final String INSERT = "INSERT INTO compra (quantidade) VALUES (?)";
	private final String UPDATE = "UPDATE compra SET quantidade = ? WHERE id = ?";
	private final String DELETE = "DELETE FROM compra WHERE id = ?";
	private final String SELECT = "SELECT * FROM compra";
	private final String SELECT_ID = "SELECT * FROM compra WHERE id = ?";

	public void salvar(Compra compra) throws Exception {
		if (compra.getId() == 0) {
			inserir(compra);
		} else {
			alterar(compra);
		}

	}

	private void inserir(Compra compra) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(INSERT);
			ps.setInt(1, compra.getQuantidade());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar fazer a compra");
		}

	}

	public void alterar(Compra compra) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(UPDATE);
			ps.setInt(1, compra.getQuantidade());
			ps.setLong(2, compra.getId());

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

	public List<Compra> listarTodos() {
		List<Compra> compras = new ArrayList<Compra>();
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Compra compra = parseCompra(rs);
				compras.add(compra);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select da compra: " + e);
		}
		return compras;
	}

	private Compra parseCompra(ResultSet rs) throws SQLException {
		Compra compra = new Compra();
		compra.setQuantidade(rs.getInt("quantidade"));
		compra.setId(rs.getLong("id"));
		return compra;

	}

	public Compra buscarPorId(Long id) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT_ID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Compra compra = parseCompra(rs);
				return compra;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select da compra: " + e);
		}
		return null;
	}

}