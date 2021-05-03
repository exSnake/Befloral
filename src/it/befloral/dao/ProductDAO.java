package it.befloral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.befloral.beans.ProductBean;


public class ProductDAO implements ModelDAO<ProductBean>{
	
	private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");
		}catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	////////////////METHODS////////////////////////////////////////////
	
	private static final String TABLE_NAME = "product";

	@Override
	public synchronized ProductBean doRetriveByKey(int code) throws SQLException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ProductBean bean = new ProductBean();
		
		String selectSQL = "SELECT * FROM "+ ProductDAO.TABLE_NAME + " WERE idproduct = ? "; 
				
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

	@Override
	public synchronized void doSave(ProductBean dao) throws SQLException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String inserSQL = "INSERT INTO " + ProductDAO.TABLE_NAME
				+ " (idproduct,name,description,shortDescription,"
				+ "metaDescription,metaKeyword,price,weight,available,"
				+ "discount,onSale,quantity) VALUES (?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?)";
		
		try {
			
			conn = ds.getConnection();
			stmt = conn.prepareStatement(inserSQL);
			
			stmt.setInt(1, dao.getId());
			stmt.setString(2,dao.getName() );
			stmt.setString(3, dao.getDescription());
			stmt.setString(4, dao.getShortDescription());
			stmt.setString(5, dao.getMetaDescription());
			stmt.setString(6, dao.getMetaKeyword());
			stmt.setDouble(7, dao.getPrice());
			stmt.setBoolean(8, dao.isAvailable());
			stmt.setDouble(9, dao.getDiscount());
			stmt.setInt(10, dao.getOnSale());
			stmt.setInt(11, dao.getQuantity());
			
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
	public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		
		Collection<ProductBean> products = new LinkedList<ProductBean>();
		
		String selectSQL = "SELECT * FROM " + ProductDAO.TABLE_NAME ;
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(selectSQL);
			

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ProductBean bean = new ProductBean();
				
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
			
				products.add(bean);
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
		return products;
	}
		
	@Override
	public synchronized void doUpdate(ProductBean dao) throws SQLException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		doSave(dao);
		
		
	}

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		
		Connection conn = null;
		PreparedStatement stmt = null;

		int result = 0;
		
		String deleteSQL = "DELETE FROM " + ProductDAO.TABLE_NAME + " WHERE idproduct = ?";
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(deleteSQL);
			stmt.setInt(1, code);
		
			result = stmt.executeUpdate();
		} finally {
			try {
				if(stmt!=null)
					stmt.close();			
				
			} finally {
					if(conn!=null)
						conn.close();
				}
		
		
		return (result!=0);
		
		}
	}

	

	
}
