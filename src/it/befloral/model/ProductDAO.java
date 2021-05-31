package it.befloral.model;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.xdevapi.Result;

import it.befloral.beans.*;

public class ProductDAO implements GenericDAO<Product> {
	private static DataSource ds;
	private static final String TABLE_NAME = "products";
	private static final Logger LOGGER = LogManager.getLogger();

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
	public synchronized Collection<Product> doRetrieveAll(String order) throws SQLException {
		Collection<Product> products = new LinkedList<>();
		String selectSQL = "SELECT s.*, c.name AS cname,c.id AS cid, c.description AS cdescription ,c.metaKeywords AS cmetaKeywords"
				+ "	 FROM ( " + TABLE_NAME +  " AS s  LEFT JOIN categories_products cp ON cp.pid=s.id )"
				+ "		 LEFT JOIN categories c ON cp.cid=c.id";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try(var connection = ds.getConnection()) {
			try (var preparedStatement = connection.prepareStatement(selectSQL)) {
				ResultSet rs = preparedStatement.executeQuery();
				if (!rs.next())
					return products;	
				do {
					Product bean = new Product();
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
					if (rs.getString("cname") != null) {
						do {
							Category c = new Category();
							c.setId(rs.getInt("cid"));
							c.setName("cname");
							c.setDescription(rs.getString("cdescription"));
							c.setMetaKeywords(rs.getString("cmetaKeywords"));
							bean.addCategory(c);
						} while (rs.next() && rs.getInt("id") == bean.getId());
					}
					else {
						rs.next();
					}
					products.add(bean);				
				} while (!rs.isAfterLast());
			}
		}
		return products;
	}

	@Override
	public synchronized Product doRetriveByKey(int code) throws SQLException {
		Product bean = new Product();
		ReviewDAO rdao = new ReviewDAO();
		String selectSQL = "SELECT s.*, c.name AS cname,c.id AS cid, c.description AS cdescription ,c.metaKeywords AS cmetaKeywords"
				+ "	 FROM ( " + TABLE_NAME +  " AS s  LEFT JOIN categories_products cp ON cp.pid=s.id )"
				+ "		 LEFT JOIN categories c ON cp.cid=c.id WHERE s.id=?";
		
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				stmt.setInt(1, code);
				LOGGER.debug(stmt);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
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
					if (rs.getString("cname") != null) {
						do {
							Category c = new Category();
							c.setId(rs.getInt("cid"));
							c.setName("cname");
							c.setDescription(rs.getString("cdescription"));
							c.setMetaKeywords(rs.getString("cmetaKeywords"));
							bean.addCategory(c);
						} while (rs.next());
					}
				}
			}
		}
		bean.setReviewes(rdao.doRetrieveByProduct("id", bean));
		return bean;
	}

	// Create or insert user
	@Override
	public synchronized void doSave(Product dao) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;

		String inserSQL = "INSERT INTO " + ProductDAO.TABLE_NAME + " (name,description,shortDescription,"
				+ "metaDescription,metaKeyword,price,weight,available,"
				+ "discount,onSale,quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String updateSQL = "INSERT INTO categories_products (pid,cid) VALUES (?,?) ON DUPLICATE KEY UPDATE pid=pid";
		String deleteSQL_Category="Delete * from categories_products WHERE pid=?";
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(inserSQL,Statement.RETURN_GENERATED_KEYS);
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
			LOGGER.debug(stmt);
			stmt.execute();
			ResultSet res  = stmt.getGeneratedKeys();
			int key;
			if (res.next()) {
				key = res.getInt(1);
				for (Category c : dao.getCategories()) {
					stmt2 = conn.prepareStatement(updateSQL);
					stmt2.setInt(1, key);
					stmt2.setInt(2, c.getId());

					LOGGER.debug(stmt2);
					stmt2.execute();
				}
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if(conn != null) conn.rollback();
		}
			finally {
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
	public synchronized int doUpdate(Product dao) throws SQLException {
		String updateSQL = "UPDATE products p  " + "SET p.name = ?," + "p.description = ?," + "p.shortDescription = ?,"
				+ "p.metaDescription = ?," + "p.metaDescription = ?," + "p.price = ?," + "p.weight = ?,"
				+ "p.available = ?," + "p.discount = ?," + "p.onSale = ?," + "p.quantity = ? " + "WHERE p.id = ? ";
		String inserSQL = "INSERT INTO categories_products (pid,cid) VALUES (?,?) ON DUPLICATE KEY UPDATE pid=pid";
		String deleteSQL_Category="Delete * from categories_products WHERE pid=?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		var result = 0;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);	
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
			
			stmt.executeUpdate();
			
			stmt2 = conn.prepareStatement(deleteSQL_Category);
			stmt2.execute();					
				for (Category c : dao.getCategories()) {
					stmt3 = conn.prepareStatement(inserSQL);
					stmt3.setInt(1, dao.getId());
					stmt3.setInt(2, c.getId());
					stmt3.execute();
				}	
				conn.commit();
			}catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} 
		finally {
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
