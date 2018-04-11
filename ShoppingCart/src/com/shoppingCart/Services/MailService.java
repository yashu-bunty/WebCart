package com.shoppingCart.Services;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;

import com.shoppingCart.Model.LoginDetails;
import com.shoppingCart.Model.OrderDetails;
import com.shoppingCart.Model.VendorDetails;
import com.shoppingCart.delegates.MailParams;

import javax.activation.*;  

public class MailService {
	  
	
	 
	 public void sendEmail(MailParams mp){  
		 
		// Recipient's email ID needs to be mentioned.
	     // String to = "yashaswi.vemula@gmail.com";

	      // Sender's email ID needs to be mentioned
	      String from = Constants.mail_User_Name;
	      
	      
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", Constants.host_name);
	      props.put("mail.smtp.port", "25");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(Constants.mail_User_Name, Constants.mail_user_password);
	            }
	      });

	      try {
	            // Create a default MimeMessage object.
	    	  Message message = new MimeMessage(session);

	    	  // Set From: header field of the header.
	    	  message.setFrom(new InternetAddress(from));

	    	  // Set To: header field of the header.
	    	  message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mp.getToEmail()));

	    	  // Set Subject: header field
	    	  message.setSubject(mp.getSubject());

	    	  // Send the actual HTML message, as big as you like
	    	  message.setContent(mp.getMessage(),"text/html");

	    	  // Send message
	    	  Transport.send(message);

	    	  System.out.println("Sent message successfully....");
	    	  
	      } catch (MessagingException e) {
	    	  e.printStackTrace();
	    	  throw new RuntimeException(e);
	      }
		 
	   }
	 
	 //Send User Password reset Link
	 public void sendPwdResetMail(LoginDetails user){
		 String msg="<h1>Hi "+user.getFullName()+",</h1></br> "
		 		+ "<a href=\"http://localhost:8080/ShoppingCart/paswordReset.html\">"
             + "Click Here</a>";
		 MailParams mp=new MailParams(user.getContact(),msg,Constants.sub_pwd_reset);
		 sendEmail(mp);
	 }
	 
	 //send vendor mail regarding item Quantities
	 public void sendEmailToVendor(VendorDetails vendor,int itemQty,String itemName){
		 itemQty = itemQty+10;
		 String msg = "<h2>Hi  "+vendor.getVendorName()+
				 " Vendor,</h2><br>Item : <b>"+itemName+"</b>, is running out of stock: <b>"
				 +"</b>.<br> Please deliver the product as soon as Possible.<br> "
				 + "<a style=\"font-weight:bold;\">Required Quantity :"+itemQty + "</a><br>"
				 + "For any issues or Conserns please contact Yash Shop Administrator";
		 MailParams mp=new MailParams(vendor.getVendorEmail(),msg,Constants.sub_order_request);
		 
		 sendEmail(mp);
	 }
	 
	 //Sends Purchase details to User
	 public void sendPurchaseMailToUser(List<OrderDetails> orderlist,Double total){
		 String msg = "<h2>Hi "+orderlist.get(0).getLoginDetails().getFullName().toString()+",</h2><br>";
		 msg += "<div align=\"center\">";
		 msg += "<a><b>Please find the below Purchase Details For Order Number:"
				 +orderlist.get(0).getOrderId() + "</b></a><br><br>";//Updated After Intermediate Demo
		 msg +="<table border=\"3\">"
				 +"<tr>"
				 +	"<th>Item Name</th>"
				 +	"<th>Quantity</th>"
				 +	"<th>Price Per Product</th>"
				 +	"<th>Delivery Status</th>"
				 +"</tr>";
		 		
		 for(OrderDetails order:orderlist){
			 msg +=	"<tr>"
					 + "<td>"+order.getInventory().getItemName().toString()+"</td>"
					 + "<td>"+order.getQuantity().toString()+"</td>"
					 + "<td>"+order.getInventory().getItemPrice().toString()+"</td>"
					 + "<td>"+order.getOrderStatus().toString()+"</td>"
				  + "</tr>";
			 		
		 }
		 msg += "</table><br><br>"
		 		+ "<a><b>Total Amount : "+total+"</b></a></div>";
		 
		 MailParams mp = new MailParams(orderlist.get(0).getLoginDetails().getContact().toString(),msg,Constants.sub_purchase_details);
		 sendEmail(mp);
	 }
	 
	 public void sendItemUpdatedStatusToUser(List<OrderDetails> orderlist){
		 String msg = "<h2>Hi  "+orderlist.get(0).getLoginDetails().getFullName()+",</h2><br>"
				+"	Order Number <a style=\"font-weight:bold;\">"+orderlist.get(0).getOrderId().toString()+"</a>, status update<br><br><br>";
				msg +="<table border=\"3\">"
						 +"<tr>"
						 +	"<th>Item Name</th>"
						 +	"<th>Quantity</th>"
						 +	"<th>Price Per Product</th>"
						 +	"<th>Delivery Status</th>"
						 +"</tr>";
				 		
				 for(OrderDetails order:orderlist){
					 msg +=	"<tr>"
							 + "<td>"+order.getInventory().getItemName().toString()+"</td>"
							 + "<td>"+order.getQuantity().toString()+"</td>"
							 + "<td>"+order.getInventory().getItemPrice().toString()+"</td>"
							 + "<td>"+order.getOrderStatus().toString()+"</td>"
						  + "</tr>";
					 		
				 }
				 msg += "</table><br><br>";
		 MailParams mp=new MailParams(orderlist.get(0).getLoginDetails().getContact(),msg,
				 Constants.sub_purchase_status_update+": Order # "+orderlist.get(0).getOrderId().toString()); //Updated After Intermediate Demo
		 
		 sendEmail(mp);
	 }
	 
	 
	 
	 
	 
	 
	 public static void main(String[] args) {
	      // Recipient's email ID needs to be mentioned.
	      String to = "yashaswi.vemula@gmail.com";

	      // Sender's email ID needs to be mentioned
	      String from = Constants.mail_User_Name;
	      
	      
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", Constants.host_name);
	      props.put("mail.smtp.port", "25");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(Constants.mail_User_Name, Constants.mail_user_password);
	            }
		});

	      try {
	            // Create a default MimeMessage object.
	            Message message = new MimeMessage(session);

	   	   // Set From: header field of the header.
		   message.setFrom(new InternetAddress(from));

		   // Set To: header field of the header.
		   message.setRecipients(Message.RecipientType.TO,
	              InternetAddress.parse(to));

		   // Set Subject: header field
		   message.setSubject(Constants.sub_pwd_reset);
		   
		   String msg = "<h2>Hi xxxxxxxxxxx,</h2><br>";
			 msg += "<div align=\"center\">";
			 msg += "<a><b>Please find the below Purchase Details</b></a><br><br>";
			 msg +="<table border=\"3\">"
					 +"<tr>"
					 +	"<th>Item Name</th>"
					 +	"<th>Quantity</th>"
					 +	"<th>Price Per Product</th>"
					 +	"<th>Delivery Status</th>"
					 +"</tr>";
			 		
			 
				 msg +=	"<tr>"
						 + "<td>xxxxxxxxxx</td>"
						 + "<td>yyyyyyyyyyy</td>"
						 + "<td>zzzzzzzzzzzz</td>"
						 + "<td>aaaaaaaaaa</td>"
					  + "</tr>";
				 		
			
			 msg += "</div></table>";

		   // Send the actual HTML message, as big as you like
		   message.setContent(msg,"text/html");

		   // Send message
		   Transport.send(message);

		   System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
		   e.printStackTrace();
		   throw new RuntimeException(e);
	      }
	   }
	
}
