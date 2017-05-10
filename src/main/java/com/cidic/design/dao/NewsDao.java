package com.cidic.design.dao;

import java.util.List;

import com.cidic.design.model.News;

public interface NewsDao {

	public void createNews(News news);
	
	public void deleteNews(int id);
	
	public void updateNews(News news);
	
	public News findNewsById(int id);
	
	public List<News> findNewsByPage(int offset, int limit);
}
