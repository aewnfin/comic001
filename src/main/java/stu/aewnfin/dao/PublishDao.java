package stu.aewnfin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import stu.aewnfin.entity.Publish;

public interface PublishDao {
	//添加发布记录
	public void addPublish(@Param("author_id")Integer author_id,@Param("penName")String penName,@Param("comic_id")Integer comic_id);
	
	//查询发布记录
	public List<Publish> findAllByAuthorId(@Param("author_id")Integer author_id);
	
	//是否 存在发布记录
	public Boolean truehavePublish(@Param("author_id")Integer author_id,@Param("comic_id")Integer comic_id);
}
