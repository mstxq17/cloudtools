package com.xq17.cloudtools.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq17.cloudtools.bean.User;
import com.xq17.cloudtools.mapper.UserMapper;
import com.xq17.cloudtools.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public Integer insert(User user) {
		// TODO Auto-generated method stub
		return userMapper.insert(user);
	}

	@Override
	public User findByName(String username) {
		// TODO Auto-generated method stub
		return userMapper.findByName(username);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userMapper.findByEmail(email);
	}

	@Override
	public User findByOpenId(String openId) {
		// TODO Auto-generated method stub
		return userMapper.findByOpenId(openId);
	}

	@Override
	public User findByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.findByUserId(userId);
	}

	@Override
	public User login(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userMapper.login(map);
	}

	@Override
	public Integer update(User user) {
		// TODO Auto-generated method stub
		return userMapper.update(user);
	}

	@Override
	public Map<String, Object> findUsers(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", userMapper.totalUser());
		result.put("rows", userMapper.findUsers(map));
		return result;
	}
}
