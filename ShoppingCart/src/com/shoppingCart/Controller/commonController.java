package com.shoppingCart.Controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingCart.Model.Inventory;
import com.shoppingCart.Model.LoginDetails;
import com.shoppingCart.Model.OrderDetails;
import com.shoppingCart.Model.VendorDetails;
import com.shoppingCart.Services.Constants;
import com.shoppingCart.Services.InventoryService;
import com.shoppingCart.Services.LoginDetailsService;
import com.shoppingCart.Services.MailService;
import com.shoppingCart.Services.OrderDetailsService;
import com.shoppingCart.Services.VendorService;
import com.shoppingCart.delegates.AddImage;
import com.shoppingCart.delegates.Cart;
import com.shoppingCart.delegates.CartDetails;
import com.shoppingCart.delegates.TrackOrders;

@Controller
public class commonController {
	
	private HttpSession hSession;
	private CartDetails cdetails = new CartDetails();
	Inventory updateInv = new Inventory();
	
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private LoginDetailsService loginDetailService;
	
	@Autowired
	private OrderDetailsService orderDetailService;
	
	@Autowired
	private VendorService vendorService;
	
	//Loads Home Page
	@RequestMapping(value="/yashShoppingHome")
	public ModelAndView loadHomepage(Inventory inventory,HttpServletRequest request){
		hSession =request.getSession(true);
		String fltroption = request.getParameter("filter");
		if(fltroption!=null && !fltroption.equals("")){
			cdetails.setInventoryList(inventoryService.getInventoryItemsByProduct(fltroption));
		}else{
			cdetails.setInventoryList(inventoryService.getInventoryItems());
		}
		
		cdetails.setProductGroup(inventoryService.getProductGroups());		
		return new ModelAndView("/HeaderFooter.jsp?page=yashShoppingHome","setproductList",cdetails);
	}
	

	//Adds Items to the Cart within the Session
	@RequestMapping(value="/addCart")
	public ModelAndView addCart(Inventory inventory,HttpServletRequest request){
		
		hSession = request.getSession(true);
		Cart cart = (Cart) hSession.getAttribute("Cart1");
		
		if(cart == null){
			
			cart = new Cart();
			hSession.setAttribute("Cart1",cart );//Adding Cart to Session Attribute
		}
		
		String itemId = request.getParameter("Add");
		Inventory inv = null;
		
		if(!(itemId == null)){
			inv = inventoryService.getItemByIdSrvc(itemId);
			cart.add(itemId, inv);
		}
		
		cart.getTotal();// updates the total cart price
		
		
		if(cdetails==null)
			loadHomepage(inventory, request);//if cdetails is null below line never executes
		
		
		return new ModelAndView("/HeaderFooter.jsp?page=yashShoppingHome","setproductList",cdetails);
	}
	
	//This is called from the Added Cart page, to remove items
	@RequestMapping(value="/userCart")
	public ModelAndView showCart(Inventory cartpojo,HttpServletRequest request){
		   Cart cart = (Cart) hSession.getAttribute("Cart1");
		   String removeParam = request.getParameter("Remove");
		   String rQty = request.getParameter("sendQty");
		   String addQuantityId = request.getParameter("valId");
		   
			if (removeParam != null && cart!=null && removeParam !="0") {
				// Removes the items from the Shopping Cart
				cart.remove(removeParam);
			}
			
			if(addQuantityId != null && cart != null && rQty != null && rQty != "" ){
				int prsntQntyt = cart.getItemsList().get(addQuantityId).getCartQuantity();
				int reqstdQty = Integer.parseInt(rQty);
				if(prsntQntyt != reqstdQty){
					cart.updateQty(addQuantityId,reqstdQty);
				}
			}
		   return new ModelAndView("/HeaderFooter.jsp?page=userCart");
	}
	   
	@RequestMapping(value="/loginRegister")
	public ModelAndView navToLoginPage(Inventory cartpojo,HttpServletRequest request){
		String type = request.getParameter("LoginType");
		if(type !=null && type.equals("Admin")){
			hSession.invalidate();
			hSession = request.getSession(true);
			return new ModelAndView("/HeaderFooter.jsp?page=loginRegister","adminLogin","Yes");
		}
		 return new ModelAndView("/HeaderFooter.jsp?page=loginRegister");
	}
	
