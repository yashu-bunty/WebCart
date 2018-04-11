package com.shoppingCart.DaoIMPL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shoppingCart.Daos.VendorDao;
import com.shoppingCart.Model.VendorDetails;
/**
 * 
 * @author yashaswivemula
 *This is a Implementation Class of Vendor Dao
 *Here we retrieve data from Vendor Table
 */

public class VendorDaoIMPL extends HibernateDaoSupport implements VendorDao {

	@Override
	public VendorDetails getVendorContactDetails(int id) {
		@SuppressWarnings("unchecked")
		List<Object[]> list= (List<Object[]>)getHibernateTemplate().find(
				"select vendorName,vendorEmail from VendorDetails where vendorId=?",id);
		VendorDetails vendor = new VendorDetails();
		for(Object[] obj:list){
			vendor.setVendorId(id);
			vendor.setVendorName(obj[0].toString());
			vendor.setVendorEmail(obj[1].toString());
		}
		
		return vendor;
	}

	@Override
	public Boolean addVendor(VendorDetails vendor) {
		
		boolean added = false;
		
		try{
			getHibernateTemplate().save(vendor);
			added = true;
		}catch (Exception e) {
			System.err.println("Adding User Failed"+e.getMessage().toString());
			e.printStackTrace();
		}
		return added;
	}
	
	
	@Override
	public List<VendorDetails> getVendorsList(){
		
		
		@SuppressWarnings("unchecked")
		List<Object[]> list= (List<Object[]>)getHibernateTemplate().find(
				"select vendorId,vendorName,vendorEmail from VendorDetails");
		
		List<VendorDetails> vList = new ArrayList<VendorDetails>();
		for(Object[] obj:list){
			VendorDetails vendor = new VendorDetails();
			vendor.setVendorId(Integer.parseInt(obj[0].toString()));
			vendor.setVendorName(obj[1].toString());
			vendor.setVendorEmail(obj[2].toString());
			vList.add(vendor);
		}
		
		return vList;
		
	}
	
	

}
