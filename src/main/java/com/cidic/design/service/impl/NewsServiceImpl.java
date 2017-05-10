package com.cidic.design.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.NewsDao;
import com.cidic.design.model.News;
import com.cidic.design.service.NewsService;

@Service
@Component
@Qualifier(value = "newsServiceImpl")
@Transactional
public class NewsServiceImpl implements NewsService {

	@Autowired
	@Qualifier(value = "newsDaoImpl")
	private NewsDao newsDaoImpl;
	
	@Override
	public void createNews(News news) {
		newsDaoImpl.createNews(news);
	}

	@Override
	public void deleteNews(int id) {
		newsDaoImpl.deleteNews(id);
	}

	@Override
	public void updateNews(News news) {
		newsDaoImpl.updateNews(news);
	}

	@Override
	public Optional<News> findNewsById(int id) {
		
		return newsDaoImpl.findNewsById(id);
	}

	@Override
	public List<News> findNewsByPage(int offset, int limit) {
		return newsDaoImpl.findNewsByPage(offset, limit);
	}

}
