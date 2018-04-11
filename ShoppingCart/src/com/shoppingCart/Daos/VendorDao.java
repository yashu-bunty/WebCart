package com.shoppingCart.Daos;

import java.util.List;

import com.shoppingCart.Model.VendorDetails;

public interface VendorDao {
	
	public VendorDetails getVendorContactDetails(int id);
	public Boolean addVendor(VendorDetails vendor);
	
	public List<VendorDetails> getVendorsList();

}
