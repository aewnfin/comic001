package stu.aewnfin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import stu.aewnfin.dao.AdminDao;
import stu.aewnfin.dao.AuthorDao;
import stu.aewnfin.dao.ReaderDao;
import stu.aewnfin.dao.UserDao;
import stu.aewnfin.entity.Admin;
import stu.aewnfin.entity.Author;
import stu.aewnfin.entity.Reader;
import stu.aewnfin.entity.User;
import stu.aewnfin.util.NoteResult;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Resource
	private UserDao userDao;
	@Resource
	private ReaderDao readerDao;
	@Resource
	private AuthorDao authorDao;
	@Resource
	private AdminDao adminDao;

	// 通过用户名和密码验证登陆
	public NoteResult<User> verifyUser(String userName, String passWord) {
		NoteResult<User> noteResult = new NoteResult<User>();

		System.out.println("登陆验证：name=[" + userName + "],password=[" + passWord + "]");

		// 查询数据库
		User user = userDao.findByName(userName);
		if (user == null) {
			noteResult.setStatus(1);
			noteResult.setMsg("用户名不存在");
			// 。
			return noteResult;
		}
		if (!user.getPassword().equals(passWord)) {
			noteResult.setStatus(2);
			noteResult.setMsg("密码错误");
			// 。
			return noteResult;
		}
		noteResult.setStatus(0);
		noteResult.setMsg("登陆成功");
		// 更新登陆时间,取得登陆信息
		userDao.updateDate(user.getId());
		user = userDao.findById(user.getId());
		noteResult.setData(user);
		// 。
		return noteResult;
	}

	// 取得身份1
	public Reader loginReader(Integer id) {
		return readerDao.findById(id);
	}

	// 取得身份2
	public Author loginAuthor(Integer id) {
		return authorDao.findById(id);
	}

	// 取得身份3
	public Admin loginAdmin(Integer id) {
		return adminDao.findById(id);
	}

}
