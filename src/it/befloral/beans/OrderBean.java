package it.befloral.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class OrderBean {
	private int id;
	private String destination; 
	private int totalProducts;
	private double totalPaid;
	private String trackNumber;
	private String status;
	private boolean gift;
	private String  giftMessage;
	private Collection<OrderItemBean> items;
	private UserBean user;
	private LocalDateTime createdAt;
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public Collection<OrderItemBean> getItems() {
		return items;
	}
	public void setItems(Collection<OrderItemBean> items) {
		this.items = items;
	}
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getTotalProducts() {
		return totalProducts;
	}
	public void setTotalProducts(int totalProducts) {
		this.totalProducts = totalProducts;
	}
	public double getTotalPaid() {
		return totalPaid;
	}
	public void setTotalPaid(double totalPaid) {
		this.totalPaid = totalPaid;
	}
	public String getTrackNumber() {
		return trackNumber;
	}
	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isGift() {
		return gift;
	}
	public void setGift(boolean gift) {
		this.gift = gift;
	}
	public String getGiftMessage() {
		return giftMessage;
	}
	public void setGiftMessage(String giftMessage) {
		this.giftMessage = giftMessage;
	}
	public void addItem(OrderItemBean bean) {
		if(this.items == null)
			this.items = new ArrayList<OrderItemBean>();
		this.items.add(bean);
	}
	
	
	
}
