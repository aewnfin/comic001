package stu.aewnfin.service;

import java.util.List;

import stu.aewnfin.entity.Chapter;
import stu.aewnfin.entity.Comic;
import stu.aewnfin.entity.Page;

public interface ReadService {
	//获取漫画信息
		public Comic getComicInfo(int id);
		
		//获取漫画章节列表
		public List<Chapter> getComicChapters(int id);
		
		//获取漫画页册
		public List<Page> getComicChapterPages(int comic_id,int chapter_part);
		
}
