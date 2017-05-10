package com.cidic.design.service;

import java.util.List;
import java.util.Optional;

import com.cidic.design.model.Judge;

public interface JudgeService {

	public void createJudge(Judge judge);
	
	public void deleteJudge(int id);
	
	public void updateJudge(Judge judge);
	
	public Optional<Judge> findJudgeById(int id);
	
	public List<Judge> getAllJudge();
}
