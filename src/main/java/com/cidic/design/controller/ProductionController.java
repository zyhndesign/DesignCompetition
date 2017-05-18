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
import org.springframework.web.servlet.ModelAndView;

import com.cidic.design.DcController;
import com.cidic.design.exception.DCException;
import com.cidic.design.model.Judge;
import com.cidic.design.model.Production;
import com.cidic.design.model.ResultModel;
import com.cidic.design.service.ProductionService;
import com.cidic.design.util.ConfigInfo;
import com.cidic.design.util.DateUtil;

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
	
	@Autowired
	@Qualifier(value = "configInfo")
	private ConfigInfo configInfo;
	
	@RequiresRoles(value ={"竞赛者"})
	@RequestMapping(value = "/works")
	public String works(HttpServletRequest request, Model model) {
		return "/frontend/works";
	}
	
	@RequiresRoles(value ={"竞赛者"})
	@RequestMapping(value = "/uploadWork")
	public String uploadWork(HttpServletRequest request, Model model) {
		if(DateUtil.compareDate(configInfo.contribute_end_time)){
			return ""; //投稿结束页面
		}
		else{
			return "/frontend/uploadWork";
		}
		
	}
	
	@RequiresRoles(value ={"竞赛者"})
	@RequestMapping(value = "/uploadWork/{id}")
	public ModelAndView uploadWork(HttpServletRequest request, @PathVariable int id) {
		Production production = null;
		ModelAndView model = new ModelAndView();
		if(DateUtil.compareDate(configInfo.contribute_end_time)){
			model.setViewName(""); //投稿结束页面
		}
		else{
			if (id > 0){
				production = productionServiceImpl.getProductionDetailById(id).get();
			}
			model.setViewName("/frontend/uploadWork");
			model.addObject(production);
		}
		
        return model;
	}
	
	@RequiresRoles(value ={"竞赛者"})
	@ResponseBody
	@RequestMapping(value="/createProduction", method = RequestMethod.POST)
	public ResultModel createProduction(HttpServletRequest request, HttpServletResponse response,@RequestBody Production production) throws DCException{
		resultModel = new ResultModel();
		try{
			if(!DateUtil.compareDate(configInfo.contribute_end_time)){
				production.setCreateTime(new Date());
				productionServiceImpl.createProduction(production);
				resultModel.setResultCode(200);
				resultModel.setSuccess(true);
				return resultModel;
			}
			else{
				throw new DCException(600, "投稿日期结束");
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DCException(500, "创建出错");
		}
	}
	
	@RequiresRoles(value ={"竞赛者"})
	@ResponseBody
	@RequestMapping(value="/updateProduction", method = RequestMethod.POST)
	public ResultModel updateProduction(HttpServletRequest request, HttpServletResponse response,@RequestBody Production production) throws DCException{
		resultModel = new ResultModel();
		try{
			if(!DateUtil.compareDate(configInfo.contribute_end_time)){
				production.setCreateTime(new Date());
				productionServiceImpl.updateProduction(production);
				resultModel.setResultCode(200);
				resultModel.setSuccess(true);
				return resultModel;
			}
			else{
				throw new DCException(600, "投稿日期结束");
			}
			
		}
		catch(Exception e){
			throw new DCException(500, "修改出错");
		}
	}
	
	@RequiresRoles(value ={"竞赛者"})
	@ResponseBody
	@RequestMapping(value="/deleteProduction/{id}", method = RequestMethod.GET)
	public ResultModel deleteProduction(HttpServletRequest request, HttpServletResponse response,@PathVariable int id) throws DCException{
		resultModel = new ResultModel();
		try{
			if(!DateUtil.compareDate(configInfo.contribute_end_time)){
				productionServiceImpl.deleteProduction(id);
				resultModel.setResultCode(200);
				resultModel.setSuccess(true);
				return resultModel;
			}
			else{
				throw new DCException(600, "投稿日期结束");
			}
			
		}
		catch(Exception e){
			throw new DCException(500, "删除出错");
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
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "获取数据出错");
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
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "获取数据出错");
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
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "获取数据出错");
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
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DCException(500, "计算总分出错");
		}
	}
}
