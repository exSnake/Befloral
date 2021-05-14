package it.befloral.model;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.befloral.beans.*;

public class ProductDAO implements GenericDAO<ProductBean> {
	private static DataSource ds;
	private static final String TABLE_NAME = "products";

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/befloral");
		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	@Override
	public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + ProductDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setDescription(rs.getString("description"));
				bean.setShortDescription(rs.getString("shortDescription"));
				bean.setMetaDescription(rs.getString("metaDescription"));
				bean.setMetaKeyword(rs.getString("metaKeyword"));
				bean.setPrice(rs.getDouble("price"));
				bean.setWeight(rs.getDouble("weight"));
				bean.setAvailable(rs.getBoolean("available"));
				bean.setDiscount(rs.getDouble("discount"));
				bean.setOnSale(rs.getInt("onSale"));
				bean.setQuantity(rs.getInt("quantity"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
	}

	@Override
	public synchronized ProductBean doRetriveByKey(int code) throws SQLException {
		ProductBean bean = new ProductBean();
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ? ";
		try(var conn = ds.getConnection()) {
			try(var stmt = conn.prepareStatement(selectSQL)){
				stmt.setInt(1, code);
	
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					bean.setId(rs.getInt("id"));
					bean.setName(rs.getString("name"));
					bean.setDescription(rs.getString("description"));
					bean.setShortDescription(rs.getString("shortdescription"));
					bean.setMetaDescription(rs.getString("metadescription"));
					bean.setMetaKeyword(rs.getString("metakeyword"));
					bean.setPrice(rs.getDouble("price"));
					bean.setWeight(rs.getDouble("weight"));
					bean.setAvailable(rs.getBoolean("available"));
					bean.setDiscount(rs.getDouble("discount"));
					bean.setOnSale(rs.getInt("onsale"));
					bean.setQuantity(rs.getInt("quantity"));
			}
			}
		}
		return bean;
	}

	// Create or insert user
	@Override
	public synchronized void doSave(ProductBean dao) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		String inserSQL = "INSERT INTO " + ProductDAO.TABLE_NAME + " (name,description,shortDescription,"
				+ "metaDescription,metaKeyword,price,weight,available,"
				+ "discount,onSale,quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(inserSQL);

			stmt.setString(1, dao.getName());
			stmt.setString(2, dao.getDescription());
			stmt.setString(3, dao.getShortDescription());
			stmt.setString(4, dao.getMetaDescription());
			stmt.setString(5, dao.getMetaKeyword());
			stmt.setDouble(6, dao.getPrice());
			stmt.setDouble(7, dao.getWeight());
			stmt.setBoolean(8, dao.isAvailable());
			stmt.setDouble(9, dao.getDiscount());
			stmt.setInt(10, dao.getOnSale());
			stmt.setInt(11, dao.getQuantity());
			stmt.execute();

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				if (conn != null)
					conn.close();
			}
		}

	}

	@Override
	public synchronized int doUpdate(ProductBean dao) throws SQLException {
		String updateSQL = "UPDATE products p  "
				+ "SET p.name = ?,"
				+ "p.description = ?,"
				+ "p.shortDescription = ?,"
				+ "p.metaDescription = ?,"
				+ "p.metaDescription = ?,"
				+ "p.price = ?,"
				+ "p.weight = ?,"
				+ "p.available = ?,"
				+ "p.discount = ?,"
				+ "p.onSale = ?,"
				+ "p.quantity = ? "
				+ "WHERE p.id = ? ";
		Connection conn = null;
		PreparedStatement stmt = null;
		var result=0;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(updateSQL);

			stmt.setString(1, dao.getName());
			stmt.setString(2, dao.getDescription());
			stmt.setString(3, dao.getShortDescription());
			stmt.setString(4, dao.getMetaDescription());
			stmt.setString(5, dao.getMetaKeyword());
			stmt.setDouble(6, dao.getPrice());
			stmt.setDouble(7, dao.getWeight());
			stmt.setBoolean(8, dao.isAvailable());
			stmt.setDouble(9, dao.getDiscount());
			stmt.setInt(10, dao.getOnSale());
			stmt.setInt(11, dao.getQuantity());
			stmt.setInt(12, dao.getId());
			result=stmt.executeUpdate();

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				if (conn != null)
					conn.close();
			}
		}

		return result;
	}

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductDAO.TABLE_NAME + " WHERE id = ?";

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(deleteSQL);
			stmt.setInt(1, code);

			result = stmt.executeUpdate();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				if (conn != null)
					conn.close();
			}
		}

		return (result != 0);
	}
	
}
