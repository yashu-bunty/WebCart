package com.shoppingCart.Services;

import java.util.List;

import com.shoppingCart.Daos.VendorDao;
import com.shoppingCart.Model.Inventory;
import com.shoppingCart.Model.VendorDetails;

public class VendorService {
	
	private VendorDao vendorDao = null;
	
	public void remindVendor(Inventory itm,int requiredQty){
		VendorDetails vendor = new VendorDetails();
		vendor = getVendorDetails(itm.getVendorId());
		MailService ms = new MailService();
		ms.sendEmailToVendor(vendor,requiredQty,itm.getItemName());
	}
	
	public VendorDetails getVendorDetails(int vendorId){
		return vendorDao.getVendorContactDetails(vendorId);
	}

	public VendorService() {
		super();
	}

	public VendorService(VendorDao vendorDao) {
		super();
		this.vendorDao = vendorDao;
	}

	public VendorDao getVendorDao() {
		return vendorDao;
	}

	public void setVendorDao(VendorDao vendorDao) {
		this.vendorDao = vendorDao;
	}
	
	public Boolean addVendor(VendorDetails vendor){
		
		return vendorDao.addVendor(vendor);
	}
	
	public List<VendorDetails> getVendorsList(){
		return vendorDao.getVendorsList();
	}

}
