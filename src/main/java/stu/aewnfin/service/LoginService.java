package stu.aewnfin.service;

import stu.aewnfin.entity.Admin;
import stu.aewnfin.entity.Author;
import stu.aewnfin.entity.Reader;
import stu.aewnfin.entity.User;
import stu.aewnfin.util.NoteResult;

public interface LoginService {
	// 通过用户名和密码验证登陆
	public NoteResult<User> verifyUser(String userName, String passWord);

	// 取得身份1
	public Reader loginReader(Integer id);

	// 取得身份2
	public Author loginAuthor(Integer id);

	// 取得身份3
	public Admin loginAdmin(Integer id);
}
