package com.cidic.design;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cidic.design.model.User;
import com.cidic.design.model.UserPageModel;
import com.cidic.design.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserTest {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userServiceImpl;
	
	@Test
	public void userTest(){
		/*
		User user = new User();
		user.setEmail("423789298@qq.com");
		user.setAddress("湖南省长沙市岳麓区湖南大学设计艺术学院");
		user.setCreatetime(new Date());
		user.setPassword("testyuanxiang001");
		user.setMobile("18975182468");
		user.setRealname("袁翔");
		userServiceImpl.createUser(user);
		
		user = new User();
		user.setEmail("cidic@cidic.cn");
		user.setAddress("湖南省长沙市后湖国际艺术区");
		user.setCreatetime(new Date());
		user.setPassword("testcidic002");
		user.setMobile("18684799929");
		user.setRealname("中意创新中心");
		userServiceImpl.createUser(user);
		
		user = new User();
		user.setEmail("maximuslee@126.com");
		user.setAddress("湖南省长沙市后湖国际艺术区");
		user.setCreatetime(new Date());
		user.setPassword("testmaximuslee003");
		user.setMobile("18684799929");
		user.setRealname("中意创新中心");
		userServiceImpl.createUser(user);
		
		
		User user = new User();
		user.setEmail("271838424@qq.com");
		user.setAddress("湖南省长沙市岳麓区");
		user.setCreatetime(new Date());
		user.setPassword("testknx_manage_001");
		user.setMobile("18911111111");
		user.setRealname("康乃馨养老集团");
		userServiceImpl.createUser(user);
		
		user = new User();
		user.setEmail("381437751@qq.com");
		user.setAddress("湖南省长沙市岳麓区");
		user.setCreatetime(new Date());
		user.setPassword("testknx_manage_002");
		user.setMobile("18911111111");
		user.setRealname("康乃馨养老集团");
		userServiceImpl.createUser(user);
		
		*/
	}
	
	//@Test
	public void updateUserValidTest(){
			
		userServiceImpl.updateValidSign("csboyty@163.com", 1);
	}
	
	//@Test
	public void getUserTest(){
			
		UserPageModel userPageModel = userServiceImpl.getUserByPage(0, 10);
		System.out.println(userPageModel.getCount());
	}
	//@Test
	public void buildRelationshipUserAndRole(){
		userServiceImpl.correlationRoles(1L, 3L,2L);
	} 
	
	//@Test
	public void unBuildRelationshipUserAndRole(){
		userServiceImpl.correlationRoles(1L, 3L,2L);
	}
}
