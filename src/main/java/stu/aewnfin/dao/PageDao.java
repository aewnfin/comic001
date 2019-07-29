package stu.aewnfin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import stu.aewnfin.entity.Page;

public interface PageDao {

	//获取漫画册，图片地址
	public List<Page> getPages(@Param("id")int id,@Param("part")int part);
	
	//新增页
	public void addPage(@Param("id")int comic_id,@Param("part")int chapter_part,@Param("num")int page_num,@Param("content")String content);
}
