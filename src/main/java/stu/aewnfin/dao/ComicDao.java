package stu.aewnfin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import stu.aewnfin.entity.Comic;

public interface ComicDao {
	//新增漫画
	public void addComic(@Param("title")String title,@Param("img_uri")String img_uri);
	
	//搜索功能，关键字查找comic ，返回结果集
	public List<Comic> searchByTitle(String likeWord);
	//首页推荐，top X 
	public List<Comic> searchTopX(Map<String, ?> map);
	
	//更新漫画信息
	public void updateComicLength(@Param("id")int id);
	
	// 通过漫画id 查找漫画相关所有信息，结果唯一
	public Comic findById(int id);
	
	//下架漫画
	public void offComic(int id);
	
	//查询重名漫画
	public Comic findByTitle(String title);
	
	//作者的所有漫画
	public List<Comic> allAuthorComic(Integer author_id);
	
	//删除漫画记录
	public void deleteWrongComic(@Param("id")Integer id);
	
//	//搜索提示功能，关键字查找title ，返回结果集
//	public List<String> searchTitleByLikeWord(String likeWord);
//	

}
