package stu.aewnfin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import stu.aewnfin.entity.Chapter;
import stu.aewnfin.entity.Share;

public interface ChapterDao {
	//查找漫画章节，需要漫画_id
	public List<Chapter> getChapterListByComicId(Integer comic_id);
	
	//获取阅读权限
	public Share getShare(int user_id,int comic_id,int part_num,Integer share_user) ;
	
	//添加章节信息
	public void addChapter(@Param("id")int comic_id,@Param("part")Integer part,@Param("title")String title,@Param("cost")Integer cost);
	
	//获悉花费
	public Chapter getCost(@Param("comic_id")int comic_id,@Param("part")int part);
	
}
