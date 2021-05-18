package it.befloral.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.befloral.beans.Customer;
import it.befloral.beans.User;

public class CustomerDAO implements GenericDAO<Customer> {

	private static DataSource ds;
	public static final String TABLE_NAME = "customers";

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/befloral");
		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	public CustomerDAO() {
	}

	public Customer doRetriveByEmail(String email) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE email = ? ";

		ResultSet result = null;

		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, email);
				result = stmt.executeQuery();
				Customer temp = null;
				if (result.next()) {
					temp = new Customer();
					User user = new User();
					temp.setId(result.getInt("id"));
					temp.setFirstName(result.getString("firstName"));
					temp.setLastName(result.getString("lastName"));
					temp.setGender(result.getInt("gender"));
					temp.setSubscription(result.getBoolean("newsletter"));
					temp.setBirthday(result.getDate("birthday").toLocalDate());
					temp.setUser(null);
					if (result.getString("email") != null) {
						user.setEmail(result.getString("email"));
						user.setPassword(result.getString("password"));
						user.setRole(result.getString("role"));
						user.setActive(result.getBoolean("active"));
					}
				}
				return temp;
			}
		}
	}

	public Collection<Customer> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer doRetriveByKey(int code) throws SQLException {
		String sql = "SELECT * FROM " + TABLE_NAME + "c WHERE id = ? LEFT JOIN " + UserDAO.TABLE_NAME
				+ " u ON u.id = c.uid  ";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, code);
				ResultSet result = stmt.executeQuery();
				Customer temp = null;
				if (result.next()) {
					temp = new Customer();
					User user = new User();
					temp.setId(result.getInt("id"));
					temp.setFirstName(result.getString("firstName"));
					temp.setLastName(result.getString("lastName"));
					temp.setGender(result.getInt("gender"));
					temp.setSubscription(result.getBoolean("newsletter"));
					temp.setBirthday(result.getDate("birthday").toLocalDate());
					temp.setUser(null);
					if (result.getString("email") != null) {
						user.setEmail(result.getString("email"));
						user.setPassword(result.getString("password"));
						user.setRole(result.getString("role"));
						user.setActive(result.getBoolean("active"));
					}
				}
				return temp;
			}
		}
	}

	@Override
	public void doSave(Customer dao) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + " (uid, firstName, lastName, "
				+ "gender, subscription, birthday) VALUES (?, ?, ?, ?, ?, ?) ";
		UserDAO user = new UserDAO();
		user.doSave(dao.getUser());
		User newUser = user.doRetriveByUsername(dao.getUser().getEmail());
		if (newUser == null)
			throw new SQLException("User null");
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, newUser.getId());
				stmt.setString(2, dao.getFirstName());
				stmt.setString(3, dao.getLastName());
				stmt.setInt(4, dao.getGender());
				stmt.setBoolean(5, dao.isSubscripted());
				stmt.setDate(6, java.sql.Date.valueOf(dao.getBirthday()));
				stmt.execute();
			}
		}

	}

	@Override
	public int doUpdate(Customer dao) throws SQLException {
		String sql = "UPDATE " + CustomerDAO.TABLE_NAME + " ( id, fristname, lastname, " + "email, gender, active, "
				+ "newsletter, password, birthday) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		return 0;
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		String sql = "DELETE FROM " + CustomerDAO.TABLE_NAME + " WHERE (id = ? ) ";
		return false;
	}

}
