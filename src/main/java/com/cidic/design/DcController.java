package com.cidic.design;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cidic.design.exception.DCException;
import com.cidic.design.exception.DcRedirectException;
import com.cidic.design.model.ResultModel;

public abstract class DcController {

	protected ResultModel resultModel = null;

	@ExceptionHandler(DCException.class)
	public @ResponseBody ResultModel handleDCException(DCException ex) {
		resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		return resultModel;
	}
	
	/**
	 * 处理权限异常
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public @ResponseBody ResultModel handleUnauthorizedException(UnauthorizedException ex) {
		resultModel = new ResultModel();
		resultModel.setResultCode(900);
		resultModel.setMessage("无权限进行操作");
		resultModel.setSuccess(false);
		return resultModel;
	}
	
	@ExceptionHandler(DcRedirectException.class)
	public ModelAndView handlerDcRedirectException(DcRedirectException ex){
		ModelAndView modelView = new ModelAndView();
		resultModel = new ResultModel();
		resultModel.setResultCode(ex.getErrCode());
		resultModel.setMessage(ex.getErrMsg());
		resultModel.setSuccess(false);
		modelView.setViewName("/frontend/error");
        return modelView;
	}
	
}
