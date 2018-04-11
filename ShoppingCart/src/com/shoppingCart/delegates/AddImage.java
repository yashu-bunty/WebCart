package com.shoppingCart.delegates;

import java.io.File;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.shoppingCart.Services.Constants;

public class AddImage {
	
	FileItem item;

	
	

	public AddImage(FileItem item) {
		super();
		this.item = item;
	}




	public String uploadImage(){
		// configures upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(Constants.THRESHOLD_SIZE);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		 
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(Constants.MAX_FILE_SIZE);
		//upload.setSizeMax(Constants.MAX_REQUEST_SIZE);
		
		String fileName  = "";
		
		try {
			
				// processes only fields that are not form fields
				if (!item.isFormField()) {
					fileName = new File(item.getName()).getName();
					String filePath = Constants.img_UPLOAD_DIRECTORY + File.separator + fileName;
					File storeFile = new File(filePath);
		 
					// saves the file on disk
					item.write(storeFile);
					fileName = "resources"+ File.separator +"img" +File.separator+ fileName;
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
		
	}
	
	
}
