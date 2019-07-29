package stu.aewnfin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import stu.aewnfin.dao.ComicDao;
import stu.aewnfin.entity.Comic;

@Service("searchService")
public class SearchServiceImpl implements SearchService{
	@Resource
	private ComicDao comicDao;
	//返回按xx排序的前X项漫画
	@Override
	public List<Comic> forTopX(String orderby, int X) {
		List<Comic> topX=null;
		
		Map<String, Object> map=new HashMap<>();
		map.put("orderBy", orderby);
		map.put("X", X);
		topX=comicDao.searchTopX(map);
		
		return topX;
	}

	//返回漫画搜素结果集
	@Override
	public List<Comic> forMoreComic(String keyWord) {
//		String keyWord=request.getParameter("keyword");
		List<Comic> comics=null;
		System.out.println(keyWord);
		comics=comicDao.searchByTitle("%"+keyWord+"%");
		
		return comics;
	}
	//获悉所有在版漫画
	public List<Comic> allAuthorComic(int author_id){
		return comicDao.allAuthorComic(author_id);
	}
		
	
}
