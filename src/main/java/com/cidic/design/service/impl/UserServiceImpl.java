package com.cidic.design.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.UserDao;
import com.cidic.design.model.MailBean;
import com.cidic.design.model.User;
import com.cidic.design.service.MailService;
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
	
	@Autowired
	@Qualifier(value = "mailServiceImpl")
	private MailService mailServiceImpl;
	
	@Override
	public int createUser(User user) {
		try{
			Optional<User> optUser = userDaoImpl.findByUsername(user.getEmail());
		    
		    if (optUser.isPresent()) {
				return ResponseCodeUtil.UESR_CREATE_EXIST;
			} else {
				PasswordHelper.encryptAppPassword(user);
				user.setActivecode(PasswordHelper.getMD5(user.getEmail()));
				userDaoImpl.createUser(user);
				
				StringBuffer sBuilder=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
				sBuilder.append("<a href=\"http://localhost:8080/user/active?email=");
		        sBuilder.append(user.getEmail()); 
		        sBuilder.append("&activeCode="); 
		        sBuilder.append(user.getActivecode());
		        sBuilder.append("\">"); 
		        sBuilder.append("</a>");
				
		        MailBean mailBean = new MailBean();
		        mailBean.setContext(sBuilder.toString());
		        mailBean.setFrom("");
		        mailBean.setFromName("");
		        mailBean.setSubject("");
		        mailBean.setToEmails(new String[]{user.getEmail()});
		        mailServiceImpl.sendMail(mailBean);
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
