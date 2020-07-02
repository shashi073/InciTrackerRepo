package com.genpact.IncidentTracker.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.genpact.IncidentTracker.model.ImageFile;
import com.genpact.IncidentTracker.model.VideoFile;
import com.genpact.IncidentTracker.service.FileService;

@RestController
public class FileController {

	@Autowired
	private FileService cService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/addVideo", consumes= {"multipart/form-data"})
	public void addComments(@RequestParam("fileData") MultipartFile file ,@RequestParam("incId") int incidentId) {
		 VideoFile vfile = new VideoFile();
		 try {
		 vfile.setIncidentId(incidentId);
		 vfile.setVideoDescription(file.getContentType());
		 vfile.setVideoName(file.getOriginalFilename());
		 vfile.setVideoData(file.getBytes());
		 cService.addVideo(vfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getVideos")
	public List<VideoFile> getVideos(@RequestParam int incidentId) {
	     return cService.getVideo(incidentId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addImage", consumes= {"multipart/form-data"})
	public void addImage(@RequestParam("fileData") MultipartFile file ,@RequestParam("incId") int incidentId) {
		 ImageFile ifile = new ImageFile();
		 try {
		 ifile.setIncidentId(incidentId);
		 ifile.setImageDescription(file.getContentType());
		 ifile.setImageName(file.getOriginalFilename());
		 ifile.setImageData(file.getBytes());
		 cService.addImage(ifile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getImages")
	public List<ImageFile> getImages(@RequestParam int incidentId) {
	     return cService.getImages(incidentId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getPath")
	public void getPath() {
		 System.out.println("Working Directory = " + System.getProperty("user.dir"));
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		System.out.println("Current relative path is: " + currentRelativePath.toString());
		File f = new File("C:\\Work\\Java\\InciTrackerRepo\\test.txt");
		try {
			System.out.println(f.getPath()+"---"+f.getAbsolutePath()+"---"+f.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
