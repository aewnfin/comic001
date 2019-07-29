package stu.aewnfin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import stu.aewnfin.dao.AdminDao;
import stu.aewnfin.dao.AuthorDao;
import stu.aewnfin.dao.ReaderDao;
import stu.aewnfin.dao.UserDao;
import stu.aewnfin.entity.Admin;
import stu.aewnfin.entity.Author;
import stu.aewnfin.entity.User;
import stu.aewnfin.util.NoteResult;

@Service("verifyService")
public class VerifyServiceImpl implements VerifyService{
	@Resource
	private ReaderDao readerDao;
	@Resource
	private AuthorDao authorDao;
	@Resource
	private AdminDao adminDao;
	@Resource
	private UserDao userDao;
	
	//user身份类型-作者
	private static final Integer AUTHOR=2;
	
	//读者认证
	public void beReader(User user,String email) {
		//无则新增
		readerDao.addReader(user.getId(), email);
		//有则更新
		
	}
			
	//作者认证申请
	public NoteResult<User> beAuthor(User user,String IDcard,String firstName,String secondName) {
		NoteResult<User> noteResult=new NoteResult<>();
		if(authorDao.ifHaveAuthor(user.getId())) {
			authorDao.reVerifyAuthor(user.getId(), IDcard, firstName, secondName);
			noteResult.setStatus(2);
			noteResult.setMsg("已经重新提交申请");
		}else {
			authorDao.addAuthor(user.getId(), IDcard, firstName, secondName);
			userDao.changeType(user.getId(), AUTHOR);
			noteResult.setStatus(1);
			noteResult.setMsg("已经提交申请");
			noteResult.setData(userDao.findById(user.getId()));
		}
		return noteResult;
	}
			
	//作者认证通过
	public NoteResult<Author> canBeAuthor(Admin admin,Integer id) {
		System.out.println("管理员："+admin.getId()+"尝试进行作者"+id+"认证");
		NoteResult<Author> noteResult=new NoteResult<Author>();
		
		Author author=authorDao.findById(id);
		//申请是否存在
		if(author==null) {
			noteResult.setStatus(0);
			noteResult.setMsg("申请不存在");
			return noteResult;
		}
		
		//是否重复认证
		if(author.getVerify()) {
			noteResult.setStatus(1);
			noteResult.setMsg("申请已通过，无需重复认证");
		}else {
			authorDao.verifyAuthor(id);
			noteResult.setStatus(2);
			noteResult.setMsg("认证成功");
		}

		return noteResult;
	}
}
