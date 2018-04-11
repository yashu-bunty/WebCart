package com.shoppingCart.Services;

import java.util.List;

import com.shoppingCart.Daos.LoginDetailsDao;
import com.shoppingCart.Model.LoginDetails;
import com.shoppingCart.Model.OrderDetails;

public class LoginDetailsService {
	
	private LoginDetailsDao logDetDao = null;

	public LoginDetailsDao getLogDetDao() {
		return logDetDao;
	}

	public void setLogDetDao(LoginDetailsDao logDetDao) {
		this.logDetDao = logDetDao;
	}
	
	//Validating User credentials
	public LoginDetails validateUserLogin(String userID,String password){
		return logDetDao.validateUserLogin(userID, password);
		
	}
	
	//Validating User Name to maintain Unique Id's
	public Boolean validateUid(String Uname,String email){
		
		return logDetDao.validateEmail(email) && logDetDao.validateUserName(Uname);
	}
	
	//add New User
	public void addUser(String UserName,String password,String fname,String email){
		logDetDao.registerUser(UserName, password,fname,email);
	}
	
	public LoginDetails getUserDatafromMailId(String mail){
		
		return logDetDao.getUserFromMail(mail);
	}
	
	
	public LoginDetails getUserFromId(String uId){
		return logDetDao.getUserFromId(uId);
	}

}
