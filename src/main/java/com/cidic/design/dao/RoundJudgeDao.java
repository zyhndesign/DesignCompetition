package com.cidic.design.dao;

import java.util.List;

import com.cidic.design.model.RoundJudge;

public interface RoundJudgeDao {
	
	public void createRoundJudge(RoundJudge roundJudge);
	
	public void updateRoundJudge(RoundJudge roundJudge);
	
	public void deleteRoundJudge(int id);
	
	public List<RoundJudge> getAllRoundJudge();
}
