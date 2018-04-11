package com.shoppingCart.DaoIMPL;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shoppingCart.Daos.LoginDetailsDao;
import com.shoppingCart.Model.LoginDetails;

public class LoginDetailsDaoIMPL extends HibernateDaoSupport implements LoginDetailsDao {

	@Override
	public LoginDetails validateUserLogin(String userID, String password) {
		@SuppressWarnings("unchecked")
		List<Object[]> list= (List<Object[]>)getHibernateTemplate().find(
				"select userId,fullName from LoginDetails where userId=? and userPassword=?",userID,password);
		LoginDetails login = new LoginDetails();
		for(Object[] obj:list){
			login.setUserId((obj[0].toString()));
			login.setFullName((obj[1].toString()));
		}
		return login;
		
	}
	
	@Override
	public Boolean registerUser(String uid, String pwd, String fname, String email) {
		boolean updated = false;
		LoginDetails updateDetail = new LoginDetails();
		try{
			updateDetail.setUserId(uid);
			updateDetail.setUserPassword(pwd);
			updateDetail.setFullName(fname);
			updateDetail.setContact(email);
			getHibernateTemplate().save(updateDetail);
			updated = true;
		}catch (Exception e) {
			System.err.println("Adding User Failed"+e.getMessage().toString());
		}
		
		return updated;
	}
	
	@Override
	public Boolean validateUserName(String uId){
		boolean valid = false;
		@SuppressWarnings("unchecked")
		List<Object[]> list= (List<Object[]>)getHibernateTemplate().find(
				"select userId,fullName from LoginDetails where userId=?",uId);
		if(list.size()==0)
			valid = true;
		return valid;
		
	}
	
	@Override
	public Boolean validateEmail(String email){
		boolean valid = false;
		@SuppressWarnings("unchecked")
		List<Object[]> list= (List<Object[]>)getHibernateTemplate().find(
				"select userId,fullName from LoginDetails where contact=?",email);
		if(list.size()==0)
			valid = true;
		return valid;
		
	}

	@Override
	public LoginDetails getUserFromMail(String mail) {
		@SuppressWarnings("unchecked")
		List<Object[]> list= (List<Object[]>)getHibernateTemplate().find(
				"select userId,fullName,contact from LoginDetails where contact=?",mail);
		LoginDetails user = new LoginDetails();
		for(Object[] obj:list){
			user.setUserId((obj[0].toString()));
			user.setFullName((obj[1].toString()));
			user.setContact(obj[2].toString());
		}
		return user;
	}
	
	@Override
	public LoginDetails getUserFromId(String uId) {
		@SuppressWarnings("unchecked")
		List<Object[]> list= (List<Object[]>)getHibernateTemplate().find(
				"select userId,fullName,contact from LoginDetails where userId=?",uId);
		LoginDetails user = new LoginDetails();
		for(Object[] obj:list){
			user.setUserId((obj[0].toString()));
			user.setFullName((obj[1].toString()));
			user.setContact(obj[2].toString());
		}
		return user;
	}

}
