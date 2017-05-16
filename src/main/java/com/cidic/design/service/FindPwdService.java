package com.cidic.design.service;


import com.cidic.design.model.FindPwd;

public interface FindPwdService {

	public void createFindPwd(FindPwd findPwd);
	
	public boolean getFindPwdByCondition(String email, String validCode);
}
