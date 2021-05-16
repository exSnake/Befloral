package it.befloral.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class IsDao<T> implements GenericDAO<T> {

	private String TABLE_NAME;

	protected static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/befloral");
		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	public IsDao(String name_table) {
		this.TABLE_NAME = name_table;
	}

	public synchronized T doRetriveByKey(int code) throws SQLException {

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE (id = ? ) ";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, code);
			result = stmt.executeQuery(sql);

			T temp = null;
			if (result.next()) {
				temp = getParam(result);
			}
			return temp;

		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}

	}

	public synchronized Collection<T> doRetrieveAll(String order) throws SQLException {
		ArrayList<T> searchResults = new ArrayList();

		String selectSQL = "SELECT * FROM " + TABLE_NAME;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(selectSQL);
			result = stmt.executeQuery(selectSQL);

			while (result.next()) {
				searchResults.add(getParam(result));
			}

		} finally {
			if (result != null)
				result.close();
			if (stmt != null)
				stmt.close();
		}

		return searchResults;
	}

	public synchronized void doSave(T dao) throws SQLException {

		T valueObject = dao;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {
			conn = ds.getConnection();
			// Insert into unica per ogni DAO
			stmt = conn.prepareStatement(inserIntoSQL());
			// Imposta i parametri del DAO
			setParam(dao, stmt);

			stmt.execute();

		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

	public synchronized int doUpdate(T dao) throws SQLException {

		T valueObject = dao;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		boolean flag;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(updateSQL());

			setParam(valueObject, stmt);

			flag = stmt.execute();

		} finally {
			if (stmt != null)
				stmt.close();
		}

		return (flag == true ? 1 : 0);
	}

	public synchronized boolean doDelete(int code) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		boolean flag;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(deleteSQL());
			stmt.setInt(1, code);

			flag = stmt.execute();

		} finally {
			if (stmt != null)
				stmt.close();
		}
		return flag;
	}

	// TO IMPLEMENT
	protected abstract T getParam(ResultSet result) throws SQLException;

	protected abstract void setParam(T dao, PreparedStatement stmt) throws SQLException;

	protected abstract String inserIntoSQL();

	protected abstract String updateSQL();

	protected abstract String deleteSQL();
}
