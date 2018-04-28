package com.htzhny.service;

import java.util.List;

import com.htzhny.entity.User;

public interface UserService {
	public User findUserByUserName(String user_name);
	public Integer addUser(User user);
	public Integer updatePassword(String user_name,String password);
	public Integer updateMessage(User user);
	public List<User> selectAllUser();
}
