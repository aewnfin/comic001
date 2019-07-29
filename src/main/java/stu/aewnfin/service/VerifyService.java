package stu.aewnfin.service;

import stu.aewnfin.entity.Admin;
import stu.aewnfin.entity.Author;
import stu.aewnfin.entity.User;
import stu.aewnfin.util.NoteResult;

public interface VerifyService {

	//读者认证
	public void beReader(User user,String email);
		
	//作者认证申请
	public NoteResult<User> beAuthor(User user,String IDcard,String firstName,String secondName);
		
	//作者认证通过
	public NoteResult<Author> canBeAuthor(Admin admin,Integer id);
	
}
