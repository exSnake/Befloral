/**
 * 
 */
package it.befloral.model;

import java.sql.SQLException;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.befloral.beans.Address;

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
		// TODO Auto-generated method stub
		return null;
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
