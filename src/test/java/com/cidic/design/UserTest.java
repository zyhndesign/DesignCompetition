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
	
	//@Test
	public void userTest(){
		User user = new User();
		user.setEmail("liling@cidic.cn");
		user.setAddress("湖南省长沙市后湖国际艺术区");
		user.setCreatetime(new Date());
		user.setPassword("111111");
		user.setMobile("13119019836");
		user.setRealname("brain");
		user.setSlot("f309a706ea681b5a020ea78278121198");
		userServiceImpl.createUser(user);
	}
	
	@Test
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
