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
import com.cidic.design.model.JudgePageModel;
import com.cidic.design.model.ListResultModel;
import com.cidic.design.model.ResultModel;
import com.cidic.design.service.JudgeService;

/**
 * 评委信息处理类
 * @author dev
 *
 */
@Controller
@RequestMapping(value="/judge")
public class JudgeController  extends DcController{

	@Autowired
	@Qualifier(value = "judgeServiceImpl")
	private JudgeService judgeServiceImpl;
	
	@RequestMapping(value = "/judge")
	public ModelAndView judge(HttpServletRequest request, Model model) {
		List<Judge> judgeList = judgeServiceImpl.getAllJudge();
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("/frontend/judges");
		modelView.addObject(judgeList);
        return modelView;
	}
	
	@RequestMapping(value = "/judgeDetail/{id}")
	public ModelAndView judgeDetail(HttpServletRequest request, Model model,@PathVariable int id) {
		Judge judge = null;
		if (id > 0){
			judge = judgeServiceImpl.findJudgeById(id).get();
		}
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("/frontend/judgeDetail");
		modelView.addObject(judge);
        return modelView;
	}
	
	@RequiresRoles(value ={"管理员"})
	@RequestMapping(value = "/judgeMgr")
    public String judgeMgr(HttpServletRequest request, Model model) {
        return "backend/judgeMgr";
    }
	
	@RequiresRoles(value ={"管理员"})
	@RequestMapping(value = "/judgeCOU")
    public String judgeCOU(HttpServletRequest request, Model model) {
        return "backend/judgeCOU";
    }
	
	@RequiresRoles(value ={"管理员"})
	@RequestMapping(value = "/judgeCOU/{id}",method = RequestMethod.GET)
    public ModelAndView updateCOU(HttpServletRequest request, @PathVariable int id) {
		Judge judge = null;
		if (id > 0){
			judge = judgeServiceImpl.findJudgeById(id).get();
		}
		
		ModelAndView model = new ModelAndView();
		model.setViewName("backend/judgeCOU");
		model.addObject(judge);
        return model;
    }
	
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/createJudge", method = RequestMethod.POST)
	public ResultModel createJudge(HttpServletRequest request, HttpServletResponse response,@RequestBody Judge judge){
		resultModel = new ResultModel();
		try{
			judge.setCreatetime(new Date());
			judgeServiceImpl.createJudge(judge);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/deleteJudge/{id}", method = RequestMethod.GET)
	public ResultModel deleteJudge(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		resultModel = new ResultModel();
		try{
			judgeServiceImpl.deleteJudge(id);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/updateJudge", method = RequestMethod.POST)
	public ResultModel updateJudge(HttpServletRequest request, HttpServletResponse response,@RequestBody Judge judge){
		resultModel = new ResultModel();
		try{
			judge.setCreatetime(new Date());
			judgeServiceImpl.updateJudge(judge);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/findJudgeById/{id}", method = RequestMethod.GET)
	public ResultModel findJudgeById(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		
		resultModel = new ResultModel();
		try{
			Optional<Judge> judge = judgeServiceImpl.findJudgeById(id);
			resultModel.setResultCode(200);
			resultModel.setObject(judge.get());
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/getAllJudge", method = RequestMethod.POST)
	public ResultModel getAllJudge(){
		
		resultModel = new ResultModel();
		try{
			List<Judge> list = judgeServiceImpl.getAllJudge();
			resultModel.setResultCode(200);
			resultModel.setObject(list);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@RequiresRoles(value ={"管理员"})
	@ResponseBody
	@RequestMapping(value="/findJudgesByPage", method = RequestMethod.POST)
	public ListResultModel findJudgesByPage(HttpServletRequest request, HttpServletResponse response, @RequestParam int iDisplayStart, 
			@RequestParam int iDisplayLength,@RequestParam String sEcho){

		ListResultModel listResultModel = new ListResultModel();
		try {
			JudgePageModel judgePageModel = judgeServiceImpl.findJudgeByPage(iDisplayStart, iDisplayLength);
			listResultModel.setAaData(judgePageModel.getList());
			listResultModel.setsEcho(sEcho);
			listResultModel.setiTotalRecords(judgePageModel.getCount());
			listResultModel.setiTotalDisplayRecords(judgePageModel.getCount());
			listResultModel.setSuccess(true);
		}
		catch (Exception e) {
			listResultModel.setSuccess(false);
		}
		return listResultModel;
	}
}
