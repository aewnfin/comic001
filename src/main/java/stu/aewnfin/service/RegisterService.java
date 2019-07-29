package stu.aewnfin.service;

import stu.aewnfin.entity.User;
import stu.aewnfin.util.NoteResult;

public interface RegisterService {
	// 尝试注册用户
	public NoteResult<User> registerUser(String userName, String passWord,Integer type);
//
//	// 新建用户
//	public void buildUser(String userName);

}
