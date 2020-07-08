package com.genpact.IncidentTracker.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.genpact.IncidentTracker.Mapper.CommentMapper;
import com.genpact.IncidentTracker.model.Comment;

@Repository
public class IncidentCommentRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String addComment(Comment comment) {
		 String commentId;
		 final String INSERT_SQL = "Insert into Comments(IncidentId,Comment) Values(?,?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement(INSERT_SQL, new String[] {"CommentId"});
			            ps.setInt(1,comment.getIncidentId());
			            ps.setString(2, comment.getComments());
			            return ps;
			        }
			    },
			    keyHolder);
			commentId = String.valueOf(keyHolder.getKey().intValue());
			return commentId;
	}

	public List<Comment> getComments(int incidentId) {
		String selectQuery = "Select CommentId,IncidentId,Comment from Comments where IncidentId=?";
		List<Comment> comments = jdbcTemplate.query(selectQuery,new Object[] {incidentId}, new CommentMapper());
		return comments;
	}

	
}
