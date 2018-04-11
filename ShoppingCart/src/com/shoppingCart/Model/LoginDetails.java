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
 * LoginDetails generated by hbm2java
 */
@Entity
@Table(name = "login_details", catalog = "shopping_cart_schema")
public class LoginDetails implements java.io.Serializable {

	private String userId;
	private String userPassword;
	private String fullName;
	private String contact;
	private Set<OrderDetails> orderDetailses = new HashSet<OrderDetails>(0);

	public LoginDetails() {
	}

	public LoginDetails(String userId) {
		this.userId = userId;
	}

	public LoginDetails(String userId, String userPassword, String fullName, String contact, Set<OrderDetails> orderDetailses) {
		this.userId = userId;
		this.userPassword = userPassword;
		this.fullName = fullName;
		this.contact = contact;
		this.orderDetailses = orderDetailses;
	}

	@Id

	@Column(name = "user_id", unique = true, nullable = false, length = 45)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "user_password", length = 45)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "full_name", length = 45)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "Contact", length = 45)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loginDetails")
	public Set<OrderDetails> getOrderDetailses() {
		return this.orderDetailses;
	}

	public void setOrderDetailses(Set<OrderDetails> orderDetailses) {
		this.orderDetailses = orderDetailses;
	}

}
