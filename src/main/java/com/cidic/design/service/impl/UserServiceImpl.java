package com.cidic.design.service.impl;

import java.util.Set;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cidic.design.dao.UserDao;
import com.cidic.design.model.User;
import com.cidic.design.service.UserService;
import com.cidic.design.util.PasswordHelper;


@Service
@Component
@Qualifier(value = "userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier(value = "userDaoImpl")
	private UserDao userDaoImpl;
	
	@Override
	public User createUser(User user) {
		
		PasswordHelper.encryptPassword(user);
		return userDaoImpl.createUser(user);
	}

	@Override
	public void updateUser(User user) {
		userDaoImpl.updateUser(user);
	}

	@Override
	public void deleteUser(Long userId) {
		userDaoImpl.deleteUser(userId);
	}

	@Override
	public void correlationRoles(Long userId, Long... roleIds) {
		userDaoImpl.correlationRoles(userId, roleIds);
	}

	@Override
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		userDaoImpl.uncorrelationRoles(userId, roleIds);
	}

	@Override
	public User findOne(Long userId) {
		return userDaoImpl.findOne(userId);
	}

	@Override
	public User findByUsername(String username) {
		return userDaoImpl.findByUsername(username);
	}

	@Override
	public Set<String> findRoles(String username) {
		
		return userDaoImpl.findRoles(username);
	}

	@Override
	public Set<String> findPermissions(String username) {
		
		return userDaoImpl.findPermissions(username);
	}

}
