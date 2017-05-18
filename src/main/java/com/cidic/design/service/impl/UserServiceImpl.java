package com.cidic.design.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.UserDao;
import com.cidic.design.exception.DCException;
import com.cidic.design.exception.DcRedirectException;
import com.cidic.design.model.MailBean;
import com.cidic.design.model.Role;
import com.cidic.design.model.User;
import com.cidic.design.model.UserPageModel;
import com.cidic.design.model.UserRole;
import com.cidic.design.service.MailService;
import com.cidic.design.service.UserService;
import com.cidic.design.util.ConfigInfo;
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

	@Autowired
	@Qualifier(value = "configInfo")
	private ConfigInfo configInfo;

	@Override
	public int createUser(User user) {
		try {
			Optional<User> optUser = userDaoImpl.findByEmail(user.getEmail());

			if (optUser.isPresent()) {
				return ResponseCodeUtil.UESR_CREATE_EXIST;
			} else {
				PasswordHelper.encryptAppPassword(user);
				user.setActivecode(PasswordHelper.getMD5(user.getEmail()));
				Set<UserRole> userRoles = new HashSet<>();
				UserRole userRole = new UserRole();
				userRole.setUser(user);
				Role role = new Role();
				role.setId(3);
				userRole.setRole(role);
				userRoles.add(userRole);
				user.setUserRoles(userRoles);
				userDaoImpl.createUser(user);

				StringBuffer sBuilder = new StringBuffer("点击下面链接激活账号，48小时生效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
				sBuilder.append("<a href=\"" + configInfo.email_active_url + "/user/active?email=");
				sBuilder.append(user.getEmail());
				sBuilder.append("&activeCode=");
				sBuilder.append(user.getActivecode());
				sBuilder.append("\">激活账号：" + user.getEmail());
				sBuilder.append("</a>");

				MailBean mailBean = new MailBean();
				mailBean.setContext(sBuilder.toString());
				mailBean.setFrom(configInfo.email_active_from);
				mailBean.setFromName(configInfo.email_active_from_name);
				mailBean.setSubject(configInfo.email_active_subject);
				mailBean.setToEmails(new String[] { user.getEmail() });
				mailServiceImpl.sendMail(mailBean);
				return ResponseCodeUtil.UESR_OPERATION_SUCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}

	}

	@Override
	public int updateUser(User user) {
		try {
			userDaoImpl.updateUser(user);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}

	}

	@Override
	public int deleteUser(Long userId) {
		try {
			userDaoImpl.deleteUser(userId);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public int correlationRoles(Long userId, Long... roleIds) {
		try {
			userDaoImpl.correlationRoles(userId, roleIds);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}

	}

	@Override
	public int uncorrelationRoles(Long userId, Long... roleIds) {
		try {
			userDaoImpl.uncorrelationRoles(userId, roleIds);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}

	}

	@Override
	public Optional<User> findOne(Long userId) {
		return userDaoImpl.findOne(userId);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userDaoImpl.findByEmail(email);
	}

	@Override
	public Set<String> findRoles(String username) {

		return userDaoImpl.findRoles(username);
	}

	@Override
	public Set<String> findPermissions(String username) {

		return userDaoImpl.findPermissions(username);
	}

	@Override
	public void activeUser(String email, String activeCode) throws DCException {
		Optional<User> user = userDaoImpl.findByEmail(email);
		if (user.isPresent()) {
			if (user.get().getActivesign() == 0) {

				if (activeCode.equals(user.get().getActivecode())) {
					userDaoImpl.updateActiveSign(email);
				} else {
					throw new DcRedirectException(400, "激活码不正确");
				}

			} else {
				throw new DcRedirectException(200, "邮箱已激活，请登录！");
			}
		} else {
			throw new DcRedirectException(500, "邮箱未注册");
		}
	}

	@Override
	public int updatePwd(String email, String password,String oldSlot) {
		try {

			User user = new User();
			user.setPassword(password);
			user.setEmail(email);
			PasswordHelper.encryptAppPassword(user);

			userDaoImpl.updatePwd(email, user.getPassword(), user.getSlot(), oldSlot);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}
	
	@Override
	public int resetLoginUserPwd(String password) {
		try {
			Subject subject = SecurityUtils.getSubject();
			String email = subject.getSession().getAttribute("email").toString();
			
			User user = new User();
			user.setPassword(password);
			user.setEmail(email);
			PasswordHelper.encryptAppPassword(user);

			userDaoImpl.resetLoginUserPwd(email, user.getPassword(), user.getSlot());
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public Optional<User> checkAuthc(String email) {
		// TODO Auto-generated method stub
		return userDaoImpl.checkAuthc(email);
	}

	@Override
	public UserPageModel getUserByPage(int offset, int limit) {
		List<User> list = userDaoImpl.findUserListByPage(offset, limit);
		int count = userDaoImpl.getCountUser();
		UserPageModel userPageModel = new UserPageModel();
		userPageModel.setCount(count);
		userPageModel.setList(list);
		return userPageModel;
	}

	@Override
	public int updateValidSign(String email, int validValue) {
		try {
			userDaoImpl.updateValidSign(email, validValue);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

}
