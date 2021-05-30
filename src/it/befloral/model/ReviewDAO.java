package it.befloral.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.befloral.beans.Product;
import it.befloral.beans.Review;
import it.befloral.beans.User;

public class ReviewDAO implements GenericDAO<Review>{
	
	private static DataSource ds;
	private static final String TABLE_NAME = "reviewes";

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
	public Collection<Review> doRetrieveAll(String order) throws SQLException {
		Collection<Review> reviewes = new LinkedList<>();
		var sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + order;
		var pdao = new ProductDAO();
		var udao = new UserDAO();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					var rw = new Review();
					rw.setId(rs.getInt("id"));
					rw.setScore(rs.getInt("score"));
					rw.setTitle(rs.getString("title"));
					rw.setBody(rs.getString("body"));
					rw.setProduct(pdao.doRetriveByKey(rs.getInt("pid")));
					rw.setUser(udao.doRetriveByKey(rs.getInt("uid")));
					rw.setReply(rs.getString("reply"));
					reviewes.add(rw);
				}
			}
		}
		return reviewes;
	}
	
	public Collection<Review> doRetrieveByUser(String order, User user) throws SQLException {
		Collection<Review> reviewes = new LinkedList<>();
		var sql = "SELECT * FROM " + TABLE_NAME + " WHERE uid = ? ORDER BY " + order;
		var pdao = new ProductDAO();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, user.getId());
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					var rw = new Review();
					rw.setId(rs.getInt("id"));
					rw.setScore(rs.getInt("score"));
					rw.setTitle(rs.getString("title"));
					rw.setBody(rs.getString("body"));
					rw.setProduct(pdao.doRetriveByKey(rs.getInt("pid")));
					rw.setUser(user);
					rw.setReply(rs.getString("reply"));
					reviewes.add(rw);
				}
			}
		}
		return reviewes;
	}
	
	public Collection<Review> doRetrieveByProduct(String order, Product prod) throws SQLException {
		Collection<Review> reviewes = new LinkedList<>();
		var sql = "SELECT * FROM " + TABLE_NAME + " WHERE pid = ? ORDER BY " + order;
		var udao = new UserDAO();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, prod.getId());
				System.out.println(stmt);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					var rw = new Review();
					rw.setId(rs.getInt("id"));
					rw.setScore(rs.getInt("score"));
					rw.setTitle(rs.getString("title"));
					rw.setBody(rs.getString("body"));
					rw.setProduct(prod);
					rw.setUser(udao.doRetriveByKey(rs.getInt("uid")));
					rw.setReply(rs.getString("reply"));
					reviewes.add(rw);
				}
			}
		}
		return reviewes;
	}
	
	

	@Override
	public Review doRetriveByKey(int code) throws SQLException {
		var sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
		Review rw = null;
		var pdao = new ProductDAO();
		var udao = new UserDAO();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, code);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					rw = new Review();
					rw.setId(rs.getInt("id"));
					rw.setScore(rs.getInt("score"));
					rw.setTitle(rs.getString("title"));
					rw.setBody(rs.getString("body"));
					rw.setProduct(pdao.doRetriveByKey(rs.getInt("pid")));
					rw.setUser(udao.doRetriveByKey(rs.getInt("uid")));
					rw.setReply(rs.getString("reply"));
				}
			}
		}
		return rw;
	}

	@Override
	public void doSave(Review rw) throws SQLException {
		var sql = "INSERT INTO " + TABLE_NAME + " (`pid`, `uid`, `score`, `title`, `body`, `reply`) VALUES (?, ?, ?, ?, ?, ?)";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, rw.getProduct().getId());
				stmt.setInt(2, rw.getUser().getId());
				stmt.setInt(3, rw.getScore());
				stmt.setString(4, rw.getTitle());
				stmt.setString(5, rw.getBody());
				stmt.setString(6, rw.getReply());
				stmt.executeUpdate();
			}
		}
	}

	@Override
	public int doUpdate(Review rw) throws SQLException {
		var sql = "UPDATE FROM " + TABLE_NAME + " SET `score` = ?, `title` = ?, `body` = ?, `reply` = ? WHERE id = ?";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, rw.getScore());
				stmt.setString(2, rw.getTitle());
				stmt.setString(3, rw.getBody());
				stmt.setString(4, rw.getReply());
				stmt.setInt(5, rw.getId());
				return stmt.executeUpdate();
			}
		}
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		var sql = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, code);
				return stmt.executeUpdate() > 0;
			}
		}
	}

}
