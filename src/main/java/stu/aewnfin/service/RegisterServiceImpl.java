package stu.aewnfin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import stu.aewnfin.dao.UserDao;
import stu.aewnfin.entity.User;
import stu.aewnfin.util.NoteResult;

@Service("registerService")
public class RegisterServiceImpl implements RegisterService {
	@Resource
	private UserDao userDao;

	// 注册验证,用户名重复
	public NoteResult<User> registerUser(String userName, String passWord,Integer type) {
		NoteResult<User> noteResult = new NoteResult<User>();
//
//		System.out.println("注册验证：name=[" + userName + "]");

		// 查询数据库
		User user = userDao.findByName(userName);
		if (user == null) {
			userDao.addUser(userName, passWord,type);
			noteResult.setStatus(0);
			noteResult.setMsg("注册用户通过");
			// 。
			return noteResult;
		} else {
			noteResult.setStatus(1);
			noteResult.setMsg("用户名重复");
			// 。
			return noteResult;
		}
	}

//	// 新建用户
//	public void buildUser(String userName, String passWord) {
//		userDao.addUser(userName, passWord);
//	}

}
