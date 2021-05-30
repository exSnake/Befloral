/**
 * 
 */
package it.befloral.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.befloral.beans.Address;
import it.befloral.beans.User;

/**
 * @author exSna
 *
 */
public class AddressDAO implements GenericDAO<Address> {
	
	private static DataSource ds;
	public static final String TABLE_NAME = "addresses";

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
	public Collection<Address> doRetrieveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address doRetriveByKey(int code) throws SQLException {
		String sql = "SELECT "
				+ "	  a.id AS aid, a.firstName AS aFirstName, a.lastName AS aLastName, a.address, a.postalCode, a.city, a.province, a.phone, a.info, a.alias, a.preferred"
				+ "   FROM " + TABLE_NAME + " a"
				+ "   WHERE a.id=?";
		Address addr = new Address();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, code);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
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
				}
			}
		}
		return addr;
	}

	@Override
	public void doSave(Address dao) throws SQLException {
		String sql = "INSERT INTO addresses (firstName, lastName, address, postalCode, city, province, phone, info, alias, preferred"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try(var conn = ds.getConnection()) {
			try(var stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, dao.getFirstName());
				stmt.setString(2, dao.getLastName());
				stmt.setString(3, dao.getAddress());
				stmt.setString(4, dao.getPostalCode());
				stmt.setString(5, dao.getCity());
				stmt.setString(6, dao.getProvince());
				stmt.setString(7, dao.getPhone());
				stmt.setString(8, dao.getInfo());
				stmt.setString(9, dao.getAlias());
				stmt.setBoolean(10, false);
				
				stmt.execute();
			}
		}
	}

	@Override
	public int doUpdate(Address dao) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
