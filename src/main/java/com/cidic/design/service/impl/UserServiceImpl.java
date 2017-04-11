package com.cidic.design.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.UserDao;
import com.cidic.design.model.User;
import com.cidic.design.service.UserService;
import com.cidic.design.util.PasswordHelper;
import com.cidic.design.util.ResponseCodeUtil;


@Service
@Component
@Qualifier(value = "userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier(value = "userDaoImpl")
	private UserDao userDaoImpl;
	
	@Override
	public int createUser(User user) {
		try{
			Optional<User> optUser = userDaoImpl.findByUsername(user.getEmail());
		    
		    if (optUser.isPresent()) {
				return ResponseCodeUtil.UESR_CREATE_EXIST;
			} else {
				PasswordHelper.encryptAppPassword(user);
				userDaoImpl.createUser(user);
				return ResponseCodeUtil.UESR_OPERATION_SUCESS;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
		
	}

	@Override
	public int updateUser(User user) {
		try{
			userDaoImpl.updateUser(user);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
		
	}

	@Override
	public int deleteUser(Long userId) {
		try{
			userDaoImpl.deleteUser(userId);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public int correlationRoles(Long userId, Long... roleIds) {
		try{
			userDaoImpl.correlationRoles(userId, roleIds);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
		
	}

	@Override
	public int uncorrelationRoles(Long userId, Long... roleIds) {
		try{
			userDaoImpl.uncorrelationRoles(userId, roleIds);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
		
	}

	@Override
	public Optional<User> findOne(Long userId) {
		return userDaoImpl.findOne(userId);
	}

	@Override
	public Optional<User> findByUsername(String username) {
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
