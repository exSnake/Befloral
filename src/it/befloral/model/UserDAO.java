package it.befloral.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.befloral.beans.UserBean;

public class UserDAO implements GenericDAO<UserBean> {
	public static final String TABLE_NAME = "users";
	private static DataSource ds;

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
	public Collection<UserBean> doRetrieveAll(String order)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBean doRetriveByKey(int code) throws SQLException {
		String sql = "SELECT * FROM " + UserDAO.TABLE_NAME + " WHERE id = ?";
		UserBean bean = new UserBean();
		try(var conn = ds.getConnection()) {
			try(var stmt = conn.prepareStatement(sql)){
				stmt.setInt(1, code);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					bean.setId(rs.getInt("id"));
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("password"));
					bean.setRole(rs.getString("role"));
					bean.setActive(rs.getBoolean("active"));
				}
			}
			
		} 
		return bean;
	}

	@Override
	public void doSave(UserBean dao) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " (email, password, role, active) VALUES (?, ?, ?, ?) ";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, dao.getEmail());
				stmt.setString(2, dao.getPassword());
				stmt.setString(3, dao.getRole());
				stmt.setBoolean(4, dao.isActive());
				stmt.execute();
			}
		}
	}

	@Override
	public int doUpdate(UserBean dao) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public UserBean doRetriveByUsername(String username) throws SQLException {
		String sql = "SELECT * FROM " + UserDAO.TABLE_NAME + " WHERE email = ?";
		UserBean bean = new UserBean();
		try(var conn = ds.getConnection()) {
			try(var stmt = conn.prepareStatement(sql)){
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					bean.setId(rs.getInt("id"));
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("password"));
					bean.setRole(rs.getString("role"));
					bean.setActive(rs.getBoolean("active"));
				}
			}
		} 
		return bean;
	}

}
