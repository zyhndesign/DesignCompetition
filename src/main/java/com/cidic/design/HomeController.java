package com.cidic.design;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cidic.design.exception.ServerException;
import com.cidic.design.model.News;
import com.cidic.design.model.User;
import com.cidic.design.service.JudgeService;
import com.cidic.design.service.NewsService;
import com.cidic.design.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends DcController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	@Qualifier(value = "newsServiceImpl")
	private NewsService newsServiceImpl;

	@Autowired
	@Qualifier(value = "userServiceImpl")
	private UserService userServiceImpl;

	@Autowired
	@Qualifier(value = "judgeServiceImpl")
	private JudgeService judgeServiceImpl;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		try {
			List<News> newsList = newsServiceImpl.getTopThreeNews();
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("/frontend/index");
			modelView.addObject(newsList);
			return modelView;
		} catch (Exception e) {
			throw new ServerException(400, "服务器内部出错了");
		}

	}

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, Model model) {
		return "/frontend/login";
	}

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request, Model model) {
		try {
			List<News> newsList = newsServiceImpl.getTopThreeNews();
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("/frontend/index");
			modelView.addObject(newsList);
			return modelView;
		} catch (Exception e) {
			throw new ServerException(400, "服务器内部出错了");
		}
	}

	@RequestMapping(value = "/dologin")
	public String doLogin(HttpServletRequest request, Model model) {
		String msg = "";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			if (subject.isAuthenticated()) {
				
				try {
					subject.checkRole("管理员");
					return "redirect:/news/newsMgr";
				} catch (AuthorizationException e) {
					try{
						subject.checkRole("评委");
						return "redirect:/review/judgeIndex";
					}
					catch (AuthorizationException ex) {
						return "redirect:/production/works";
					}
					
				}
			} else {
				return "/frontend/login";
			}
		} catch (IncorrectCredentialsException e) {
			msg = "登录密码错误.";
			model.addAttribute("error", msg);
			System.out.println(msg);
		} catch (ExcessiveAttemptsException e) {
			msg = "登录失败次数过多";
			model.addAttribute("error", msg);
			System.out.println(msg);
		} catch (LockedAccountException e) {
			msg = "帐号已被锁定.";
			model.addAttribute("error", msg);
			System.out.println(msg);
		} catch (DisabledAccountException e) {
			msg = "帐号已被禁用. ";
			model.addAttribute("error", msg);
			System.out.println(msg);
		} catch (ExpiredCredentialsException e) {
			msg = "帐号已过期.";
			model.addAttribute("error", msg);
			System.out.println(msg);
		} catch (UnknownAccountException e) {
			msg = "帐号不存在.或者未激活";
			model.addAttribute("error", msg);
			System.out.println(msg);
		} catch (UnauthorizedException e) {
			msg = "您没有得到相应的授权！";
			model.addAttribute("error", msg);
			System.out.println(msg);
		}
		return "/frontend/login";
	}

	@RequestMapping(value = "/reviewLogin")
	public String reviewLogin(HttpServletRequest request, Model model) {
		String msg = "";
		String username = request.getParameter("email");
		String validCode = request.getParameter("validCode");
		String password = judgeServiceImpl.findJudgePwdByEmail(username,validCode);
		int round = Integer.parseInt(request.getParameter("round").toString());
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			if (subject.isAuthenticated()) {
				
				try{
					subject.checkRole("评委");
					System.out.println("评审页面");
					return "redirect:/review/judgeIndex/"+round;
				}
				catch (AuthorizationException ex) {
					
				}
					
			} else {
				return "/frontend/login";
			}
		} catch (IncorrectCredentialsException e) {
			msg = "登录密码错误.";
			model.addAttribute("error", msg);
		} catch (ExcessiveAttemptsException e) {
			msg = "登录失败次数过多";
			model.addAttribute("error", msg);
		} catch (LockedAccountException e) {
			msg = "帐号已被锁定.";
			model.addAttribute("error", msg);
		} catch (DisabledAccountException e) {
			msg = "帐号已被禁用. ";
			model.addAttribute("error", msg);
		} catch (ExpiredCredentialsException e) {
			msg = "帐号已过期.";
			model.addAttribute("error", msg);
		} catch (UnknownAccountException e) {
			msg = "帐号不存在.或者未激活";
			model.addAttribute("error", msg);
		} catch (UnauthorizedException e) {
			msg = "您没有得到相应的授权！";
			model.addAttribute("error", msg);
		}
		catch(AuthorizationException e){
			msg = "认证失败！";
			model.addAttribute("error", msg);
		}
		return "/frontend/login";
	}
	@RequestMapping(value = "/logout")
	public String doLogout(HttpServletRequest request, Model model) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "/frontend/login";
	}
}
