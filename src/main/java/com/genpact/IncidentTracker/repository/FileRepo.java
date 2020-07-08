package com.genpact.IncidentTracker.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.genpact.IncidentTracker.Mapper.IncidentCommentFileDetailMapper;
import com.genpact.IncidentTracker.Mapper.IncidentFileDetailMapper;
import com.genpact.IncidentTracker.model.FileDetails;


@Repository
public class FileRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addIncidentFile(FileDetails fDetails) {
		 String insertQuery = "Insert into IncidentFile(IncidentId,FileType,FileUrl) Values(?,?,?)";
		 jdbcTemplate.update(insertQuery,new Object[] {fDetails.getIncidentId(),fDetails.getFileType(),fDetails.getFileUrl()});
	}

	public List<FileDetails> getIncidentFile(int incidentId) {
		String selectQuery = "Select FileId,IncidentId,FileType,FileUrl from IncidentFile where IncidentId=?";
		List<FileDetails> iFiles = jdbcTemplate.query(selectQuery,new Object[] {incidentId}, new IncidentFileDetailMapper());
		return iFiles;
	}

	public void addIncidentCommentFile(FileDetails fDetails) {
		String insertQuery = "Insert into CommentFile(CommentId,FileType,FileUrl) Values(?,?,?)";
		jdbcTemplate.update(insertQuery,new Object[] {fDetails.getCommentId(),fDetails.getFileType(),fDetails.getFileUrl()});
		
	}
	
	public List<FileDetails> getIncidentCommentFile(int incidentId) {
		String selectQuery = "Select cF.FileId, cF.CommentId, cF.FileType, cF.FileUrl from CommentFile cF join Comments c on"
				+ " cF.CommentId=c.CommentId where c.IncidentId=?";
		List<FileDetails> iFiles = jdbcTemplate.query(selectQuery,new Object[] {incidentId}, new IncidentCommentFileDetailMapper());
		return iFiles;
	}

	/*
	 * public void addImage(ImageFile imageFile) { String insertQuery =
	 * "Insert into ImageFile(IncidentId,ImageName,ImageDescription,ImageData) Values(?,?,?,?)"
	 * ; jdbcTemplate.update(insertQuery,new Object[]
	 * {imageFile.getIncidentId(),imageFile.getImageName(),
	 * imageFile.getImageDescription(),imageFile.getImageData()}); }
	 * 
	 * public List<ImageFile> getImages(int incidentId) { String selectQuery =
	 * "Select ImageId,IncidentId,ImageName,ImageDescription,ImageData" +
	 * " from ImageFile where IncidentId=?"; List<ImageFile> iFiles =
	 * jdbcTemplate.query(selectQuery,new Object[] {incidentId}, new ImageMapper());
	 * return iFiles; }
	 */
	
}
