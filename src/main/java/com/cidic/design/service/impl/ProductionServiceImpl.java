package com.cidic.design.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.ProductionDao;
import com.cidic.design.dao.ReviewDao;
import com.cidic.design.model.Production;
import com.cidic.design.model.ScoreBean;
import com.cidic.design.service.ProductionService;

@Service
@Component
@Qualifier(value = "productionServiceImpl")
@Transactional
public class ProductionServiceImpl implements ProductionService {

	@Autowired
	@Qualifier(value = "productionDaoImpl")
	private ProductionDao productionDaoImpl;
	
	@Autowired
	@Qualifier(value = "reviewDaoImpl")
	private ReviewDao reviewDaoImpl;
	
	@Override
	public void createProduction(Production production) {
		productionDaoImpl.createProduction(production);
	}

	@Override
	public void updateProduction(Production production) {
		productionDaoImpl.updateProduction(production);
	}

	@Override
	public void deleteProduction(int id) {
		productionDaoImpl.deleteProduction(id);
	}

	@Override
	public List<Production> getListProductionByPage(int offset, int limit, int groupId) {
		return productionDaoImpl.getListProductionByPage(offset, limit, groupId);
	}

	@Override
	public List<Production> getListProductionByPageAndUserId(int userId, int offset, int limit, int groupId) {
		
		return productionDaoImpl.getListProductionByPageAndUserId(userId, offset, limit, groupId);
	}

	@Override
	public Optional<Production> getProductionDetailById(int id) {
		
		return productionDaoImpl.getProductionDetailById(id);
	}

	@Override
	public void updateProductionScore() {
		List<ScoreBean> list = reviewDaoImpl.getAllReviewResult();
		//productionDaoImpl.batchUpdateProductionScore(list);
		for (int i = 0; i < 10; i++){
			ScoreBean scoreBean = new ScoreBean();
			scoreBean.setProductionId(1);
			scoreBean.setGradeSum(2);
			scoreBean.setScoreSum(187);
			scoreBean.setAverageScore((float)187/2);
			list.add(scoreBean);
		}
		
		for (ScoreBean scoreBean : list){
			productionDaoImpl.updateProductionScore(scoreBean.getProductionId(), scoreBean.getAverageScore());
		}
	}

}