	//This is called to send the Password Reset Link to User Email
	@RequestMapping(value="/sendResetMail")
	public ModelAndView sendMail(Inventory cartpojo,HttpServletRequest request){
		   MailService ms= new MailService();
		   String mail = request.getParameter("emailId");
		   LoginDetails user = loginDetailService.getUserDatafromMailId(mail);
		   if(user.getFullName() != null)
			   ms.sendPwdResetMail(user);
		   else{
			   return new ModelAndView("/HeaderFooter.jsp?page=loginRegister","message", "User with Entered Mail Id is not Registered with Us");
		   }
		   
		   request.setAttribute("message", "Password Reset Link has been sent to Registered Mail Id");
		   return new ModelAndView("/HeaderFooter.jsp?page=yashShoppingHome","setproductList",cdetails);
	}
	
	//This method is called to reset the user password
	@RequestMapping(value="/paswordReset")
	public ModelAndView passwordReset(Inventory cartpojo,HttpServletRequest request){
		   
		   String npwd = request.getParameter("npwd");
		   String cpwd = request.getParameter("cpwd");
		   if(npwd !=null && cpwd !=null){//verifies if the call is from password reset page
			   if(npwd.equals("") || cpwd.equals(""))
				   return new ModelAndView("/HeaderFooter.jsp?page=passwordReset","message","Please Don't Leave Fields Blank");
			   else if(!npwd.equals(cpwd))//validates new password and confirm password
				   return new ModelAndView("/HeaderFooter.jsp?page=passwordReset","message","Passwords Do not Match");
			   else//redirects to Login page once credentials are updated
				   return new ModelAndView("/HeaderFooter.jsp?page=loginRegister","message", "Please Login with new Credentials");
		   }
		   return new ModelAndView("/HeaderFooter.jsp?page=passwordReset");
	   }
	
	@RequestMapping(value="/loginUser")
	public ModelAndView login(Inventory cartpojo,HttpServletRequest request){
		String UserName = request.getParameter("uname");
		String password = request.getParameter("upwd");
		String adminName = request.getParameter("adminName");
		String adminPwd = request.getParameter("adminPwd");
		String fullName="";
		if(hSession == null || request.isRequestedSessionIdValid())
			hSession = request.getSession(true);
		if(adminName == null && adminPwd == null){
			LoginDetails returnedDetails = loginDetailService.validateUserLogin(UserName, password);
			if(returnedDetails!=null && returnedDetails.getFullName()!=null 
				&& !returnedDetails.getFullName().equals("") && hSession != null){
				fullName = returnedDetails.getFullName();
				hSession.setAttribute("userID", returnedDetails.getUserId());
				return new ModelAndView("/HeaderFooter.jsp?page=paymentPage","cartHistory",orderDetailService.getCartHistoryOfUser(returnedDetails.getUserId()));
			 }else{
				   fullName = "Invalid Credentials";
				   return new ModelAndView("/HeaderFooter.jsp?page=loginRegister","message",fullName);
			 }
		}else{
			LoginDetails returnedDetails = loginDetailService.validateUserLogin(adminName, adminPwd);
			
			if(returnedDetails!=null && returnedDetails.getFullName()!=null 
					&& !returnedDetails.getFullName().equals("")){
					fullName = returnedDetails.getFullName();
					runningOutOfStock();
					return new ModelAndView("/HeaderFooter.jsp?page=AddUpdateInventory","sValue","Search");
				 }else{
					   fullName = "Invalid Credentials";
					   request.setAttribute("adminLogin","Yes");
					   return new ModelAndView("/HeaderFooter.jsp?page=loginRegister","message",fullName);
				 }
			
		}
		
	}
	
	public void runningOutOfStock(){
		List<Inventory> invList = inventoryService.getInventoryItems();
		List<Inventory> runningOutList = new ArrayList<Inventory>();
		for(int i =0;i<invList.size();i++){
			if(invList.get(i).getItemQunatity() < 5)
			runningOutList.add(invList.get(i));
		}
		hSession.setAttribute("runningOut", runningOutList);
	}
	
	@RequestMapping(value="/addUser")
	public ModelAndView addUser(Inventory cartpojo,HttpServletRequest request){
		   String UserName = request.getParameter("uname").toString();
		   String password = request.getParameter("password").toString();
		   String fname = request.getParameter("fullName").toString();
		   String email = request.getParameter("emailId");
		   if(email.isEmpty()  || UserName.isEmpty() || password.isEmpty() || fname.isEmpty()){
			   return new ModelAndView("/HeaderFooter.jsp?page=userRegister","Message","Enter Valid Details");
		   }
		   if(!loginDetailService.validateUid(UserName,email)){
			   return new ModelAndView("/HeaderFooter.jsp?page=userRegister","Message","Selected User Name/mailId matches our records please select unique id");

		   }
		   loginDetailService.addUser(UserName, password,fname,email);
		   hSession.setAttribute("userID", fname);
		   
		   return new ModelAndView("/HeaderFooter.jsp?page=paymentPage");
	 }
	
