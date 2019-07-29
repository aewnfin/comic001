package test.Dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import stu.aewnfin.dao.ChapterDao;
import stu.aewnfin.entity.Chapter;

public class TestChapterDao {
	private ApplicationContext ctx;
	ChapterDao dao;
	
	@Before
	public void Init() {
		//加载配置
		ctx = new ClassPathXmlApplicationContext("config/spring-mybatis.xml");
		// 获取dao对象，！开头字母小写
		dao = ctx.getBean("chapterDao", ChapterDao.class);
	}
	
	@Test
	public void test1() {
		List<Chapter> chapters=dao.getChapterListByComicId(1);
		System.out.println(chapters.toString());
	}

}
