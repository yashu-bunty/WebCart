package com.shoppingCart.Daos;

import java.util.List;

import com.shoppingCart.Model.Inventory;
import com.shoppingCart.Model.OrderDetails;

public interface OrderDetailsDao {
	
	public List<OrderDetails> getCartHistoryOfUser(String userName);
	public Integer saveOrder(List<OrderDetails> order);
	
	public List<OrderDetails> getOrdersWithPendingStatus(int itemID);
	public void updateOrderStatus(OrderDetails od);
	
	public void saveSingleOrder(OrderDetails od);
	

}