	@RequestMapping(value="/orderConfirmed")
	public ModelAndView completeOrder(Inventory cartpojo,HttpServletRequest request){
		
		   hSession = request.getSession(true);
		   Inventory cartitem = new Inventory();
		   MailService ms= new MailService();
		   List<OrderDetails> orderlist = new ArrayList<OrderDetails>();
		   OrderDetails splitOrder = new OrderDetails();
		   Cart cart = (Cart) hSession.getAttribute("Cart1");
		   String userId = (String) hSession.getAttribute("userID");
		   if(cart!=null){
			   for(int i=0;i<cart.getItems().size();i++){
				   OrderDetails order = new OrderDetails();
				   LoginDetails user = new LoginDetails();
				   cartitem  =  (Inventory) cart.getItems().get(i).getCartItems();
				   user = loginDetailService.getUserFromId(userId);
				   order.setInventory(cartitem);
				   order.setLoginDetails(user);
				   order.setQuantity(cart.getItems().get(i).getCartQuantity());
				   Inventory qtyCheck = inventoryService.getItemByIdSrvc(Integer.toString(cartitem.getItemId()));
				 //verifying Quantity of item to update status
				   if(qtyCheck.getItemQunatity()-order.getQuantity() >= 0)
					   order.setOrderStatus(Constants.delivery_ready);
				   else{//setting delivery pending for only Quantity that will not available
					   int pendingQuantity = order.getQuantity() - qtyCheck.getItemQunatity();
					   
					   for(int j =0;j<2;j++){
						   if(j==0 && pendingQuantity != 0){//adding pending delivery to only qty not availble
							   order.setQuantity(pendingQuantity);
							   order.setOrderStatus(Constants.delivery_pending);
							   orderlist.add(order);
						   }else{//for other quantity it is delivery ready
							   splitOrder.setInventory(cartitem);
							   splitOrder.setLoginDetails(user);
							   splitOrder.setQuantity(qtyCheck.getItemQunatity());
							   splitOrder.setOrderStatus(Constants.delivery_ready);
							   orderlist.add(splitOrder);
						   }
						   
						   
					   }
					   if(qtyCheck.getItemQunatity() - order.getQuantity() < 5){
						 //verifying if pending Order Quantity -- Updated After Intermediate Demo
							 int qtySum = 0;
							 if(pendingQuantity > 0){
									 qtySum += pendingQuantity;
							 }
							//verifying if pending Order Quantity -- Updated After Intermediate Demo
						   vendorService.remindVendor(qtyCheck,qtySum);//+qtyCheck.getItemQunatity());
					   }
					   continue;//to skip to next iteration
				   }
					   
				 //verifying Quantity of item to send mail to Vendor to deliver product
				 //When item Quantity is less than 5 in Inventory, mail is sent for every order
				 if(qtyCheck.getItemQunatity() - order.getQuantity() < 5){
					 //verifying if pending Order Quantity -- Updated After Intermediate Demo
					 int pendingQuantity =order.getQuantity() - qtyCheck.getItemQunatity();
					 int qtySum = 0;
					 if(pendingQuantity > 0){
							 qtySum += pendingQuantity;
					 }
					//verifying if pending Order Quantity -- Updated After Intermediate Demo
					 vendorService.remindVendor(qtyCheck,qtySum);//+qtyCheck.getItemQunatity());
				 }
				 
				   orderlist.add(order);
			   }
			   int OrderId = orderDetailService.saveOrders(orderlist);
			   if(OrderId > 0){//order is saved to database
				   orderlist.get(0).setOrderId(OrderId);
				 //once order is successfully updated,its quantity is updated in Inventory
				   inventoryService.updateInvQty(orderlist);
				   
				 //Send mail to user regarding Purchase and Total
				   ms.sendPurchaseMailToUser(orderlist,cart.getTotal());
			   }
			   else{
				   System.out.println("Error Occured while inserting Order Details data");
			   }
		   }
		   hSession.invalidate();
		   return new ModelAndView("/HeaderFooter.jsp?page=thankyou");
	   }
	
	
	
//	@RequestMapping(value="/addupdateinventory")
//	public ModelAndView adminLogin(Inventory cartpojo,HttpServletRequest request){
//		
//		return new ModelAndView("loginRegister","adminLogin","Yes");
////		return new ModelAndView("AddUpdateInventory","sValue","Search");
//	}
	
