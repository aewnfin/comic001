package stu.aewnfin.service;

import java.util.List;

import stu.aewnfin.entity.Comic;

public interface SearchService {

	// 搜索排序服务
	List<Comic> forTopX(String orderby, int X);

	// 搜素相似名称漫画
	List<Comic> forMoreComic(String keyWord);
	
	
	//获悉作者所有在版漫画
	public List<Comic> allAuthorComic(int author_id);
	
	
	/*
	 * 搜索
	 */
//	//搜索提示服务
//	List<String> forTips(String keyWorld);
}
