package com.cidic.design.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidic.design.DcController;
import com.cidic.design.exception.DCException;
import com.cidic.design.model.ResultModel;
import com.cidic.design.model.Rule;
import com.cidic.design.service.RuleService;

/**
 * 大赛规则信息
 * @author dev
 *
 */
@Controller
@RequestMapping(value="/rule")
public class RuleController  extends DcController{

	@Autowired
	@Qualifier(value = "ruleServiceImpl")
	private RuleService ruleServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/createRule", method = RequestMethod.POST)
	public ResultModel createRule(HttpServletRequest request, HttpServletResponse response,@RequestParam Rule rule){
		resultModel = new ResultModel();
		try{
			ruleServiceImpl.createRule(rule);
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/updateRule", method = RequestMethod.POST)
	public ResultModel updateRule(HttpServletRequest request, HttpServletResponse response,@RequestParam Rule rule){
		resultModel = new ResultModel();
		try{
			ruleServiceImpl.updateRule(rule);
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteRule/{id}", method = RequestMethod.POST)
	public ResultModel deleteRule(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		resultModel = new ResultModel();
		try{
			ruleServiceImpl.deleteRule(id);
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getAllRule", method = RequestMethod.POST)
	public ResultModel getAllRule(){
		resultModel = new ResultModel();
		try{
			List<Rule> list = ruleServiceImpl.getAllRule();
			resultModel.setResultCode(200);
			resultModel.setObject(list);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getRuleById/{id}", method = RequestMethod.POST)
	public ResultModel getRuleById(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		resultModel = new ResultModel();
		try{
			Optional<Rule> rule = ruleServiceImpl.getRuleById(id);
			resultModel.setResultCode(200);
			resultModel.setObject(rule);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
}
