package stu.aewnfin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import stu.aewnfin.entity.Author;

public interface AuthorDao {

	//新增作者
	public void addAuthor(@Param("id")Integer id,@Param("IDcard")String IDcard,@Param("firstName")String firstName,@Param("secondName")String secondName);
	
	//作者认证通过更新
	public void verifyAuthor(@Param("id")Integer id);
	
	//作者登录
	public Author findById(@Param("id")Integer id);
	
	//所有未认证作者
	public List<Author> AllForVerify();
	
	//是否已经申请成为作者
	public Boolean ifHaveAuthor(@Param("id")Integer id);
	
	//更新信息
	public void reVerifyAuthor(@Param("id")Integer id,@Param("IDcard")String IDcard,@Param("firstName")String firstName,@Param("secondName")String secondName);
}
