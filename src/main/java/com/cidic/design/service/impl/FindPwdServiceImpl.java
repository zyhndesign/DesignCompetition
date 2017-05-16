package com.cidic.design.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.FindPwdDao;
import com.cidic.design.model.FindPwd;
import com.cidic.design.model.MailBean;
import com.cidic.design.service.FindPwdService;
import com.cidic.design.service.MailService;
import com.cidic.design.util.ConfigInfo;
import com.cidic.design.util.PasswordHelper;
import com.cidic.design.util.ResponseCodeUtil;

@Service
@Component
@Qualifier(value = "findPwdServiceImpl")
@Transactional
public class FindPwdServiceImpl implements FindPwdService {

	@Autowired
	@Qualifier(value = "findPwdDaoImpl")
	private FindPwdDao findPwdDaoImpl;

	@Autowired
	@Qualifier(value = "configInfo")
	private ConfigInfo configInfo;

	@Autowired
	@Qualifier(value = "mailServiceImpl")
	private MailService mailServiceImpl;

	@Override
	public int createFindPwd(FindPwd findPwd) {
		try {
			// 生成密钥
			String secretKey = UUID.randomUUID().toString();
			// 设置过期时间
			Date outDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);// 30分钟后过期

			long date = outDate.getTime() / 1000 * 1000;// 忽略毫秒数 mySql
														// 取出时间是忽略毫秒数的
			String key = findPwd.getEmail() + "$" + secretKey;

			String digitalSignature = PasswordHelper.getMD5(key);// 数字签名
			
			findPwd.setOutDate((int) date);
			findPwd.setValidCode(secretKey);
			int id = findPwdDaoImpl.createFindPwd(findPwd);

			StringBuffer sBuilder = new StringBuffer("请勿回复本邮件.点击下面的链接,重设密码,本邮件超过30分钟,链接将会失效，需要重新申请找回密码！</br>");
			sBuilder.append("<a href=\"" + configInfo.email_active_url + "/user/active?email=");
			sBuilder.append(findPwd.getEmail());
			sBuilder.append("&validCode=");
			sBuilder.append(digitalSignature);
			sBuilder.append("&id=");
			sBuilder.append(id);
			sBuilder.append("\">激活账号：" + findPwd.getEmail());
			sBuilder.append("</a>");

			MailBean mailBean = new MailBean();
			mailBean.setContext(sBuilder.toString());
			mailBean.setFrom(configInfo.email_active_from);
			mailBean.setFromName(configInfo.email_active_from_name);
			mailBean.setSubject(configInfo.email_active_subject);
			mailBean.setToEmails(new String[] { findPwd.getEmail() });
			mailServiceImpl.sendMail(mailBean);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (

		Exception e) {
			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public boolean getFindPwdByCondition(String email, String validCode, int id) {
		Optional<FindPwd> findPwd = findPwdDaoImpl.getFindPwdById(id);
		
		return false;
	}

}
