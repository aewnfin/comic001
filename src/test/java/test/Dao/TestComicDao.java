package test.Dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import stu.aewnfin.dao.ComicDao;
import stu.aewnfin.entity.Comic;

public class TestComicDao {
	private ApplicationContext ctx;
	ComicDao dao;
	
	@Before
	public void Init() {
		//加载配置
		ctx = new ClassPathXmlApplicationContext("config/spring-mybatis.xml");
		// 获取dao对象
		dao = ctx.getBean("comicDao", ComicDao.class);
	}

	@Test
	public void testComic() {	
		Comic comic = dao.findById(3);
		if (comic != null) {
			System.out.println(comic);
		} else {
			System.out.println("漫画不存在");
		}
	}
//	@Test
//	public void testComic2() {	
//		List<String> tips= dao.searchTitleByLikeWord("%三%");
//		if (tips != null) {
//			System.out.println(tips);
//		} else {
//			System.out.println("找不到类似的");
//		}
//	}
	@Test
	public void testComic3() {	
		List<Comic> cm= dao.searchByTitle("%无%");
		if (cm != null) {
			System.out.println(cm);
			System.out.println(cm.size());
		} else {
			System.out.println("找不到类似的");
		}
	}
	@Test
	public void testComic4() {	
		Map<String, Object> map=new HashMap<>();
		map.put("orderBy", "update");
		map.put("X", 2);
		List<Comic> cm= dao.searchTopX(map);
		if (cm != null) {
			System.out.println(cm);
			System.out.println(cm.size());
		} else {
			System.out.println("找不到类似的");
		}
	}
	
}
