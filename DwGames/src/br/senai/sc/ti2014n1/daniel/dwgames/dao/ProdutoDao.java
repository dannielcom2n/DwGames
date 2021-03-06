package br.senai.sc.ti2014n1.daniel.dwgames.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Produto;

public class ProdutoDao extends Dao {

	private final String INSERT = "INSERT INTO produto (codigoDeBarras,nome,valor,marca,quantidade,descricao,foto) VALUES (?,?,?,?,?,?,?)";
	private final String UPDATE = "UPDATE produto SET codigoDeBarras = ?, nome = ?, valor = ?, marca = ?, quantidade = ?,descricao = ?, foto = ? WHERE id = ?";
	private final String DELETE = "DELETE FROM produto WHERE id = ?";
	private final String SELECT = "SELECT * FROM produto";
	private final String SELECT_ID = "SELECT * FROM produto WHERE id = ?";

	public Produto salvar(Produto produto) throws Exception {
		try {
			if (produto.getId() != null && produto.getId() > 0) {
				alterar(produto);
			} else {
				inserir(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Erro ao salvar o produto");
		}
		return produto;

	}

	private void inserir(Produto produto) throws Exception {

		try {

			PreparedStatement ps = getConnection().prepareStatement(INSERT,
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, produto.getCodigoDeBarras());
			ps.setString(2, produto.getNome());
			ps.setDouble(3, produto.getValor());
			ps.setString(4, produto.getMarca());
			ps.setInt(5, produto.getQuantidade());
			ps.setString(6, produto.getDescricao());
			ps.setString(7, produto.getFoto());

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				Long novoId = rs.getLong(1);
				produto.setId(novoId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o insert do produto: " + e);
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
			ps.setString(7, produto.getFoto());
			ps.setLong(8, produto.getId());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o update: do produto" + e);
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
		produto.setDescricao(rs.getString("descricao"));
		produto.setFoto(rs.getString("foto"));
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
