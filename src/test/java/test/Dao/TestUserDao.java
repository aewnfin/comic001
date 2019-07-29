package test.Dao;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import stu.aewnfin.dao.UserDao;
import stu.aewnfin.entity.User;

public class TestUserDao {
	private ApplicationContext ctx;
	UserDao dao;
	
	@Before
	public void Init() {
		ctx = new ClassPathXmlApplicationContext("config/spring-mybatis.xml");
		// 获取dao对象
		dao = ctx.getBean("userDao", UserDao.class);
	}

	@Test
	public void testUser() {	
		User user = dao.findByName("aewnfi");
		if (user != null) {
			System.out.println(user);
		} else {
			System.out.println("用户不存在");
		}
	}
	
//	@Test
//	public void testUser2() {	
//		User user = dao.findByEmail("aewnfin@qq.com");
//		if (user != null) {
//			System.out.println(user);
//		} else {
//			System.out.println("用户不存在");
//		}
//	}
	
	@Test
	public void testUser3() {	
		User user = dao.findById(2);
		if (user != null) {
			System.out.println(user);
		} else {
			System.out.println("用户不存在");
		}
	}
	
//	@Test
//	public void testUser4() {	
//		List<String> userNameTips= dao.searchSameNameByLikeWord("%用户%");
//		if (userNameTips != null) {
//			System.out.println(userNameTips);
//		} else {
//			System.out.println("用户不存在");
//		}
//	}
}
