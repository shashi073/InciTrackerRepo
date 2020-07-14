package com.genpact.IncidentTracker.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.genpact.IncidentTracker.Mapper.UserMapper;
import com.genpact.IncidentTracker.model.User;

@Repository
public class UserRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean addUser(User user) {
		boolean status = false;
		String selectQuery = "Select UserId,UserName,Email,MobileNo from User where Email=?";
		List<User> users = jdbcTemplate.query(selectQuery, new Object[] {user.getEmailId()}, new UserMapper());
		if(users.size()==0) {
			String insertQuery = "Insert into User(UserName,Email,Password,MobileNo) Values(?,?,?,?)";
			jdbcTemplate.update(insertQuery,new Object[] {user.getUserName(),user.getEmailId(),user.getPassword(),user.getMobileNo()});
			status = true;
		}
		return status; 
	}

	public User validateUserLogin(User user) {
		User loggedInuser = null;
		String selectQuery = "Select UserId,UserName,Email,MobileNo from User where Email=? AND Password=?";
		List<User> users = jdbcTemplate.query(selectQuery, new Object[] {user.getEmailId(), user.getPassword()}, new UserMapper());
		if(users.size()>0) {
			loggedInuser = users.get(0);
		}
		
		return loggedInuser;
	}

	public boolean checkEmailIdAvailability(User user) {
		boolean status = true;
		String selectQuery = "Select UserId,UserName,Email,MobileNo from User where Email=?";
		List<User> users = jdbcTemplate.query(selectQuery, new Object[] {user.getEmailId()}, new UserMapper());
		if(users.size()>0) {
			status = true;
		}
		else {
			status =false;
		}
		return status; 
	}
	
}
