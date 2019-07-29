package stu.aewnfin.dao;


import org.apache.ibatis.annotations.Param;

import stu.aewnfin.entity.Reader;

public interface ReaderDao {
	//新增reader
	public void addReader(@Param("id")Integer id,@Param("email")String email);

	//读者登录
	public Reader findById(@Param("id")Integer id);
	
	//更新用户余额
	public void updatePrice(@Param("price")Integer price,@Param("id")Integer id);
	//更新用户免费额度
	public void updateFreeprice(@Param("freeprice")Integer freeprice,@Param("id")Integer id);
	
	//是否已存在读者
	public Boolean ifHaveReader(@Param("id")Integer id);
}
