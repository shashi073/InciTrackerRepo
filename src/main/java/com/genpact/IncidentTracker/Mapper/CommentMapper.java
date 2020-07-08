package com.genpact.IncidentTracker.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.genpact.IncidentTracker.model.Comment;

public class CommentMapper implements RowMapper<Comment> 
{

	@Override
	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comment c = new Comment();
		c.setCommentId(rs.getInt("CommentId"));
		c.setIncidentId(rs.getInt("IncidentId"));
		c.setComments(rs.getString("Comment"));
		return c;
	}
}
