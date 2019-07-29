package stu.aewnfin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import stu.aewnfin.entity.Review;

public interface ReviewDao {

	//新增评论
	public void addReview(@Param("user_id")Integer user_id,@Param("comic_id")Integer comic_id,@Param("comment")String comment,@Param("type")Integer type);
	
	//相关漫画所有评论
	public List<Review> findAllReviewForComic(@Param("comic_id")Integer comic_id);
	
}
