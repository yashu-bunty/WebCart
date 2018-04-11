package com.shoppingCart.Model;
// Generated Nov 17, 2016 7:16:37 PM by Hibernate Tools 5.2.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Inventory generated by hbm2java
 */
@Entity
@Table(name = "inventory", catalog = "shopping_cart_schema")
public class Inventory implements java.io.Serializable {

	private int itemId;
	private String itemName;
	private String itemPrice;
	private String itemImageUrl;
	private String itemGroup;
	private Integer itemQunatity;
	private Integer vendorId;
	private Set<OrderDetails> orderDetailses = new HashSet<OrderDetails>(0);

	public Inventory() {
	}

	public Inventory(int itemId) {
		this.itemId = itemId;
	}

	public Inventory(int itemId, String itemName, String itemPrice, String itemImageUrl, String itemGroup,
			Integer itemQunatity, Integer vendorId, Set<OrderDetails> orderDetailses) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemImageUrl = itemImageUrl;
		this.itemGroup = itemGroup;
		this.itemQunatity = itemQunatity;
		this.vendorId = vendorId;
		this.orderDetailses = orderDetailses;
	}

	
	public Inventory(int itemId, String itemName, String itemPrice, String itemImageUrl, String itemGroup,
			Integer itemQunatity, Integer vendorId) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemImageUrl = itemImageUrl;
		this.itemGroup = itemGroup;
		this.itemQunatity = itemQunatity;
		this.vendorId = vendorId;
	}

	@Id

	@Column(name = "Item_id", unique = true, nullable = false)
	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	@Column(name = "Item_Name", length = 45)
	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Column(name = "item_price", length = 45)
	public String getItemPrice() {
		return this.itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Column(name = "item_imageUrl")
	public String getItemImageUrl() {
		return this.itemImageUrl;
	}

	public void setItemImageUrl(String itemImageUrl) {
		this.itemImageUrl = itemImageUrl;
	}

	@Column(name = "Item_Group", length = 45)
	public String getItemGroup() {
		return this.itemGroup;
	}

	public void setItemGroup(String itemGroup) {
		this.itemGroup = itemGroup;
	}

	@Column(name = "item_qunatity")
	public Integer getItemQunatity() {
		return this.itemQunatity;
	}

	public void setItemQunatity(Integer itemQunatity) {
		this.itemQunatity = itemQunatity;
	}

	@Column(name = "vendor_id")
	public Integer getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "inventory")
	public Set<OrderDetails> getOrderDetailses() {
		return this.orderDetailses;
	}

	public void setOrderDetailses(Set<OrderDetails> orderDetailses) {
		this.orderDetailses = orderDetailses;
	}

}
