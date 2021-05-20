package it.befloral.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.befloral.beans.Order;
import it.befloral.beans.OrderItem;
import it.befloral.beans.User;

public class OrderDAO implements GenericDAO<Order> {
	private static DataSource ds;
	private static final String TABLE_NAME = "orders";

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
	public Collection<Order> doRetrieveAll(String order) throws SQLException {
		Collection<Order> orders = new LinkedList<>();
		String selectSQL = "SELECT * FROM " + TABLE_NAME;
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Order bean = new Order();
					bean.setId(rs.getInt("id"));
					bean.setDestination(rs.getString("destination"));
					bean.setTotalProducts(rs.getInt("totalProducts"));
					bean.setTotalPaid(rs.getDouble("totalPaid"));
					bean.setStatus(rs.getString("status"));
					bean.setGift(rs.getBoolean("gift"));
					bean.setGiftMessage(rs.getString("giftMessage"));
					bean.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
					orders.add(bean);
				}
			}
		}
		return orders;
	}

	@Override
	public Order doRetriveByKey(int code) throws SQLException {
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ? LEFT JOIN order_items s WHERE s.oid = id ";
		Order order = new Order();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				stmt.setInt(1, code);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					order.setId(rs.getInt("id"));
					order.setDestination(rs.getString("destination"));
					order.setTotalProducts(rs.getInt("totalProducts"));
					order.setTotalPaid(rs.getDouble("totalPaid"));
					order.setGift(rs.getBoolean("gift"));
					order.setGiftMessage(rs.getString("giftMessage"));
				}
			}
		}
		return order;
	}

	@Override
	public void doSave(Order dao) throws SQLException {
		String insertOrder = "INSERT INTO orders (`uid`, `destination`, `totalProducts`, `totalPaid`, `trackNumber`, `gift`, `giftMessage`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		String insertItem = "INSERT INTO order_items "
				+ "(`oid`, `name`, `description`, `shortDescription`, `price`, `weight`, `discount`, `quantity`) "
				+ "VALUES (? , ?, ?, ?, ?, ?, ?, ?)";
		var conn = ds.getConnection();
		try {
			var stmt = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, dao.getUser().getId());
			stmt.setString(2, dao.getDestination());
			stmt.setInt(3, dao.getTotalProducts());
			stmt.setDouble(4, dao.getTotalPaid());
			stmt.setString(5, dao.getTrackNumber());
			stmt.setBoolean(6, dao.isGift());
			stmt.setString(7, dao.getGiftMessage());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int lastInsertedId = rs.getInt(1);
				for (OrderItem item : dao.getItems()) {
					var stmt2 = conn.prepareStatement(insertItem);
					stmt2.setInt(1, lastInsertedId);
					stmt2.setString(2, item.getName());
					stmt2.setString(3, item.getDescription());
					stmt2.setString(4, item.getShortDescription());
					stmt2.setDouble(5, item.getPrice());
					stmt2.setDouble(6, item.getWeight());
					stmt2.setDouble(7, item.getDiscount());
					stmt2.setInt(8, item.getQuantity());
					stmt2.execute();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}

	}

	@Override
	public int doUpdate(Order dao) throws SQLException {
		
		String updateOrder = "UPDATE  orders SET(`destination` = ?, `totalProducts` = ?, `totalPaid` =?, `trackNumber` =?, `gift`=?, `giftMessage`=?) "
				+ "WHERE `uid`=? and 'id' = ? ";
		String updateItem = "UPDATE  order_items SET(`name` = ?, `description` = ?, `shortDescription` = ?"
				+ ", `price` = ?, `weight` = ?, `discount` = ?, `quantity`= ? ) "
				+ "WHERE 'id' = ? and 'oid' = ?  ";
		var conn = ds.getConnection();
		
		try {
			var stmt = conn.prepareStatement(updateOrder);
			stmt.setString(1, dao.getDestination());
			stmt.setInt(2, dao.getTotalProducts());
			stmt.setDouble(3, dao.getTotalPaid());
			stmt.setString(4, dao.getTrackNumber());
			stmt.setBoolean(5, dao.isGift());
			stmt.setString(6, dao.getGiftMessage());
			
			stmt.setInt(7,  dao.getUser().getId() );
			stmt.setInt(8, dao.getId());
			
			stmt.executeUpdate();
		
				for (OrderItem item : dao.getItems()) {
					var stmt2 = conn.prepareStatement(updateItem);
					
					stmt2.setString(1, item.getName());
					stmt2.setString(2, item.getDescription());
					stmt2.setString(3, item.getShortDescription());
					stmt2.setDouble(4, item.getPrice());
					stmt2.setDouble(5, item.getWeight());
					stmt2.setDouble(6, item.getDiscount());
					stmt2.setInt(7, item.getQuantity());
					
					stmt2.setInt(8, item.getId());
					stmt2.setInt(9,  dao.getId());
					
					stmt2.execute();
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}	
		
		return 0;
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		String deleteSQL = "DELETE * FROM " + TABLE_NAME + "WHERE id = ?";
		int rs;
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(deleteSQL)) {
				
				stmt.setInt(1,code ); 
				
				rs = stmt.executeUpdate();

		
				return rs>0 ? true : false ;
			}
		}
}
			
	public Collection<Order> doRetriveByUser(User userBean) throws SQLException {
		Collection<Order> orders = new LinkedList<Order>();
		String selectSQL = "SELECT * FROM " + TABLE_NAME;
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Order bean = new Order();
					bean.setId(rs.getInt("id"));
					bean.setDestination(rs.getString("destination"));
					bean.setTotalProducts(rs.getInt("totalProducts"));
					bean.setTotalPaid(rs.getDouble("totalPaid"));
					bean.setStatus(rs.getString("status"));
					bean.setGift(rs.getBoolean("gift"));
					bean.setGiftMessage(rs.getString("giftMessage"));
					bean.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
					orders.add(bean);
				}
			}
		}
		return orders;
	}

}
