package com.cidic.design.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.design.DcController;
import com.cidic.design.exception.DCException;
import com.cidic.design.model.Production;
import com.cidic.design.model.ResultModel;
import com.cidic.design.service.ProductionService;

/**
 * 选手作品信息处理
 * @author dev
 *
 */
@Controller
@RequestMapping(value="/production")
public class ProductionController  extends DcController{

	@Autowired
	@Qualifier(value = "productionServiceImpl")
	private ProductionService productionServiceImpl;
	
	@RequestMapping(value = "/home")
	public String login(HttpServletRequest request, Model model) {
		return "production";
	}
	
	@ResponseBody
	@RequestMapping(value="/createProduction", method = RequestMethod.POST)
	public ResultModel createProduction(HttpServletRequest request, HttpServletResponse response,@RequestBody Production production){
		resultModel = new ResultModel();
		try{
			production.setCreatetime(new Date());
			productionServiceImpl.createProduction(production);
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/updateProduction", method = RequestMethod.POST)
	public ResultModel updateProduction(HttpServletRequest request, HttpServletResponse response,@RequestBody Production production){
		resultModel = new ResultModel();
		try{
			production.setCreatetime(new Date());
			productionServiceImpl.updateProduction(production);
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteProduction/{id}", method = RequestMethod.GET)
	public ResultModel deleteProduction(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		resultModel = new ResultModel();
		try{
			productionServiceImpl.deleteProduction(id);
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getListProductionByPage", method = RequestMethod.POST)
	public ResultModel getListProductionByPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int offset, @RequestParam int limit, @RequestParam int groupId){
		
		resultModel = new ResultModel();
		try{
			List<Production> list = productionServiceImpl.getListProductionByPage(offset, limit, groupId);
			resultModel.setResultCode(200);
			resultModel.setObject(list);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getListProductionByPageAndUserId", method = RequestMethod.POST)
	public ResultModel getListProductionByPageAndUserId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int userId, @RequestParam int offset, @RequestParam int limit, @RequestParam int groupId){
		
		resultModel = new ResultModel();
		try{
			List<Production> list = productionServiceImpl.getListProductionByPageAndUserId(userId, offset, limit, groupId);
			resultModel.setResultCode(200);
			resultModel.setObject(list);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getProductionDetailById/{id}", method = RequestMethod.GET)
	public ResultModel getProductionDetailById(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		
		resultModel = new ResultModel();
		try{
			Optional<Production> production = productionServiceImpl.getProductionDetailById(id);
			resultModel.setObject(production.get());
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	//@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/updateProductionScore", method = RequestMethod.GET)
	public ResultModel updateProductionScore(HttpServletRequest request, HttpServletResponse response){
		
		resultModel = new ResultModel();
		try{
			productionServiceImpl.updateProductionScore();
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DCException(500, "计算总分出错");
		}
	}
}
