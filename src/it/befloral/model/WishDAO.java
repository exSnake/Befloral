package it.befloral.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;

import it.befloral.beans.Product;
import it.befloral.beans.User;
import it.befloral.beans.Wish;

public class WishDAO implements GenericDAO<Wish> {
	private static DataSource ds;
	private static final String TABLE_NAME = "wishs";

	@Override
	public Collection<Wish> doRetrieveAll(String order) throws SQLException {
		Collection<Wish> wishs = new LinkedList<>();
		var sql = "SELECT w.id as wid, w.uid,w.pid, w.price ad wprice FROM " + TABLE_NAME + " w LEFT JOIN products p ON p.id=w.pid" + " ORDER BY " + order;
		var product = new Product();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					var rw = new Wish();
					rw.setId(rs.getInt("wid"));
					rw.setUid(rs.getInt("uid"));
					product.setId(rs.getInt("pid"));
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setShortDescription(rs.getString("shortDescription"));
					product.setMetaDescription(rs.getString("metaDescription"));
					product.setMetaKeyword(rs.getString("metaKeyword"));
					product.setPrice(rs.getDouble("price"));
					product.setWeight(rs.getDouble("weight"));
					product.setAvailable(rs.getBoolean("available"));
					product.setDiscount(rs.getDouble("discount"));
					product.setOnSale(rs.getInt("onSale"));
					product.setQuantity(rs.getInt("quantity"));
					rw.setProd(product);
					rw.setPrice(rs.getDouble("wprice"));
					wishs.add(rw);
				}
			}
		}
		return wishs;
	}

	public Collection<Wish> doRetrieveByProduct(String order, Product prod) throws SQLException {
		Collection<Wish> wishs = new LinkedList<>();
		var sql = "SELECT * FROM " + TABLE_NAME + " WHERE pid = ? ORDER BY " + order;
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, prod.getId());
				System.out.println(stmt);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					var rw = new Wish();
					rw.setId(rs.getInt("id"));
					rw.setUid(rs.getInt("uid"));
					rw.setProd(prod);
					rw.setPrice(rs.getDouble("price"));
					wishs.add(rw);
				}
			}
		}
		return wishs;
	}
// join e inserire product rinominare le tabelle Servlet WishList
	public Collection<Wish> doRetrieveByUser(String order, User user) throws SQLException {
		Collection<Wish> wishs = new LinkedList<>();
		var sql = "SELECT * FROM " + TABLE_NAME + " WHERE uid = ? ORDER BY " + order;
		var pdao = new ProductDAO();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, user.getId());
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					var rw = new Wish();
					rw.setId(rs.getInt("id"));
					rw.setProd(pdao.doRetriveByKey(rs.getInt("pid")));
					rw.setPrice(rs.getDouble("price"));
					rw.setUid(rs.getInt("pid"));
					wishs.add(rw);
				}
			}
		}
		return wishs;
	}

	@Override
	public Wish doRetriveByKey(int code) throws SQLException {
		var sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
		Wish rw = null;
		var pdao = new ProductDAO();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, code);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					rw = new Wish();
					rw.setId(rs.getInt("id"));
					rw.setProd(pdao.doRetriveByKey(rs.getInt("pid")));
					rw.setUid(rs.getInt("uid"));
					rw.setPrice(rs.getDouble("price"));
				}
			}
		}
		return rw;
	}

	@Override
	public void doSave(Wish dao) throws SQLException {
		var sql = "INSERT INTO " + TABLE_NAME + " (`pid`, `uid`, `price`) VALUES (?, ?, ?)";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(sql)) {
				stmt.setInt(1, dao.getId());
				stmt.setInt(2, dao.getUid());
				stmt.setInt(3, dao.getProd().getId());
				stmt.setDouble(4, dao.getPrice());
				stmt.executeUpdate();
			}
		}
	}

	@Override
	public int doUpdate(Wish dao) throws SQLException {
		return 0;
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		var deleteSQL = "DELETE * FROM " + TABLE_NAME + "WHERE id = ?";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(deleteSQL)) {
				stmt.setInt(1, code);
				return stmt.executeUpdate() > 0;
			}
		}
	}

}
