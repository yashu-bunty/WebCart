package com.shoppingCart.Services;

import java.io.File;

public class Constants {
	
	public static final String mail_User_Name = "yashshoppingcart@gmail.com";
	public static final String mail_user_password = "yashshopping";
	

	public static final String host_name = "smtp.gmail.com";
	
	public static final String delivery_ready = "Ready to Deliver";
	public static final String delivery_pending ="Order Delivery Pending";
	
	public static final String sub_order_request= "Yash Shop Order Request";
	public static final String sub_purchase_details= "Yash Shop Purchase Details";
	public static final String sub_purchase_status_update = "Yash Shop Order Status Update";
	
	public static final String sub_pwd_reset = "Yash Shop Password Assistance";
	
	public static final String img_UPLOAD_DIRECTORY = "/Users/yashaswivemula/Documents/AdvanceSystemWS/ShoppingCart/WebContent/resources/img";
	
	public static final String eport_excel = System.getProperty("user.home")+File.separator+"Desktop";
	public static final String export_file_name = "Yash_Shop_Inventory.xls";
	public static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
	public static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	public static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
}
