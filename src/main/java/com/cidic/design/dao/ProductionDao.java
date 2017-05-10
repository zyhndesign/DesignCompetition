package com.cidic.design.dao;

import java.util.List;

import com.cidic.design.model.Production;

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
	public List<Production> getListProductionByPage(int offset, int limit, int groupId);
	
	/**
	 * 根据用户ID查看其投稿的所有作品,可以根据组别查询
	 * @param userId
	 * @param offset
	 * @param limit
	 * @param groupId 0:查看所有，>0 根据组别查询
	 * @return
	 */
	public List<Production> getListProductionByPageAndUserId(int userId, int offset, int limit, int groupId);
	
	/**
	 * 根据作品ID查看其详情
	 * @param id
	 * @return
	 */
	public Production getProductionDetailById(int id);
}
