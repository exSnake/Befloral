package it.befloral.model;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.befloral.beans.*;
import it.befloral.dao.ProductDAO;

public class ProductModelDS {
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

	public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM " + ProductModelDS.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setId(rs.getInt("idproduct"));
				bean.setName(rs.getString("name"));
				bean.setDescription(rs.getString("description"));
				bean.setShortDescription(rs.getString("shortDescription"));
				bean.setMetaDescription(rs.getString("metaDescription"));
				bean.setMetaKeyword(rs.getString("metaKeyword"));
				bean.setPrice(rs.getDouble("price"));
				bean.setWeight(rs.getString("weight"));
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

	public synchronized ProductBean doRetriveByKey(int code) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE idproduct = ? ";

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(selectSQL);
			stmt.setInt(1, code);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("idProduct"));
				bean.setName(rs.getString("name"));
				bean.setDescription(rs.getString("description"));
				bean.setShortDescription(rs.getString("shortdescription"));
				bean.setMetaDescription(rs.getString("metadescription"));
				bean.setMetaKeyword(rs.getString("metakeyword"));
				bean.setPrice(rs.getDouble("price"));
				bean.setWeight(rs.getString("weight"));
				bean.setAvailable(rs.getBoolean("available"));
				bean.setDiscount(rs.getDouble("discount"));
				bean.setOnSale(rs.getInt("onsale"));
				bean.setQuantity(rs.getInt("quantity"));
			}

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return bean;
	}
}
