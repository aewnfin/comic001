package stu.aewnfin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import stu.aewnfin.dao.ComicDao;

@Service("offService")
public class OffServiceImpl implements OffService{
	@Resource
	private ComicDao comicDao;
	
	//下架漫画
	public void offComic(Integer comic_id) {
		comicDao.offComic(comic_id);
	}
}
