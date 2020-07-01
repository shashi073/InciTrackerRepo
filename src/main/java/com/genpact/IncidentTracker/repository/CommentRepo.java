package com.genpact.IncidentTracker.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.genpact.IncidentTracker.Mapper.CommentMapper;
import com.genpact.IncidentTracker.Mapper.CountryMapper;
import com.genpact.IncidentTracker.model.Comment;
import com.genpact.IncidentTracker.model.Country;

@Repository
public class CommentRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addComment(Comment comment) {
		 String insertQuery = "Insert into Comments(IncidentId,Comment) Values(?,?)";
		 jdbcTemplate.update(insertQuery,new Object[] {comment.getIncidentId(),comment.getComments()});
	}

	public List<Comment> getComments(int incidentId) {
		String selectQuery = "Select CommentId,IncidentId,Comment from Comments where IncidentId=?";
		List<Comment> comments = jdbcTemplate.query(selectQuery,new Object[] {incidentId}, new CommentMapper());
		return comments;
	}

	
}
