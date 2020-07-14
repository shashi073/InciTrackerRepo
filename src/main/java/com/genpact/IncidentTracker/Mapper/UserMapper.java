package com.genpact.IncidentTracker.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.genpact.IncidentTracker.model.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User u = new User();
		
		u.setUserId(rs.getInt("UserId")+"") ;
		u.setUserName(rs.getString("UserName"));
		u.setEmailId(rs.getString("Email"));
		u.setMobileNo(rs.getString("MobileNo"));
		
		return u;
	}
}
