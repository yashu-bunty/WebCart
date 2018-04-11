package com.shoppingCart.Services;


import java.util.List;

import com.shoppingCart.Daos.OrderDetailsDao;
import com.shoppingCart.Model.Inventory;
import com.shoppingCart.Model.OrderDetails;

public class OrderDetailsService {
	
	public OrderDetailsDao orderDao = null;
	
	public OrderDetailsDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDetailsDao orderDao) {
		this.orderDao = orderDao;
	}

	public List<OrderDetails> getCartHistoryOfUser(String userName){
		return orderDao.getCartHistoryOfUser(userName);
	}
	
	public Integer saveOrders(List<OrderDetails> orders){
		return orderDao.saveOrder(orders);
	}
	
	public List<OrderDetails> getOrdersWithPendingStatus(int itemID){
		
		return orderDao.getOrdersWithPendingStatus(itemID);
	}
	
	public void updateOrderStatus(OrderDetails od){
		orderDao.updateOrderStatus(od);
		
	}
	
	public void saveSingleOrder(OrderDetails od){
		orderDao.saveSingleOrder(od);
		
	}
	

}
