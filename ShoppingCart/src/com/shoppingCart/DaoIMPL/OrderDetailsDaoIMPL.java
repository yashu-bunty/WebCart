package com.shoppingCart.DaoIMPL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shoppingCart.Daos.OrderDetailsDao;
import com.shoppingCart.Model.Inventory;
import com.shoppingCart.Model.LoginDetails;
import com.shoppingCart.Model.OrderDetails;
import com.shoppingCart.Services.Constants;

public class OrderDetailsDaoIMPL extends HibernateDaoSupport implements OrderDetailsDao {

	@Override
	public List<OrderDetails> getCartHistoryOfUser(String userName) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Object[]> list= (List<Object[]>)getHibernateTemplate().find(
				"select orderId,inventory.itemName,quantity,orderStatus from OrderDetails where loginDetails.userId = ?)",userName);
		List<OrderDetails> cartHistory = new ArrayList<OrderDetails>();
		if(list.size()>0){
			for(Object[] obj:list){
				OrderDetails od= new OrderDetails();
				od.setOrderId(Integer.parseInt(obj[0].toString()));
				Inventory ctItem = new Inventory();
				ctItem.setItemName(obj[1].toString());
				od.setInventory(ctItem);
				od.setQuantity(Integer.parseInt(obj[2].toString()));
				od.setOrderStatus(obj[3].toString());
				cartHistory.add(od);
			}
		}
		return cartHistory;
	}
	
	@Override
	public Integer saveOrder(List<OrderDetails> order) {
		OrderDetails orderdetail = new OrderDetails();
		int maxOrderId = getMaxOrderId(true);
		boolean inserted = false;
		try{
			for(int i = 0;i<order.size();i++){
				 orderdetail.setOrderId(maxOrderId+1);
				 orderdetail.setInventory(order.get(i).getInventory());
				 orderdetail.setLoginDetails(order.get(i).getLoginDetails());
				 orderdetail.setOrderStatus(order.get(i).getOrderStatus());
				 orderdetail.setQuantity(order.get(i).getQuantity());
				 
				getHibernateTemplate().save(orderdetail);
				inserted = true;
			}
			
		}catch (Exception e) {
			System.err.println("Adding Order Failed"+e.getMessage().toString());
		}
		int newOrderId = getMaxOrderId(inserted);
		
		return newOrderId;
		
	}
	
	public int getMaxOrderId(boolean isInserted){
		@SuppressWarnings("unchecked")
		List<Object> list= (List<Object>)getHibernateTemplate().find(
				"select max(orderId) from OrderDetails");
		int orderId=0;
		if(isInserted){
			try{
				for(int i=0;i<list.size();i++){
					orderId = (int) list.get(i);
				}
			}catch(Exception e){
				System.err.println("Retrieving Order Id Failed"+e.getMessage().toString());
			}
		}
		return orderId;
	}

	@Override
	public List<OrderDetails> getOrdersWithPendingStatus(int itemID) {
		@SuppressWarnings("unchecked")
		List<Object[]> list= (List<Object[]>)getHibernateTemplate().find(
				"select inventory.itemId,loginDetails.userId,quantity,orderId from OrderDetails where orderStatus = ? and inventory.itemId=?)",Constants.delivery_pending,itemID);
		List<OrderDetails> pendingOrders = new ArrayList<OrderDetails>();
		if(list.size()>0){
			for(Object[] obj:list){
				OrderDetails oItem = new OrderDetails();
				Inventory inv = new Inventory();
				LoginDetails ld = new LoginDetails();
				inv.setItemId(Integer.parseInt(obj[0].toString()));
				oItem.setInventory(inv);
				ld.setUserId(obj[1].toString());
				oItem.setLoginDetails(ld);
				oItem.setQuantity(Integer.parseInt(obj[2].toString()));
				oItem.setOrderId(Integer.parseInt(obj[3].toString()));
				pendingOrders.add(oItem);
			}
		}
		
		return pendingOrders;
	}

	@Override
	public void updateOrderStatus(OrderDetails od) {
		getHibernateTemplate().bulkUpdate("update OrderDetails set orderStatus=?,quantity=? where orderId=? "
				,od.getOrderStatus(),od.getQuantity(),od.getOrderId());
	}

	@Override
	public void saveSingleOrder(OrderDetails od) {
		getHibernateTemplate().save(od);
		
	}

}
