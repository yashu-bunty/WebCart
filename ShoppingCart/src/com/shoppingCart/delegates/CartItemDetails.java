package com.shoppingCart.delegates;
/**
 * 
 * @author yashaswivemula
 * This class is used to store the Item added to Cart
 * and its Quantity
 */
public class CartItemDetails {
	Object cartItem;
	int cartQuantity;
	public Object getCartItems() {
		return cartItem;
	}
	public void setCartItems(Object cartItem) {
		this.cartItem = cartItem;
	}
	public int getCartQuantity() {
		return cartQuantity;
	}
	public void setCartQuantity(int cartQuantity) {
		this.cartQuantity = cartQuantity;
	}
	
	public CartItemDetails(Object cartItem) {
		this.cartItem = cartItem;
		cartQuantity = 1;
	}
	
	public void incrementQuantity() {
		cartQuantity++;
	}

	public void decrementQuantity() {
		cartQuantity--;
	}
	
	public void updateRequestedQuantity(int reqQty) {
		cartQuantity = reqQty;
	}


}
