package com.shoppingCart.delegates;

import java.util.ArrayList;
import java.util.List;

import com.shoppingCart.Model.Inventory;

public class CartDetails {

	private List<String> productGroup = new ArrayList<String>();
	private List<Inventory> inventoryList = new ArrayList<Inventory>();
	public List<String> getProductGroup() {
		return productGroup;
	}
	public void setProductGroup(List<String> productGroup) {
		this.productGroup = productGroup;
	}
	public List<Inventory> getInventoryList() {
		return inventoryList;
	}
	public void setInventoryList(List<Inventory> inventoryList) {
		this.inventoryList = inventoryList;
	}
	public CartDetails(List<String> productGroup, List<Inventory> inventoryList) {
		super();
		this.productGroup = productGroup;
		this.inventoryList = inventoryList;
	}
	public CartDetails() {
		super();
	}
	
}
