package br.senai.sc.ti2014n1.daniel.dwgames.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Cliente;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Compra;
import br.senai.sc.tii20141n1.pw4.daniel.dwgames.model.dominio.Produto;

public class CompraDao extends Dao {

	private final String INSERT = "INSERT INTO compra (quantidade,produtoid,clienteid) VALUES (?,?,?)";
	private final String UPDATE = "UPDATE compra SET quantidade = ? , produtoid = ? , clienteid = ? WHERE id = ?";
	private final String DELETE = "DELETE FROM compra WHERE id = ?";
	private final String SELECT = "SELECT * FROM compra";
	private final String SELECT_ID = "SELECT * FROM compra WHERE id = ?";

	private ProdutoDao produtoDao;
	private ClienteDao clienteDao;

	public CompraDao() {
		produtoDao = new ProdutoDao();
		clienteDao = new ClienteDao();

	}

	public void salvar(Compra compra) throws Exception {
		try {
			getConnection().setAutoCommit(false);
			Produto produto = produtoDao.salvar(compra.getProduto());
			compra.setProduto(produto);

			Cliente cliente = clienteDao.salvar(compra.getCliente());
			compra.setCliente(cliente);

			if (compra.getId() == 0) {
				inserir(compra);
			} else {
				alterar(compra);
			}

			getConnection().commit();
		} catch (Exception e) {
			getConnection().rollback();
			throw e;
		} finally {
			getConnection().setAutoCommit(true);
		}
	}

	private void inserir(Compra compra) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(INSERT);
			ps.setInt(1, compra.getQuantidade());
			ps.setLong(2, compra.getProduto().getId());
			ps.setLong(3, compra.getCliente().getId());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar cadastrar o compra");
		}
	}

	public void alterar(Compra compra) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(UPDATE);
			ps.setInt(1, compra.getQuantidade());
			ps.setLong(2, compra.getProduto().getId());
			ps.setLong(3, compra.getCliente().getId());

			ps.setLong(4, compra.getId());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o update: da compra" + e);
		}

	}

	public void excluir(Long id) throws Exception {
		Compra compra = buscarPorId(id);
		if (compra == null) {
			return;
		}

		try {
			getConnection().setAutoCommit(false);

			PreparedStatement ps = getConnection().prepareStatement(DELETE);
			ps.setLong(1, id);
			ps.executeUpdate();

			if (compra.getProduto() != null) {
				produtoDao.excluir(compra.getProduto().getId());
				if (compra.getCliente() != null) {
					clienteDao.excluir(compra.getCliente().getId());
				}
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

	public List<Compra> listarTodos() {
		List<Compra> compras = new ArrayList<Compra>();
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Compra compra = parseCompra(rs);
				compras.add(compra);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select da compra: " + e);
		}
		return compras;
	}

	private Compra parseCompra(ResultSet rs) throws SQLException {
		Compra compra = new Compra();
		compra.setQuantidade(rs.getInt("quantidade"));
		compra.setId(rs.getLong("id"));
		compra.setProduto(getProduto(rs));
		compra.setCliente(getCliente(rs));
		return compra;

	}

	private Cliente getCliente(ResultSet rs) throws SQLException {
		Long idCliente = rs.getLong("clienteid");
		return clienteDao.buscarPorId(idCliente);
	}

	private Produto getProduto(ResultSet rs) throws SQLException {
		Long idProduto = rs.getLong("produtoid");
		return produtoDao.buscarPorId(idProduto);
	}

	public Compra buscarPorId(Long id) {
		Compra compra = null;
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT_ID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				compra = parseCompra(rs);
			}

			ps.close();
			return compra;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select do compra: " + e);
		}
		return null;
	}
}