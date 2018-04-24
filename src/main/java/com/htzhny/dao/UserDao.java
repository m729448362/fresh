package com.htzhny.dao;

import com.htzhny.entity.User;

public interface UserDao {
	public User findUserByUserName(String user_name) ;
	public Integer addUser(User user);
	public Integer updatePassword(String user_name,String password);
	public Integer updateMessage(User user);
	
}
