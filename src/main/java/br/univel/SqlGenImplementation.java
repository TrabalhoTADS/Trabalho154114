package br.univel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class SqlGenImplementation {

	private Connection con;

	public SqlGenImplementation() throws SQLException {

		startConnection();

	}

	private void startConnection() throws SQLException {

		String url = "jdbc:h2:D:\\Documentos\\Documents\\Branco154114";
		String user = "admin";
		String pass = "admin";
		/*
		 * String url =
		 * "jdbc:h2:C:/Users/ehdfreitas/Desktop/Banco de dados/banco"; String
		 * user = "sa"; String pass = "sa";
		 */
		setCon(DriverManager.getConnection(url, user, pass));

	}

	private void CloseConnection() throws SQLException {
		getCon().close();
	}

	protected String getCreateTable(Connection con, Object obj) {

		String sql = "CREATE TABLE produto(id INT PRIMARY KEY, descricao VARCHAR(255), preco double);";

		return sql.toString(); // Cria a String
	}

	protected PreparedStatement getSqlInsert(Connection con, Object obj) {

		String sql = "";
		if (obj.getClass() == Produto.class) {
			sql = "Insert into produto (id, descricao, preco) values (?, ?, ?);";
		} else if (obj.getClass() == Cliente.class) {
			sql = "Insert into produto (id, descricao, preco) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		}

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ps;
	}

	protected PreparedStatement getSqlSelectAll(Connection con, Object obj) {
		String sql = "";

		if (obj.getClass() == Produto.class) {
			sql = "SELECT * FROM  produto;";
		} else if (obj.getClass() == Cliente.class) {
			sql = "SELECT * FROM cliente;";
		}

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ps;

	}

	protected PreparedStatement getSqlSelectById(Connection con, Object obj) {
		String sql = "";

		if (obj.getClass() == Produto.class) {
			Produto p = (Produto) obj;
			sql = "SELECT * FROM  produto WHERE id = " + p.getId() + ";";
		} else if (obj.getClass() == Cliente.class) {
			Cliente c = (Cliente) obj;
			sql = "SELECT * FROM cliente WHERE id = " + c.getId() + ";";
		}

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ps;
	}

	protected PreparedStatement getSqlUpdateById(Connection con, Object obj) {
		String sql = "";

		if (obj.getClass() == Produto.class) {
			Produto p = (Produto) obj;
			sql = "UPDATE produto set descricao = '"
			    + p.getDescricao()
			    + "', preco = "
				+ p.getPreco()
				+ " WHERE id = "
				+ p.getId() + ";";
		} else if (obj.getClass() == Cliente.class){
			Cliente c = (Cliente) obj;
			sql = "SELECT * FROM cliente WHERE id = " + c.getId() + ";";
		}

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ps;
	}

	protected PreparedStatement getSqlDeleteById(Connection con, Object obj) {
		String sql = "";

		if (obj.getClass() == Produto.class) {
			Produto p = (Produto) obj;
			sql = "DELETE FROM produto WHERE id = " + p.getId() + ";";
		} else if (obj.getClass() == Cliente.class){
			Cliente c = (Cliente) obj;
			sql = "DELETE FROM cliente WHERE id = " + c.getId() + ";";
		}

		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ps;

	}

	public Connection getCon() {
		if (con == null) {
			try {
				startConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
}
