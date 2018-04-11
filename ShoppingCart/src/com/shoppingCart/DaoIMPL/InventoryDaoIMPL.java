package com.shoppingCart.DaoIMPL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shoppingCart.Daos.InventoryDao;
import com.shoppingCart.Model.Inventory;
import com.shoppingCart.Model.VendorDetails;
import com.shoppingCart.Services.Constants;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class InventoryDaoIMPL extends HibernateDaoSupport implements InventoryDao{
	
	HSSFWorkbook workbook = null;

	@Override
	public List<String> getProducts() {
		
		
		@SuppressWarnings("unchecked")
		List<Object> list= (List<Object>)getHibernateTemplate().find(
				"select itemGroup from Inventory");
		List<String> productList = new ArrayList<String>();
		for(Object obj:list)
		productList.add(obj.toString());
		return productList;
	}

	@Override
	public List<Inventory> getInventory() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Object[]> cList = ((List<Object[]>)getHibernateTemplate().find(
				"select itemId,itemName,itemPrice,itemImageUrl,itemGroup,itemQunatity,vendorId from Inventory"));
		List<Inventory> iList = new ArrayList<Inventory>();
		for(Object[] obj:cList){
			Inventory inv = new Inventory();
			inv.setItemId(Integer.parseInt(obj[0].toString()));
			inv.setItemName(obj[1].toString());
			inv.setItemPrice(obj[2].toString());
			inv.setItemImageUrl(obj[3].toString());
			inv.setItemGroup(obj[4].toString());
			inv.setItemQunatity(Integer.parseInt(obj[5].toString()));
			inv.setVendorId(Integer.parseInt(obj[6].toString()));
			iList.add(inv);
		}
		return iList;
	}

	@Override
	public Inventory getItemById(String itemId) {
		@SuppressWarnings("unchecked")
		List<Object[]> iList = (List<Object[]>)getHibernateTemplate().find(
				"select itemId,itemName,itemPrice,itemImageUrl,itemGroup,itemQunatity,vendorId from Inventory where itemId=?",Integer.parseInt(itemId));
		Inventory invItem = new Inventory();
		for(Object[] item:iList){
			
			invItem.setItemId(Integer.parseInt(item[0].toString()));
			invItem.setItemName(item[1].toString());
			invItem.setItemPrice(item[2].toString());
			invItem.setItemImageUrl(item[3].toString());
			invItem.setItemGroup(item[4].toString());
			invItem.setItemQunatity(Integer.parseInt(item[5].toString()));
			invItem.setVendorId(Integer.parseInt(item[6].toString()));
		}
		return invItem;
	}
	
	@Override
	public List<Inventory> getInventoryByProduct(String fltr){
		@SuppressWarnings("unchecked")
		List<Object[]> cList = ((List<Object[]>)getHibernateTemplate().find(
				"select itemId,itemName,itemPrice,itemImageUrl,itemGroup,itemQunatity from Inventory where itemGroup=?",fltr));
		List<Inventory> iList = new ArrayList<Inventory>();
		for(Object[] obj:cList){
			Inventory inv = new Inventory();
			inv.setItemId(Integer.parseInt(obj[0].toString()));
			inv.setItemName(obj[1].toString());
			inv.setItemPrice(obj[2].toString());
			inv.setItemImageUrl(obj[3].toString());
			inv.setItemGroup(obj[4].toString());
			inv.setItemQunatity(Integer.parseInt(obj[5].toString()));
			iList.add(inv);
		}
		return iList;
	}

	@Override
	public Integer updateInvQty(int itemID, int qty) {
		int executed = getHibernateTemplate().bulkUpdate("update Inventory set itemQunatity=? where itemId=? ",qty,itemID);
		
		return executed;
	}

	@Override
	public Boolean addNewItem(Inventory inventory) {
		boolean added = false;
		
		try{
			getHibernateTemplate().save(inventory);
			added = true;
		}catch (Exception e) {
			System.err.println("Adding New Item Failed"+e.getMessage().toString());
			e.printStackTrace();
		}
		return added;
	}
	
	
	@Override
	public void exportEcel(List<VendorDetails> vList){
		try{
			workbook = new HSSFWorkbook();
			addInventorySheet();
			addVendorSheet(vList);
	    
	    	String filePath = Constants.eport_excel+File.separator+Constants.export_file_name;
	    
	    	FileOutputStream fileOut = new FileOutputStream(filePath);
	    	workbook.write(fileOut);
	    	fileOut.close();
	    	
	    } catch (FileNotFoundException e1) {
	        e1.printStackTrace();
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    }
	}
	
	public void addInventorySheet(){
		@SuppressWarnings("unchecked")
		List<Object[]> cList = ((List<Object[]>)getHibernateTemplate().find(
				"select itemId,itemName,itemPrice,itemImageUrl,itemGroup,itemQunatity,vendorId from Inventory"));
		HSSFSheet sheet = workbook.createSheet("Inventory");
	    HSSFRow rowhead = sheet.createRow((short) 0);
	    rowhead.createCell((short) 0).setCellValue("Item ID");
	    rowhead.createCell((short) 1).setCellValue("Item Name");
	    rowhead.createCell((short) 2).setCellValue("Item Price");
	    rowhead.createCell((short) 3).setCellValue("Image Name");
	    rowhead.createCell((short) 4).setCellValue("Item Group");
	    rowhead.createCell((short) 5).setCellValue("Item Quantity");
	    rowhead.createCell((short) 6).setCellValue("Vendor Id");
	    int i = 1;
	   	    
	    for(Object[] obj:cList){
	    	HSSFRow row = sheet.createRow((short) i);
			row.createCell((short) 0).setCellValue(obj[0].toString());
			row.createCell((short) 1).setCellValue(obj[1].toString());
			row.createCell((short) 2).setCellValue(obj[2].toString());
			row.createCell((short) 3).setCellValue(obj[3].toString());
			row.createCell((short) 4).setCellValue(obj[4].toString());
			row.createCell((short) 5).setCellValue(obj[5].toString());
			row.createCell((short) 6).setCellValue(obj[6].toString());
			i++;
		}
	}
	
	public void addVendorSheet(List<VendorDetails> vList){
		
		HSSFSheet sheet = workbook.createSheet("Vedors");
	    HSSFRow rowhead = sheet.createRow((short) 0);
	    rowhead.createCell((short) 0).setCellValue("Vendor ID");
	    rowhead.createCell((short) 1).setCellValue("Vendor Name");
	    rowhead.createCell((short) 2).setCellValue("Vendor Email");
	    
	    int i = 1;
	   	    
	    for(VendorDetails obj:vList){
	    	HSSFRow row = sheet.createRow((short) i);
			row.createCell((short) 0).setCellValue(obj.getVendorId());
			row.createCell((short) 1).setCellValue(obj.getVendorName());
			row.createCell((short) 2).setCellValue(obj.getVendorEmail());
			i++;
		}
	}

	@Override
	public Boolean addInvQty(String qty,int id) {
		
		int executed = getHibernateTemplate().bulkUpdate("update Inventory set itemQunatity=? where itemId=? ",Integer.parseInt(qty),id);
		return executed>0;
		
	}

	@Override
	public Boolean updateInvPrice(String newPrice,int id) {
		
		int executed = getHibernateTemplate().bulkUpdate("update Inventory set itemPrice=? where itemId=? ",newPrice,id);
		
		return executed>0;
	}
	
	

}
