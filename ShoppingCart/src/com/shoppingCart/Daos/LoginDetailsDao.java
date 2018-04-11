package com.shoppingCart.Daos;

import com.shoppingCart.Model.LoginDetails;

public interface LoginDetailsDao {
	public LoginDetails validateUserLogin(String userID,String password);
	public Boolean registerUser(String uid, String pwd, String fname, String email);
	public Boolean validateUserName(String uName);
	public LoginDetails getUserFromMail(String mail);
	Boolean validateEmail(String email);
	
	public LoginDetails getUserFromId(String uId);
}
