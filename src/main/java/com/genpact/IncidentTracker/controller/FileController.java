package com.genpact.IncidentTracker.controller;


import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.genpact.IncidentTracker.model.FileDetails;
import com.genpact.IncidentTracker.service.AmazonClient;
import com.genpact.IncidentTracker.service.FileService;

@RestController
public class FileController {

	@Autowired
	private FileService fService;
	@Autowired 
	private AmazonClient aS3Service;
	
	@RequestMapping(method = RequestMethod.POST, value = "/addIncidentFile", consumes= {"multipart/form-data"})
	public void addIncidentFile(@RequestParam("fileData") MultipartFile file ,@RequestParam("incId") int incidentId) {
		 FileDetails fDetails = new FileDetails();
		 try {
		 fDetails.setIncidentId(incidentId);
		 fDetails.setFileType(file.getContentType());
		 System.out.println(file.getContentType());
		 String fileUrl = aS3Service.uploadFile(file);
		 fDetails.setFileUrl(fileUrl);
		 fService.addIncidentFile(fDetails);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
	    
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addIncidentCommentFile", consumes= {"multipart/form-data"})
	public void addIncidentCommentFile(@RequestParam("fileData") MultipartFile file ,@RequestParam("cmntId") int commentId) {
		 FileDetails fDetails = new FileDetails();
		 try {
		 fDetails.setCommentId(commentId);
		 fDetails.setFileType(file.getContentType());
		 System.out.println(file.getContentType());
		 String fileUrl = aS3Service.uploadFile(file);
		 fDetails.setFileUrl(fileUrl);
		 fService.addIncidentCommentFile(fDetails);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
	    
	}
	
	
	
}
