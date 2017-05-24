package com.cidic.design.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cidic.design.dao.JudgeDao;
import com.cidic.design.dao.ReviewDao;
import com.cidic.design.dao.UserDao;
import com.cidic.design.model.MailBean;
import com.cidic.design.model.Production;
import com.cidic.design.model.Review;
import com.cidic.design.service.MailService;
import com.cidic.design.service.ReviewService;
import com.cidic.design.util.ConfigInfo;


@Service
@Component
@Qualifier(value = "reviewServiceImpl")
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	@Qualifier(value = "reviewDaoImpl")
	private ReviewDao reviewDaoImpl;
	
	@Autowired
	@Qualifier(value = "judgeDaoImpl")
	private JudgeDao judgeDaoImpl;
	
	@Autowired
	@Qualifier(value = "configInfo")
	private ConfigInfo configInfo;
	
	@Autowired
	@Qualifier(value = "mailServiceImpl")
	private MailService mailServiceImpl;
	
	@Override
	public void createReview(Review review) {
		reviewDaoImpl.createReview(review);
	}

	@Override
	public void updateReview(Review review) {
		reviewDaoImpl.updateReview(review);
	}

	@Override
	public void deleteReview(int id) {
		reviewDaoImpl.deleteReview(id);
	}

	@Override
	public List<Review> getReviewListByProductionId(int productionId) {
		return reviewDaoImpl.getReviewListByProductionId(productionId);
	}

	@Override
	public List<Production> getReviewListByUserId(int userId, int scoreSign, int offset, int limit) {
		return reviewDaoImpl.getReviewListByUserId(userId, scoreSign, offset, limit);
	}

	@Override
	public void createReviews(int userId, String productIds, int round) {
		String[] productStrIds = productIds.split("\\,");
		for (String pId : productStrIds){
			Review review = new Review();
			review.setCreatetime(new Date());
			review.setRound((byte)round);
			review.setUserId(userId);
			review.setProductionId(Integer.parseInt(pId));
			reviewDaoImpl.createReview(review);
		}
		
	}

	@Override
	public void updateReviewScore(int id, int score) {
		reviewDaoImpl.updateReviewScore(id, score);
	}

	@Override
	public List<String> getSendEmailByRound(int round) {
		
		return reviewDaoImpl.getSendEmailByRound(round);
	}

	@Override
	public void sendReviewEmail(int round) {
		List<String> emailList = reviewDaoImpl.getSendEmailByRound(round);
		
		for (String email : emailList){
			String validCode = UUID.randomUUID().toString();
			judgeDaoImpl.updateJudgeValidCodeByEmail(email, validCode);
			
			StringBuffer sBuilder = new StringBuffer("尊敬的评委，请点击以下链接，经本次大赛的第 "+round+" 次评审</br>");
			sBuilder.append("<a href=\"" + configInfo.email_active_url + "/reviewLogin?email=");
			sBuilder.append(email);
			sBuilder.append("&validCode=");
			sBuilder.append(validCode);
			sBuilder.append("&round=");
			sBuilder.append(round);
			sBuilder.append("\">点击进行评审!：");
			sBuilder.append("</a>");

			MailBean mailBean = new MailBean();
			mailBean.setContext(sBuilder.toString());
			mailBean.setFrom(configInfo.email_active_from);
			mailBean.setFromName(configInfo.email_active_from_name);
			mailBean.setSubject(configInfo.email_review_subject);
			mailBean.setToEmails(new String[] { email });
			try {
				mailServiceImpl.sendMail(mailBean);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
