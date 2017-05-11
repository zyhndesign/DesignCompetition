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
import com.cidic.design.model.Judge;
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
	
	@ResponseBody
	@RequestMapping(value="/createJudge", method = RequestMethod.POST)
	public ResultModel createJudge(HttpServletRequest request, HttpServletResponse response,@RequestParam Judge judge){
		resultModel = new ResultModel();
		try{
			judgeServiceImpl.createJudge(judge);
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteJudge/{id}", method = RequestMethod.GET)
	public ResultModel deleteJudge(HttpServletRequest request, HttpServletResponse response,@PathVariable int id){
		resultModel = new ResultModel();
		try{
			judgeServiceImpl.deleteJudge(id);
			resultModel.setResultCode(200);
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/updateJudge", method = RequestMethod.POST)
	public ResultModel updateJudge(HttpServletRequest request, HttpServletResponse response,@RequestParam Judge judge){
		resultModel = new ResultModel();
		try{
			judgeServiceImpl.updateJudge(judge);
			resultModel.setResultCode(200);
			
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
			resultModel.setObject(judge);
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
			return resultModel;
		}
		catch(Exception e){
			throw new DCException(500, "创建出错");
		}
	}
}
