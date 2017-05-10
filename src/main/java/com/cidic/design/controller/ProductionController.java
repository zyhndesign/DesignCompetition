package com.cidic.design.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cidic.design.DcController;
import com.cidic.design.service.ProductionService;

@Controller
@RequestMapping(value="/production")
public class ProductionController  extends DcController{

	@Autowired
	@Qualifier(value = "productionServiceImpl")
	private ProductionService productionServiceImpl;
	
}
