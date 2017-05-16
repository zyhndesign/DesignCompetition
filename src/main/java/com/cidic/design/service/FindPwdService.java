package com.cidic.design.service;


import com.cidic.design.model.FindPwd;

public interface FindPwdService {

	public int createFindPwd(FindPwd findPwd);
	
	public boolean getFindPwdByCondition(String email, String validCode, int id);
}
