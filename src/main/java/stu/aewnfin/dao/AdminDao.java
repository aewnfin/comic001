package stu.aewnfin.dao;

import org.apache.ibatis.annotations.Param;

import stu.aewnfin.entity.Admin;

public interface AdminDao {
	
	//管理员登陆
	public Admin findById(@Param("id")Integer id);

}
