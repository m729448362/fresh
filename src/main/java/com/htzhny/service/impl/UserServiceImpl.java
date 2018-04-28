package com.htzhny.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htzhny.dao.UserDao;
import com.htzhny.entity.User;
import com.htzhny.service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired(required=true)
	UserDao userDao;
	@Transactional(readOnly=true)//��¼�˶ԣ���ʾ������Ϣ��
	@Override
	public User findUserByUserName(String user_name){
		return userDao.findUserByUserName(user_name);
	}
	//����û�
	@Override
	public Integer addUser(User user){
		return userDao.addUser(user);
	}
	//�޸�����
	@Override
	public Integer updatePassword(String user_name,String password){
		return userDao.updatePassword(user_name, password);
	}
	//�޸ĸ�����Ϣ
	@Override
	public Integer updateMessage(User user){
		return userDao.updateMessage(user);
	}
	@Override
	public List<User> selectAllUser() {
		return userDao.selectAllUser();
	}

}
