package com.cidic.design.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.design.exception.DCException;
import com.cidic.design.model.ResultModel;
import com.cidic.design.model.User;

@Controller
@RequestMapping(value="/user")
public class UserController {

	private ResultModel resultModel = null;

	@ExceptionHandler(DCException.class)
	public @ResponseBody ResultModel handleCustomException(DCException ex) {
		ResultModel resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		return resultModel;
	}
	
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
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/active", method = RequestMethod.GET)
	public ResultModel activeUser(HttpServletRequest request, HttpServletResponse response, @RequestParam String activeCode){
		resultModel = new ResultModel();
		try{
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
}
