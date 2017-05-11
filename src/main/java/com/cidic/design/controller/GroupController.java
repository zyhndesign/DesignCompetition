package com.cidic.design.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.design.DcController;
import com.cidic.design.exception.DCException;
import com.cidic.design.model.Group;
import com.cidic.design.model.ResultModel;
import com.cidic.design.service.GroupService;

/**
 * 作品分组信息的处理
 * @author dev
 *
 */
@Controller
@RequestMapping(value="/group")
public class GroupController  extends DcController{

	@Autowired
	@Qualifier(value = "groupServiceImpl")
	private GroupService groupServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/createGroup", method = RequestMethod.POST)
	public ResultModel createGroup(HttpServletRequest request, HttpServletResponse response,@RequestBody Group group){
		resultModel = new ResultModel();
		try{
			groupServiceImpl.createGroup(group);
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteGroup/{id}", method = RequestMethod.POST)
	public ResultModel deleteGroup(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		resultModel = new ResultModel();
		try{
			groupServiceImpl.deleteGroup(id);
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/updateGroup", method = RequestMethod.POST)
	public ResultModel updateGroup(HttpServletRequest request, HttpServletResponse response,@RequestBody Group group){
		resultModel = new ResultModel();
		try{
			groupServiceImpl.updateGroup(group);
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getAllGroup", method = RequestMethod.POST)
	public ResultModel getAllGroup(){
		resultModel = new ResultModel();
		try{
			List<Group> list = groupServiceImpl.getAllGroup();
			resultModel.setResultCode(200);
			resultModel.setObject(list);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
}
