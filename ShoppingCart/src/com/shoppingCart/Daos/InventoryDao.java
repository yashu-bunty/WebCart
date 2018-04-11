package com.shoppingCart.Daos;

import java.util.List;

import com.shoppingCart.Model.Inventory;
import com.shoppingCart.Model.VendorDetails;

public interface InventoryDao {
	
	public List<String> getProducts();

	public List<Inventory> getInventory();
	public Inventory getItemById(String itemId);
	public List<Inventory> getInventoryByProduct(String fltr);
	public Integer updateInvQty(int itemID,int qty);
	public Boolean addNewItem(Inventory inventory);
	
	public void exportEcel(List<VendorDetails> vList);
	 
	public Boolean addInvQty(String qty,int id);
		
	public Boolean updateInvPrice(String newPrice,int id);
}
