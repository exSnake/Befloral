package it.befloral.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.befloral.beans.Address;
import it.befloral.beans.User;

public class UserDAO implements GenericDAO<User> {
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
	public Collection<User> doRetrieveAll(String order) throws SQLException {
		String sql = "SELECT u.*, "
				+ "   a.id AS aid, a.firstName AS aFirstName, a.lastName AS aLastName, a.address, a.postalCode, a.city, a.province, a.phone, a.info, a.alias, a.preferred "
				+ "   FROM " + TABLE_NAME + " u "
				+ "	  LEFT JOIN addresses a ON a.uid = u.id ORDER BY ?";
		order = order.isEmpty() ? "id" : order;
		var users = new ArrayList<User>();
		User bean = null;
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, order);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					do {
						bean = new User();
						bean.setId(rs.getInt("id"));
						bean.setFirstName(rs.getString("firstName"));
						bean.setLastName(rs.getString("lastName"));
						bean.setGender(rs.getString("gender"));
						bean.setSubscription(rs.getBoolean("subscription"));
						bean.setBirthday(rs.getDate("birthday").toLocalDate());
						bean.setEmail(rs.getString("email"));
						bean.setPassword(rs.getString("password"));
						bean.setRole(rs.getString("role"));
						bean.setActive(rs.getBoolean("active"));
						if(rs.getInt("aid") != 0) {
							do {
								Address addr = new Address();
								addr.setId(rs.getInt("aid"));
								addr.setFirstName(rs.getString("aFirstName"));
								addr.setLastName(rs.getString("aLastName"));
								addr.setAddress(rs.getString("address"));
								addr.setPostalCode(rs.getString("postalCode"));
								addr.setCity(rs.getString("city"));
								addr.setProvince(rs.getString("province"));
								addr.setPhone(rs.getString("phone"));
								addr.setInfo(rs.getString("info"));
								addr.setAlias(rs.getString("alias"));
								addr.setPreferred(rs.getBoolean("preferred"));
								addr.setUser(bean);
								bean.addAddress(addr);
								//if exit is at the end of the stream or it's a new user.
							} while(rs.next() && bean.getId() == rs.getInt("id"));
						} else {
							rs.next();
						}
						users.add(bean);
					} while (!rs.isAfterLast());
				}
			}
		}
		return users;
	}

	@Override
	public User doRetriveByKey(int code) throws SQLException {
		String sql = "SELECT u.*,  "
				+ "	  a.id AS aid, a.firstName AS aFirstName, a.lastName AS aLastName, a.address, a.postalCode, a.city, a.province, a.phone, a.info, a.alias, a.preferred"
				+ "   FROM " + TABLE_NAME + " u"
				+ "	  LEFT JOIN addresses a ON a.uid = u.id WHERE id = ?";
		User bean = new User();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, code);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					bean.setFirstName(rs.getString("firstName"));
					bean.setLastName(rs.getString("lastName"));
					bean.setGender(rs.getString("gender"));
					bean.setSubscription(rs.getBoolean("subscription"));
					bean.setBirthday(rs.getDate("birthday").toLocalDate());
					bean.setId(rs.getInt("id"));
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("password"));
					bean.setRole(rs.getString("role"));
					bean.setActive(rs.getBoolean("active"));
				}
				//in this case there are at least one address
				if(rs.getInt("aid") != 0) {
					do {
						Address addr = new Address();
						addr.setId(rs.getInt("aid"));
						addr.setFirstName(rs.getString("aFirstName"));
						addr.setLastName(rs.getString("aLastName"));
						addr.setAddress(rs.getString("address"));
						addr.setPostalCode(rs.getString("postalCode"));
						addr.setCity(rs.getString("city"));
						addr.setProvince(rs.getString("province"));
						addr.setPhone(rs.getString("phone"));
						addr.setInfo(rs.getString("info"));
						addr.setAlias(rs.getString("alias"));
						addr.setPreferred(rs.getBoolean("preferred"));
						addr.setUser(bean);
						bean.addAddress(addr);
					} while (rs.next());
				}
			}
		}
		return bean;
	}

	@Override
	public void doSave(User dao) throws SQLException {
		String sql = "INSERT INTO " + TABLE_NAME + 
				  "   (firstName, lastName, gender, subscription, birthday, email, password, role, active)"
				+ "   VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, dao.getFirstName());
				stmt.setString(2, dao.getLastName());
				stmt.setString(3, dao.getGender());
				stmt.setBoolean(4, dao.isSubscripted());
				stmt.setDate(5, java.sql.Date.valueOf(dao.getBirthday()));
				stmt.setString(6, dao.getEmail());
				stmt.setString(7, dao.getPassword());
				stmt.setString(8, dao.getRole());
				stmt.setBoolean(9, dao.isActive());
				stmt.execute();
			}
		}
	}

	@Override
	public int doUpdate(User dao) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public User doRetriveByEmail(String email) throws SQLException {
		String sql = "SELECT u.*,  "
				+ "	  a.id AS aid, a.firstName AS aFirstName, a.lastName AS aLastName, a.address, a.postalCode, a.city, a.province, a.phone, a.info, a.alias, a.preferred"
				+ "   FROM " + TABLE_NAME + " u"
				+ "	  LEFT JOIN addresses a ON a.uid = u.id WHERE email = ?";
		User bean = null;
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, email);
				System.out.println(stmt);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					bean = new User();
					bean.setFirstName(rs.getString("firstName"));
					bean.setLastName(rs.getString("lastName"));
					bean.setGender(rs.getString("gender"));
					bean.setSubscription(rs.getBoolean("subscription"));
					bean.setBirthday(rs.getDate("birthday").toLocalDate());
					bean.setId(rs.getInt("id"));
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("password"));
					bean.setRole(rs.getString("role"));
					bean.setActive(rs.getBoolean("active"));
					//in this case there are at least one address
					if(rs.getInt("aid") != 0) {
						do {
							Address addr = new Address();
							addr.setId(rs.getInt("aid"));
							addr.setFirstName(rs.getString("aFirstName"));
							addr.setLastName(rs.getString("aLastName"));
							addr.setAddress(rs.getString("address"));
							addr.setPostalCode(rs.getString("postalCode"));
							addr.setCity(rs.getString("city"));
							addr.setProvince(rs.getString("province"));
							addr.setPhone(rs.getString("phone"));
							addr.setInfo(rs.getString("info"));
							addr.setAlias(rs.getString("alias"));
							addr.setPreferred(rs.getBoolean("preferred"));
							addr.setUser(bean);
							bean.addAddress(addr);
						} while (rs.next());
					}
				}
				
			}
		}
		return bean;
	}

	public void doSaveAddress(User user, Address address) throws SQLException {
		String sql = "INSERT INTO `addresses` "
				+ "   (`uid`, `firstName`, `lastName`, `address`, `postalCode`, `city`, `province`, `phone`, `info`, `alias`, `preferred`)"
				+ "   VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, user.getId());
				stmt.setString(2, address.getFirstName());
				stmt.setString(3, address.getLastName());
				stmt.setString(4, address.getAddress());
				stmt.setString(5, address.getPostalCode());
				stmt.setString(6, address.getCity());
				stmt.setString(7, address.getProvince());
				stmt.setString(8, address.getPhone());
				stmt.setString(9, address.getInfo());
				stmt.setString(10, address.getAlias());
				if(address.isPreferred())
					stmt.setBoolean(11, true);
				else
					stmt.setNull(11, Types.NULL);
				System.out.println(stmt);
				stmt.execute();
			}
		}
	}

	public void doSetPreferredAddress(User user, int id) throws SQLException {
		String sql1 = "UPDATE addresses SET preferred = NULL WHERE uid = ?";
		String sql2 = "UPDATE addresses SET preferred = true WHERE id = ? AND uid = ?";
		var conn = ds.getConnection();
		try {
			conn.setAutoCommit(false);
			try (var stmt = conn.prepareStatement(sql1)) {
				stmt.setInt(1, user.getId());
				stmt.execute();
			}
			try(var stmt2 = conn.prepareStatement(sql2)){
				stmt2.setInt(1, id);
				stmt2.setInt(2, user.getId());
				stmt2.execute();
			}
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		}
	}

}