	//Adds Vendor and New Item to Inventory through Admin Login
	@RequestMapping(value="/addItem")
	public ModelAndView addNewItem(HttpServletRequest request){
		
		
		String userMessage = "";
		FileItem fileItem = null;
		
		HashMap<String,String> itemsList = new HashMap<String, String>();
		//this to handle multipart data to get Form data
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		
			for (FileItem item : items) {
				if (item.isFormField()) {
					//Retrieving all input except type="file"
					itemsList.put(item.getFieldName(), item.getString());
				}else{
					fileItem = item;//Retrives Image File
				}
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(fileItem!=null){
			AddImage ai = new AddImage(fileItem);
			String imageName = ai.uploadImage();
			String vendorId = itemsList.get("vId");
			String vendorName = itemsList.get("vName");
			String vendorEmail = itemsList.get("vEmail");
			String itemId = itemsList.get("itemId");
			String itemName = itemsList.get("itemName");
			String itemPrice = itemsList.get("itemPrice");
			String itemGroup = itemsList.get("itemGroup");
			String itemQty = itemsList.get("itemqty");
			
			VendorDetails vendor = new VendorDetails(Integer.parseInt(vendorId),vendorName,vendorEmail);
			
			Inventory inventory = new Inventory(Integer.parseInt(itemId),itemName,itemPrice,imageName,itemGroup,Integer.parseInt(itemQty),Integer.parseInt(vendorId));
			
			if(vendorService.addVendor(vendor)){//Adding vendor details to database
				if(inventoryService.addNewItem(inventory))//Adding new item to Inventory
					userMessage = "Added Successfully";
				else
					userMessage = "There is a problem adding Vendor,Please verify details";
			}else{
				userMessage = "There is a problem adding Vendor,Please verify details";
			}
		}else
			return new ModelAndView("/HeaderFooter.jsp?page=AddUpdateInventory","message","Please Add Image");
			
		
		runningOutOfStock();	
		return new ModelAndView("/HeaderFooter.jsp?page=AddUpdateInventory","message", userMessage);
		
	}
	
	
	
	//This method is called to export Excel to User desktop
	@RequestMapping(value="/exportInvetory")
	public ModelAndView exportInventory(HttpServletRequest request){
		
		inventoryService.exportInventory(vendorService.getVendorsList());//Send vendor list to Export
		runningOutOfStock();
		request.setAttribute("sValue", "Search");
		return new ModelAndView("/HeaderFooter.jsp?page=AddUpdateInventory","message", "File:<strong>"+Constants.export_file_name+"</strong> downloaded to Desktop");
	}
	
	
	//This method is called when Admin updates Quantity or price of the Inventory Items
	@RequestMapping(value="/updateQty")
	public ModelAndView updateQunatity(HttpServletRequest request){
		String qty = request.getParameter("qty");
		String price = request.getParameter("price");
		String itemID = request.getParameter("itemId");
		List<OrderDetails> pendingOrders = new ArrayList<OrderDetails>();
		MailService ms = new MailService();
		
		if(itemID.equals(""))
			return new ModelAndView("/HeaderFooter.jsp?page=AddUpdateInventory","message", "Please enter Item Id");
		
		boolean updated = false;
		String msg= "";
		if((qty == null || qty.equals("")) || (updateInv.getItemId() != 0 && Integer.parseInt(itemID) != updateInv.getItemId())){//This condition verifies whether user is searching or updating
			//Enters the loop when user searches an item to update
			
			
				updateInv = inventoryService.getItemByIdSrvc(itemID);
				if(updateInv.getItemQunatity() == null){
					request.setAttribute("sValue", "Search");
					return new ModelAndView("/HeaderFooter.jsp?page=AddUpdateInventory","message", "Invalid Item ID");
				}
				request.setAttribute("qValue", updateInv.getItemQunatity());
				request.setAttribute("pValue", updateInv.getItemPrice());
				request.setAttribute("id", updateInv.getItemId());
				pendingOrders = orderDetailService.getOrdersWithPendingStatus(updateInv.getItemId());
				int minRequiredQunatity = 0;
				for(OrderDetails od:pendingOrders){
					minRequiredQunatity+= od.getQuantity();
				}
				request.setAttribute("minReqQuantity", "Minimum Required Quantity is :"+minRequiredQunatity);
				return new ModelAndView("/HeaderFooter.jsp?page=AddUpdateInventory","sValue", "Update Price/Quantity");
			
		}else{
			//Here when user updates Qty or price and hits Update button
			if(!qty.equals(Integer.toString(updateInv.getItemQunatity()))){
				updated = inventoryService.updateInvQty(qty,updateInv.getItemId());
				
				//Once Inventory is updated with Quantity Verify Pending deliveries
				if(updated){
					pendingOrders = orderDetailService.getOrdersWithPendingStatus(updateInv.getItemId());
					if(pendingOrders.size()>0){//CHECKS IF THERE ARE ANY PENDING DELIVERIES 
						for(OrderDetails pod:pendingOrders){//POD stands for Pending Orders
							Inventory inv= new Inventory();
							LoginDetails ld = new LoginDetails();
							inv = inventoryService.getItemByIdSrvc(Integer.toString(pod.getInventory().getItemId()));
							ld = loginDetailService.getUserFromId(pod.getLoginDetails().getUserId());
							pod.setInventory(inv);
							pod.setLoginDetails(ld);
							List<OrderDetails> ods = new ArrayList<OrderDetails>();
							//UPDATE ORDER TABLE WITH NEW STATUS
							if(pod.getQuantity() <= inv.getItemQunatity()){
								pod.setOrderStatus(Constants.delivery_ready);
								
								//UPDATE STATUS IN ORDER TABLE
								orderDetailService.updateOrderStatus(pod);///********
								
								//UPDATE QUANTITY IN INVENTORY
								inventoryService.updateInvQty(Integer.toString(inv.getItemQunatity() - pod.getQuantity()), pod.getInventory().getItemId());
								ods.add(pod);
								//Send Updated Status Mail to User
								ms.sendItemUpdatedStatusToUser(ods);
								
							}else if(inv.getItemQunatity() > 0){
								int remainingQty = pod.getQuantity() - inv.getItemQunatity();
								OrderDetails splitPOD= new OrderDetails(); //splitPOD stands for orginal pending is spllited
								
								pod.setQuantity(inv.getItemQunatity());
								pod.setOrderStatus(Constants.delivery_ready);
								orderDetailService.updateOrderStatus(pod);
								ods.add(pod);
								
								splitPOD.setInventory(pod.getInventory());
								splitPOD.setLoginDetails(pod.getLoginDetails());
								splitPOD.setOrderId(pod.getOrderId());
								splitPOD.setQuantity(remainingQty);
								splitPOD.setOrderStatus(Constants.delivery_pending);
								orderDetailService.saveSingleOrder(splitPOD);
								ods.add(splitPOD);
								
								inventoryService.updateInvQty(Integer.toString(0), pod.getInventory().getItemId());
								ms.sendItemUpdatedStatusToUser(ods);
								
								vendorService.remindVendor(pod.getInventory(), remainingQty);
							}
						}
						
					}
				}
				
			}else if(!price.equals(updateInv.getItemPrice()))
				updated = inventoryService.updateInvPrice(price,updateInv.getItemId());
			
		}
		if(updated)
			msg="successfully Updated";
		else
			msg="Issue occured while updating";
		
		runningOutOfStock();
		request.setAttribute("sValue", "Search");
		request.removeAttribute("qValue");
		request.removeAttribute("pValue");
		return new ModelAndView("/HeaderFooter.jsp?page=AddUpdateInventory","message", msg);
	}	
	
	//Code changes done after Intermediate Demo
		@RequestMapping(value="/trackVendorOrders")
		public ModelAndView trackVendorOrders(HttpServletRequest request){
			List<Inventory> invList = cdetails.getInventoryList();
			List<OrderDetails> pendingOrders = new ArrayList<OrderDetails>();
			List<TrackOrders> vendorOrderList = new ArrayList<TrackOrders>();
			for(int i=0;i<invList.size();i++){
				if(invList.get(i).getItemQunatity() < 5){
					pendingOrders = orderDetailService.getOrdersWithPendingStatus(invList.get(i).getItemId());
					int minRequiredQunatity = 10;
					for(OrderDetails od:pendingOrders){
						minRequiredQunatity+= od.getQuantity();
					}
					TrackOrders trackPendingOrders = new TrackOrders();
					trackPendingOrders.setItemId(invList.get(i).getItemId());
					trackPendingOrders.setItemName(invList.get(i).getItemName());
					trackPendingOrders.setVendorId(invList.get(i).getVendorId());
					trackPendingOrders.setVendorName(vendorService.getVendorDetails(invList.get(i).getVendorId()).getVendorName());
					trackPendingOrders.setOrderStatus("Ordered: "+minRequiredQunatity+" Items");
					vendorOrderList.add(trackPendingOrders);
				}
			}
			
			return new ModelAndView("/HeaderFooter.jsp?page=vendorOrderListView","VendorOrderList",vendorOrderList);
		}
}
