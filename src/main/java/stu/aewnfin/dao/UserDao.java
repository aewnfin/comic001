package stu.aewnfin.dao;


import org.apache.ibatis.annotations.Param;

import stu.aewnfin.entity.User;

public interface UserDao {
	//是否存在重名
	public Boolean ifHaveName(@Param("name")String name);
	
	//用户登录
	public User findByName(@Param("name")String name);

	//新增用户
	public void addUser(@Param("name")String name, @Param("password")String password,@Param("type")Integer type);

	//更新登录时间
	public void updateDate(@Param("id")Integer id);
	
	//取用户信息
	public User findById(@Param("id")Integer id);

	//更改用户类型
	public void changeType(@Param("id")Integer id,@Param("type")Integer type);
}
