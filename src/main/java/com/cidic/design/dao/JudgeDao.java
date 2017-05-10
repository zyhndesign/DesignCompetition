package com.cidic.design.dao;

import java.util.List;

import com.cidic.design.model.Judge;
import com.cidic.design.model.News;

public interface JudgeDao {

	public void createJudge(Judge judge);
	
	public void deleteJudge(int id);
	
	public void updateJudge(Judge judge);
	
	public News findJudgeById(int id);
	
	public List<Judge> getAllJudge();
}
