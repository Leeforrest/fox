package com.coolfish.gmserver.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolfish.gmserver.mvc.model.User;
import com.coolfish.gmserver.mybatis.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public int insert(User user) {
		return userMapper.insert(user);
	}
	
	public User findUser(String userName) {
		return userMapper.selectByUserName(userName);
	}
	
	public List<User> select() {
		return userMapper.select();
	}
	
	public User selectById(int id) {
		return userMapper.selectByPrimaryKey(id);
	}
}
