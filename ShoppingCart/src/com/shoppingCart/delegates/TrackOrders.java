package com.shoppingCart.delegates;

public class TrackOrders {
	
	private int itemId;
	private String itemName;
	private int vendorId;
	private String vendorName;
	private String orderStatus;
	
	public TrackOrders() {
		super();
	}
	public TrackOrders(int itemId, String itemName, int vendorId, String vendorName, String orderStatus) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.orderStatus = orderStatus;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	

}
