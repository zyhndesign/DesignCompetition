package com.cidic.design.dao;

import java.util.List;
import java.util.Optional;

import com.cidic.design.model.ProductUserModel;
import com.cidic.design.model.Production;
import com.cidic.design.model.ScoreBean;

public interface ProductionDao {

	public void createProduction(Production production);
	
	public void updateProduction(Production production);
	
	public void deleteProduction(int id);
	
	/**
	 * 管理员权限下查看所有的投稿作品,可以根据组别查询
	 * @param offset
	 * @param limit
	 * @param groupId 0:查看所有，>0 根据组别查询
	 * @return
	 */
	public List<ProductUserModel> getListProductionByPage(int offset, int limit, int groupId);
	
	public int getCountProduction(int groupId);
	/**
	 * 根据用户ID查看其投稿的所有作品,可以根据组别查询
	 * @param userId
	 * @param offset
	 * @param limit
	 * @param groupId 0:查看所有，>0 根据组别查询
	 * @return
	 */
	public List<Production> getListProductionByPageAndUserId(int offset, int limit, int groupId);
	
	public int getCountProductionByUserId(int groupId);
	
	/**
	 * 根据作品ID查看其详情
	 * @param id
	 * @return
	 */
	public Optional<Production> getProductionDetailById(int id);
	
	/**
	 * 最终打分结果更新到对应的作品
	 * @param productionId
	 * @param averageScore
	 */
	public void updateProductionScore(int productionId, float averageScore);
	
	/**
	 * 批量更新最后得分
	 * @param list
	 */
	public void batchUpdateProductionScore(List<ScoreBean> list);
}
