package com.genpact.IncidentTracker.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class AmazonClient {

	private AmazonS3 s3client;

	@Value("$.endPointUrl}")
	private String endpointUrl;
	@Value("${bucketName}")
	private String bucketName;
	@Value("${accessKey}")
	private String accessKey;
	@Value("${secretKey}")
	private String secretKey;

	private Logger logger = LoggerFactory.getLogger(AmazonClient.class);
	
	@PostConstruct
	private void initializeAmazon() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
		this.s3client = new AmazonS3Client(credentials);
	}

	public String uploadFile(MultipartFile multipartFile) throws IOException {

		String publicFileUrl = "";
		try {
			File file = convertMultiPartToFile(multipartFile);
			String fileName = generateFileName(multipartFile);
			publicFileUrl = uploadFileTos3bucket(fileName, file);
			file.delete();
		} /*catch (Exception e) {
			e.printStackTrace();
		}*/
		catch (AmazonServiceException ase) {
	          logger.info("Caught an AmazonServiceException from GET requests, rejected reasons:");
	          logger.info("Error Message:    " + ase.getMessage());
	          logger.info("HTTP Status Code: " + ase.getStatusCode());
	          logger.info("AWS Error Code:   " + ase.getErrorCode());
	          logger.info("Error Type:       " + ase.getErrorType());
	          logger.info("Request ID:       " + ase.getRequestId());
	          
	          
	            } catch (AmazonClientException ace) {
	              logger.info("Caught an AmazonClientException: ");
	                logger.info("Error Message: " + ace.getMessage());
	            } catch (IOException ioe) {
	              logger.info("IOE Error Message: " + ioe.getMessage());
	              
	            }
		return publicFileUrl;

	}

	public String deleteFileFromS3Bucket(String fileUrl) {
		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
		s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
		return "Successfully deleted";
	}

	private String uploadFileTos3bucket(String fileName, File file) {
		
		//s3client.putObject(
			//	new PutObjectRequest(bucketName, fileName, file));
		s3client.putObject(
				new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
		String publicFilePath= s3client.getUrl(bucketName, fileName).toExternalForm();  
		System.out.println("Path =="+publicFilePath);
		return publicFilePath;
	}

	private String generateFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

}