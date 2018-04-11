package com.shoppingCart.delegates;
/**
 * 
 * @author yashaswivemula
 *This Class is mainly used to set parameters for mail
 */
public class MailParams {
	
	private String toEmail;
	private String message;
	private String subject;
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public MailParams(String toEmail, String message, String subject) {
		super();
		this.toEmail = toEmail;
		this.message = message;
		this.subject = subject;
	}
	public MailParams() {
		super();
	}
	

}
