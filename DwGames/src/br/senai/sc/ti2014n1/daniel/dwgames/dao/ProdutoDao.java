package br.senai.sc.ti2014n1.daniel.dwgames.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Produto;

public class ProdutoDao extends Dao {

	private final String INSERT = "INSERT INTO produto (codigoDeBarras,nome,valor,marca,quantidade,descricao) VALUES (?,?,?,?,?,?)";
	private final String UPDATE = "UPDATE produto SET codigoDeBarras = ?, nome = ?, valor = ?, marca = ?, quantidade = ?,descricao = ? WHERE id = ?";
	private final String DELETE = "DELETE FROM produto WHERE id = ?";
	private final String SELECT = "SELECT * FROM produto";
	private final String SELECT_ID = "SELECT * FROM produto WHERE id = ?";

	public void salvar(Produto produto) throws Exception {
		if (produto.getId() == 0) {
			inserir(produto);
		} else {
			alterar(produto);
		}

	}

	private void inserir(Produto produto) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(INSERT);
			ps.setInt(1, produto.getCodigoDeBarras());
			ps.setString(2, produto.getNome());
			ps.setDouble(3, produto.getValor());
			ps.setString(4, produto.getMarca());
			ps.setInt(5, produto.getQuantidade());
			ps.setString(6, produto.getDescricao());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar cadastrar o produto");
		}
	}

	public void alterar(Produto produto) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(UPDATE);
			ps.setInt(1, produto.getCodigoDeBarras());
			ps.setString(2, produto.getNome());
			ps.setDouble(3, produto.getValor());
			ps.setString(4, produto.getMarca());
			ps.setInt(5, produto.getQuantidade());
			ps.setString(6, produto.getDescricao());
			ps.setLong(7, produto.getId());

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

	public List<Produto> listarTodos() {
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Produto produto = parseProduto(rs);
				produtos.add(produto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select do produto: " + e);
		}
		return produtos;
	}

	private Produto parseProduto(ResultSet rs) throws SQLException {
		Produto produto = new Produto();
		produto.setCodigoDeBarras(rs.getInt("codigoDeBarras"));
		produto.setNome(rs.getString("nome"));
		produto.setValor(rs.getDouble("valor"));
		produto.setMarca(rs.getString("marca"));
		produto.setQuantidade(rs.getInt("quantidade"));
		produto.setMarca(rs.getString("descricao"));

		produto.setId(rs.getLong("id"));
		return produto;

	}

	public Produto buscarPorId(Long id) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT_ID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Produto produto = parseProduto(rs);
				return produto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select do produto: " + e);
		}
		return null;
	}

}
