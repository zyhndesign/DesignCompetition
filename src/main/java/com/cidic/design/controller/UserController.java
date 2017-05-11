package com.cidic.design.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.design.DcController;
import com.cidic.design.exception.DCException;
import com.cidic.design.model.ResultModel;
import com.cidic.design.model.User;
import com.cidic.design.service.UserService;

/**
 * 大赛用户信息处理
 * @author dev
 *
 */
@Controller
@RequestMapping(value="/user")
public class UserController  extends DcController{

	@Autowired
	@Qualifier(value = "userServiceImpl")
	private UserService userServiceImpl;
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ResultModel registerUser(HttpServletRequest request, HttpServletResponse response,@RequestParam User user){
		resultModel = new ResultModel();
		try{
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	/**
	 * 用户信息更新
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ResultModel updateUser(HttpServletRequest request, HttpServletResponse response,@RequestParam User user){
		resultModel = new ResultModel();
		try{
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	/**
	 * 用户激活
	 * @param request
	 * @param response
	 * @param email
	 * @param activeCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/active", method = RequestMethod.GET)
	public ResultModel activeUser(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam String email, @RequestParam String activeCode) throws DCException{
		resultModel = new ResultModel();
		userServiceImpl.activeUser(email, activeCode);
		resultModel.setResultCode(200);
		return resultModel;
	}
	
	/**
	 * 找回密码
	 * @param request
	 * @param response
	 * @param email 
	 * @return 500:出错， 300：email不正确， 200:请求成功，请查看邮箱
	 */
	@ResponseBody
	@RequestMapping(value="/findYourPwd", method = RequestMethod.GET)
	public ResultModel findYourPwd(HttpServletRequest request, HttpServletResponse response, @RequestParam String email){
		resultModel = new ResultModel();
		try{
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @param newPwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/resetYourPwd", method = RequestMethod.POST)
	public ResultModel resetYourPwd(HttpServletRequest request, HttpServletResponse response, @RequestParam String newPwd){
		resultModel = new ResultModel();
		try{
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
}
