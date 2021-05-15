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

import it.befloral.beans.OrderBean;
import it.befloral.beans.OrderItemBean;
import it.befloral.beans.ProductBean;
import it.befloral.beans.UserBean;

public class OrderDAO implements GenericDAO<OrderBean> {
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
	public Collection<OrderBean> doRetrieveAll(String order)
			throws SQLException {
		Collection<OrderBean> orders = new LinkedList<>();
		String selectSQL = "SELECT * FROM " + TABLE_NAME;
		try(var conn = ds.getConnection()) {
			try(var stmt = conn.prepareStatement(selectSQL)){
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					OrderBean bean = new OrderBean();
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
	public OrderBean doRetriveByKey(int code) throws SQLException {
		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ? LEFT JOIN order_items s WHERE s.oid = id ";
		OrderBean order = new OrderBean();
		try(var conn = ds.getConnection()) {
			try(var stmt = conn.prepareStatement(selectSQL)){
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
	public void doSave(OrderBean dao) throws SQLException {
		String insertOrder = "INSERT INTO orders (`uid`, `destination`, `totalProducts`, `totalPaid`, `trackNumber`, `gift`, `giftMessage`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		String insertItem = "INSERT INTO order_items "
				+ "(`oid`, `name`, `description`, `shortDescription`, `price`, `weight`, `discount`, `quantity`) "
				+ "VALUES (? , ?, ?, ?, ?, ?, ?, ?)";
		var conn = ds.getConnection();
		try{
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
            if(rs.next()){
                int lastInsertedId = rs.getInt(1); 
                for(OrderItemBean item : dao.getItems()) {
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
		} catch (SQLException e ) {
			e.printStackTrace();
			conn.rollback();
		}
		
	}

	@Override
	public int doUpdate(OrderBean dao) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean doDelete(int code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public Collection<OrderBean> doRetriveByUser(UserBean userBean) throws SQLException {
		Collection<OrderBean> orders = new LinkedList<OrderBean>();
		String selectSQL = "SELECT * FROM " + TABLE_NAME;
		try(var conn = ds.getConnection()) {
			try(var stmt = conn.prepareStatement(selectSQL)){
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					OrderBean bean = new OrderBean();
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
