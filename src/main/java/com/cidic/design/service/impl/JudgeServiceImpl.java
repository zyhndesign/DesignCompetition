package com.cidic.design.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.JudgeDao;
import com.cidic.design.model.Judge;
import com.cidic.design.service.JudgeService;

@Service
@Component
@Qualifier(value = "judgeServiceImpl")
@Transactional
public class JudgeServiceImpl implements JudgeService {

	@Autowired
	@Qualifier(value = "judgeDaoImpl")
	private JudgeDao judgeDaoImpl;
	
	@Override
	public void createJudge(Judge judge) {
		judgeDaoImpl.createJudge(judge);
	}

	@Override
	public void deleteJudge(int id) {
		judgeDaoImpl.deleteJudge(id);
	}

	@Override
	public void updateJudge(Judge judge) {
		judgeDaoImpl.updateJudge(judge);
	}

	@Override
	public Optional<Judge> findJudgeById(int id) {
		
		return judgeDaoImpl.findJudgeById(id);
	}

	@Override
	public List<Judge> getAllJudge() {
		return judgeDaoImpl.getAllJudge();
	}

}
