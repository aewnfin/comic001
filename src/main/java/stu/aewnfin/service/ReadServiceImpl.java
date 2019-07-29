package stu.aewnfin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import stu.aewnfin.dao.ChapterDao;
import stu.aewnfin.dao.ComicDao;
import stu.aewnfin.dao.PageDao;
import stu.aewnfin.entity.Chapter;
import stu.aewnfin.entity.Comic;
import stu.aewnfin.entity.Page;

@Service("readService")
public class ReadServiceImpl implements ReadService{
	@Resource
	private ComicDao comicDao;
	@Resource
	private ChapterDao chapterDao;
	@Resource
	private PageDao pageDao;
	
	//获取漫画信息
	@Override
	public Comic getComicInfo(int id) {
		Comic comic=null;
		comic=comicDao.findById(id);
		System.out.println("漫画信息"+comic);
		return comic;
	}

	//获取漫画章节列表
	@Override
	public List<Chapter> getComicChapters(int id) {
		List<Chapter> chapters=null;
		chapters=chapterDao.getChapterListByComicId(id);
		System.out.println("漫画章节列表"+chapters);
		return chapters;
	}
	
	//获取漫画页册
	@Override
	public List<Page> getComicChapterPages(int comic_id,int chapter_part){
		List<Page> pages=null;
		
		pages=pageDao.getPages(comic_id, chapter_part);
		
		return pages;
	}

}
