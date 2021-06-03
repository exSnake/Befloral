package it.befloral.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
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

	public Collection<Order> doRetrieveOrdersBetween(String sort, int userId, int from, int howMany, Timestamp fromDate,
			Timestamp ToDate) throws SQLException {
		String selectSQL = userId == 0 ? 
				"SELECT * FROM  " + TABLE_NAME
				+ " WHERE uid LIKE ? AND createdAt BETWEEN ? AND ? ORDER BY ? LIMIT ? OFFSET ?"
				:
				"SELECT * FROM  " + TABLE_NAME
				+ " WHERE uid = ?  AND createdAt BETWEEN ? AND ? ORDER BY ? LIMIT ? OFFSET ?";
		Collection<Order> orders = new LinkedList<>();

		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				if(userId == 0)
					stmt.setString(1, "%");
				else
					stmt.setInt(1, userId);
				stmt.setTimestamp(2, fromDate);
				stmt.setTimestamp(3, ToDate);
				stmt.setString(4, sort);
				stmt.setInt(5, howMany);
				stmt.setInt(6, from);
				System.out.println(stmt);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Order bean = new Order();
					bean.setId(rs.getInt("id"));
					bean.setDestination(rs.getString("destination"));
					bean.setTotalProducts(rs.getInt("totalProducts"));
					bean.setTotalPaid(rs.getDouble("totalPaid"));
					bean.setTrackNumber(rs.getString("trackNumber"));
					bean.setGift(rs.getBoolean("gift"));
					bean.setGiftMessage(rs.getString("giftMessage"));
					bean.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
					orders.add(bean);
				}
			}
		}
		return orders;
	}

	public Collection<Order> doRetrieveSome(String order, int from, int howMany) throws SQLException {
		Collection<Order> orders = new LinkedList<>();
		String selectSQL = "SELECT * FROM  " + TABLE_NAME + " ORDER BY ? LIMIT ? OFFSET ? ";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {

				stmt.setString(1, order);
				stmt.setInt(2, howMany);
				stmt.setInt(3, from);

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
		String selectSQL = "SELECT o.id AS orderId, o.uid, o.destination, o.totalProducts, o.totalPaid, o.trackNumber, o.gift, o.giftMessage, o.createdAt,s.* FROM "
				+ TABLE_NAME + " o LEFT JOIN order_items  s ON  s.oid = o.id WHERE o.id = ?";
		Order order = new Order();
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				stmt.setInt(1, code);
				System.out.println(stmt);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					order.setId(rs.getInt("orderId"));
					order.setDestination(rs.getString("destination"));
					order.setTotalProducts(rs.getInt("totalProducts"));
					order.setTotalPaid(rs.getDouble("totalPaid"));
					order.setTrackNumber(rs.getString("trackNumber"));
					order.setGift(rs.getBoolean("gift"));
					order.setGiftMessage(rs.getString("giftMessage"));
				}
				do {
					OrderItem item = new OrderItem();
					item.setId(rs.getInt("id"));
					item.setOid(rs.getInt("oid"));
					item.setName(rs.getString("name"));
					item.setDescription(rs.getString("description"));
					item.setShortDescription(rs.getString("shortDescription"));
					item.setPrice(rs.getDouble("price"));
					item.setWeight(rs.getDouble("weight"));
					item.setDiscount(rs.getDouble("discount"));
					item.setQuantity(rs.getInt("quantity"));
					order.addItem(item);
				} while (rs.next());
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
		try (var stmt = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS)) {
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

	public int doUpdateOrder(Order dao) throws SQLException {
		String updateOrder = "UPDATE orders"
				+ "			SET `destination` = ?,"
				+ "				`trackNumber` = ?,"
				+ "				`giftMessage`= ?"
				+ "			WHERE `id` = ? ";

		try(var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(updateOrder);) {
				stmt.setString(1, dao.getDestination());
				stmt.setString(2, dao.getTrackNumber());
				if(dao.getGiftMessage() == null)
					stmt.setNull(3, Types.NULL);
				else stmt.setString(1, dao.getGiftMessage());
				stmt.setInt(4, dao.getId());
				System.out.println(stmt);
				return stmt.executeUpdate();
			} 
		}
	}

	@Override
	public int doUpdate(Order dao) throws SQLException {

		String updateOrder = "UPDATE  orders SET(`destination` = ?, `totalProducts` = ?, `totalPaid` =?, `trackNumber` =?, `gift`=?, `giftMessage`=?) "
				+ "WHERE `uid`=? and 'id' = ? ";
		String updateItem = "UPDATE  order_items SET(`name` = ?, `description` = ?, `shortDescription` = ?"
				+ ", `price` = ?, `weight` = ?, `discount` = ?, `quantity`= ? ) " + "WHERE 'id' = ? and 'oid' = ?  ";
		var conn = ds.getConnection();

		try {
			var stmt = conn.prepareStatement(updateOrder);
			stmt.setString(1, dao.getDestination());
			stmt.setInt(2, dao.getTotalProducts());
			stmt.setDouble(3, dao.getTotalPaid());
			stmt.setString(4, dao.getTrackNumber());
			stmt.setBoolean(5, dao.isGift());
			stmt.setString(6, dao.getGiftMessage());

			stmt.setInt(7, dao.getUser().getId());
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
				stmt2.setInt(9, dao.getId());

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

				stmt.setInt(1, code);

				rs = stmt.executeUpdate();

				return rs > 0 ? true : false;
			}
		}
	}

	public Collection<Order> doRetriveByUser(User userBean) throws SQLException {
		Collection<Order> orders = new LinkedList<Order>();
		String selectSQL = "SELECT * FROM " + TABLE_NAME +" WHERE uid=?";
		try (var conn = ds.getConnection()) {
			try (var stmt = conn.prepareStatement(selectSQL)) {
				stmt.setInt(1, userBean.getId());
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
					bean.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
					orders.add(bean);
				}
			}
		}
		return orders;
	}

	public int doRetrieveCount() throws SQLException {
		String sql = "SELECT COUNT(*) AS c FROM " +TABLE_NAME;
		try(var conn = ds.getConnection()){
			try(var stmt = conn.prepareStatement(sql)){
				ResultSet rs = stmt.executeQuery();
				return rs.next() ? rs.getInt("c") : 0;
			}
		}
	}

}
