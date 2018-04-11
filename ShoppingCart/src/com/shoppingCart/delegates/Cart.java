package com.shoppingCart.delegates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.shoppingCart.Model.Inventory;



/**
 * 
 * @author yashaswivemula
 *
 *This Class is used to perform all the transactions related 
 *in the Cart Ex: remove Item,update total
 */

public class Cart {

	private HashMap<String,CartItemDetails> itemsList = null;
	int numberOfItems = 0;
	double total = 0.0;
	
	public Cart() {
		itemsList = new HashMap<String, CartItemDetails>();
	}

	public synchronized void add(String itemID, Inventory setItems) {

		if (itemsList.containsKey(itemID)) {
			CartItemDetails scitem = (CartItemDetails) itemsList.get(itemID);
			scitem.incrementQuantity();
		} else {
			CartItemDetails newItem = new CartItemDetails(setItems);
			itemsList.put(itemID, newItem);
		}
	}
	
	public synchronized void updateQty(String itemId,int qty){
		if(itemsList.containsKey(itemId)){
			CartItemDetails scitem = (CartItemDetails) itemsList.get(itemId);
			scitem.updateRequestedQuantity(qty);
		}
	}

	public synchronized List<CartItemDetails> getItems() {
		List<CartItemDetails> results = new ArrayList<CartItemDetails>();
		results.addAll(this.itemsList.values());

		return results;
	}

	public synchronized int getNumberOfItems() {
		numberOfItems = 0;

		for (Iterator<CartItemDetails> i = getItems().iterator(); i.hasNext();) {
			CartItemDetails item = i.next();
			numberOfItems += item.getCartQuantity();
			//System.out.println("number of items is " + numberOfItems);
		}

		return numberOfItems;
	}

	public synchronized void remove(String itemID) {
		if (itemsList.containsKey(itemID)) {
			CartItemDetails scitem = (CartItemDetails) itemsList.get(itemID);
			//scitem.decrementQuantity();
			itemsList.remove(itemID);
//			if (scitem.getCartQuantity() <= 0) {
//				itemsList.remove(itemID);
//			}

			numberOfItems--;
		}
	}

	public synchronized void clear() {
		System.err.println("Clearing cart.");
		itemsList.clear();
		numberOfItems = 0;
	}

	public HashMap<String, CartItemDetails> getItemsList() {
		return itemsList;
	}

	public void setItemsList(HashMap<String, CartItemDetails> itemsList) {
		this.itemsList = itemsList;
	}

	public synchronized double getTotal() {
		
		double amount = 0.0;
		for(Iterator<CartItemDetails> i = getItems().iterator();i.hasNext();) {
			CartItemDetails item = (CartItemDetails)i.next();
			Inventory s = (Inventory)item.getCartItems();
			int itemPrice=0;
			if(s.getItemPrice().contains(".")){
				int dotIndex = s.getItemPrice().indexOf('.');
				itemPrice = Integer.parseInt(s.getItemPrice().substring(0,dotIndex ));
				itemPrice += Integer.parseInt(s.getItemPrice().substring(dotIndex+1, s.getItemPrice().length()-1))/100;
				//After decimal 2 digits, ex: 99/100 = .99, is added to before decimal
			}
			else
				itemPrice = Integer.parseInt(s.getItemPrice());
			System.out.println("Item Priceccccccccccc "+itemPrice);			
			amount += item.getCartQuantity() * itemPrice;
		}
		System.out.println("Total Amount"+amount);
		total=amount;
		return amount;

	}

}
