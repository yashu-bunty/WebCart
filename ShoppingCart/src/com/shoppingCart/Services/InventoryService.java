package com.shoppingCart.Services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.shoppingCart.Daos.InventoryDao;
import com.shoppingCart.Model.Inventory;
import com.shoppingCart.Model.OrderDetails;
import com.shoppingCart.Model.VendorDetails;

public class InventoryService {
	private InventoryDao invDao = null;
	

	public InventoryDao getInvDao() {
		return invDao;
	}

	public void setInvDao(InventoryDao invDao) {
		this.invDao = invDao;
	}

	
	//Retrieving Group for Each Item from Inventory Dao layer
	public List<String> getProductGroups(){
		List<String> grps = invDao.getProducts();
		Set<String> uniqueSet = new HashSet<String>();
		uniqueSet.add("");
		for(int i=0;i<grps.size();i++){
			uniqueSet.add(grps.get(i));
		}
		Iterator<String> setIterator = uniqueSet.iterator();
		grps.clear();
		while(setIterator.hasNext()){
			grps.add(setIterator.next());
		}
//		grps.add(0, "");
		return grps;
	}
	
	//Retrieving complete Items from Inventory Dao layer
	public List<Inventory> getInventoryItems(){
		return invDao.getInventory();
	}
	
	public Inventory getItemByIdSrvc(String itemId){
			
		return invDao.getItemById(itemId);
	}
	
	public List<Inventory> getInventoryItemsByProduct(String fltr){
		
		return invDao.getInventoryByProduct(fltr);		
	}
	
	public void updateInvQty(List<OrderDetails> orders){
		
		for(int i = 0;i<orders.size();i++){
			int itemId = orders.get(i).getInventory().getItemId();
			int qty = orders.get(i).getQuantity();
			Inventory inv = invDao.getItemById(Integer.toString(itemId));
			invDao.updateInvQty(itemId,(inv.getItemQunatity() - qty<0?0:inv.getItemQunatity() - qty));
			
		}
	}
	
	public Boolean updateInvQty(String qty,int id){
		return invDao.addInvQty(qty,id);
	}
	
	public Boolean updateInvPrice(String newPrice,int id){
		return invDao.updateInvPrice(newPrice,id);
	}
	
	public Boolean addNewItem(Inventory inventory){
		return invDao.addNewItem(inventory);
	}
	
	public void exportInventory(List<VendorDetails> vList){
		invDao.exportEcel(vList);
	}
}
