package com.cidic.design.dao;

import java.util.List;

import com.cidic.design.model.Rule;

public interface RuleDao {

	public void createRule(Rule rule);
	
	public void updateRule(Rule rule);
	
	public void deleteRule(int id);
	
	public List<Rule> getAllRule();
	
	public Rule getRuleById(int id);
}
