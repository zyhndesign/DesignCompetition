package com.cidic.design.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cidic.design.DcController;
import com.cidic.design.service.RuleService;

@Controller
@RequestMapping(value="/rule")
public class RuleController  extends DcController{

	@Autowired
	@Qualifier(value = "ruleServiceImpl")
	private RuleService ruleServiceImpl;
	
	
}
