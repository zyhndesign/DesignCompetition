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
import com.cidic.design.dao.ProductionDao;
import com.cidic.design.dao.ReviewDao;
import com.cidic.design.dao.UserDao;
import com.cidic.design.dao.impl.RoundJudgeDaoImpl;
import com.cidic.design.model.MailBean;
import com.cidic.design.model.Production;
import com.cidic.design.model.Review;
import com.cidic.design.model.RoundJudge;
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
	
	@Autowired
	@Qualifier(value = "roundJudgeDaoImpl")
	private RoundJudgeDaoImpl roundJudgeDaoImpl;
	
	@Autowired
	@Qualifier(value = "productionDaoImpl")
	private ProductionDao productionDaoImpl;
	
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
	public List<Production> getReviewListByUserId(int userId, int scoreSign, int round, int offset, int limit) {
		return reviewDaoImpl.getReviewListByUserId(userId, scoreSign, round, offset, limit);
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
			
			StringBuffer sBuilder = new StringBuffer("尊敬的评委：</br>您好！感谢您在百忙之中担任首届湖南省老年人服务产品设计大赛的评委，现在您收到的是已经通过审核的产品组生活辅助类的产品。</br>"
					+ " 打开链接即进入评审界面，请点击作品开始评分，点击“保存”来保存您的分数，当完成所有的打分后，提交分数后将无法更改，若在截止日期</br>"
					+ " 之前您未点击“提交”，截止系统时将自动提交所有您之前保存的分数，未打分的作品将计零分；截止日期之前该链接一直有效，</br>"
					+ " 所有通过该链接的打分都将视作您的个人行为，请勿与他人分享该链接。本轮评审截止日期为2017年9月8日，请您在截止日期前提交。</br>"
					+ " 再次感谢！</br></br>");
			sBuilder.append("<a style=\"color:#B03532\" href=\"" + configInfo.email_active_url + "/reviewLogin?email=");
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

	@Override
	public void bindProductAndRound(int productionId, int round) {
		RoundJudge roundJudge = roundJudgeDaoImpl.getRoundJudgeById(round);
		if (roundJudge.getJudge() != null){
			String[] judges = roundJudge.getJudge().split("\\,");
			for (String judgeId : judges){
				Review review = new Review();
				review.setCreatetime(new Date());
				review.setRound((byte)round);
				review.setUserId(Integer.parseInt(judgeId));
				review.setProductionId(productionId);
				reviewDaoImpl.createReview(review);
			}
			productionDaoImpl.updateRoundById(productionId, round);
		}
	}

	@Override
	public void updateReviewScoreByCondition(int productionId, int userId, int round, int score) {
		reviewDaoImpl.updateReviewScoreByCondition(productionId, userId, round, score);
	}

	@Override
	public int getScoreByCondition(int productionId, int userId, int round) {
		// TODO Auto-generated method stub
		return reviewDaoImpl.getScoreByCondition(productionId, userId, round);
	}

}
