package com.cidic.design.service;

import java.util.List;
import java.util.Optional;

import com.cidic.design.model.Judge;
import com.cidic.design.model.JudgePageModel;
import com.cidic.design.model.NewsPageModel;

public interface JudgeService {

	public void createJudge(Judge judge);
	
	public void deleteJudge(int id);
	
	public void updateJudge(Judge judge);
	
	public Optional<Judge> findJudgeById(int id);
	
	public List<Judge> getAllJudge();
	
	public JudgePageModel findJudgeByPage(int offset, int limit);
}
