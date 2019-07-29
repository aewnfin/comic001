package stu.aewnfin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import stu.aewnfin.entity.Admin;
import stu.aewnfin.entity.Author;
import stu.aewnfin.entity.User;
import stu.aewnfin.service.LoginService;
import stu.aewnfin.service.RegisterService;
import stu.aewnfin.service.VerifyService;
import stu.aewnfin.util.NoteResult;

@Controller
@RequestMapping("/user")
public class UserController {
	// 登陆服务
	@Resource
	private RegisterService registerService;
	@Resource
	private LoginService loginService;
	@Resource
	private VerifyService verifyService;

	// 登陆操作
	@RequestMapping("/login.do")
	// Ajax
	@ResponseBody
	public NoteResult<User> userLogin(
			@RequestParam(value="username")String userName,
			@RequestParam(value="password")String passWord,
			@RequestParam(required=false,value="savecookie")String savecookie,
			HttpSession session) {
		NoteResult<User> result = null;
		System.out.println("登陆数据校验：" + userName + "," + passWord);

		// 登陆验证
		result = loginService.verifyUser(userName, passWord);
		//System.out.println(result.toString());

		
		// 保存session
		if (result.getStatus()==0) {
			
			// 取得验证结果
			session.setAttribute("user", result.getData());
			switch (result.getData().getType()) {
			case 1:
				session.setAttribute("reader", loginService.loginReader(result.getData().getId()));
				break;
			case 2:
				session.setAttribute("reader", loginService.loginReader(result.getData().getId()));
				session.setAttribute("author", loginService.loginAuthor(result.getData().getId()));
				break;
			case 3:
				session.setAttribute("admin", loginService.loginAdmin(result.getData().getId()));
				break;
			default:
				System.out.println("未认证用户");
			}

		}

//		// 判断是否 持久化session到cookies
//		if (saveCookie == "true" && user != null) {
//			Cookie cookie = new Cookie("JSESSIONID", session.getId());
//			cookie.setMaxAge(30 * 24 * 60 * 60);// 保存1个月
//			response.addCookie(cookie);
//		}

		// 清除用户敏感数据
		result.setData(null);
		return result;
	}

	// 注册操作
	@RequestMapping("/register.do")
	@ResponseBody
	public NoteResult<User> userRegister(@RequestParam(value = "username") String userName,
			@RequestParam(value = "password") String passWord, @RequestParam(value = "type") Integer type,
			@RequestParam(required = false, value = "email") String email) {
		NoteResult<User> noteResult = null;
		// 校验提交数据
		// System.out.println("注册数据校验：" + userName + "," + passWord);

		// 验证用户
		noteResult = registerService.registerUser(userName, passWord, type);
		// System.out.println(noteResult.toString());

		// "1",注册用户通过，认证新用户角色
		if (noteResult.getStatus() == 0) {

			if (type == 1) {
				// 为读者
				verifyService.beReader(noteResult.getData(), email);
				noteResult.setMsg("读者认证完成");
			}

		}

		return noteResult;

	}
	
	@RequestMapping("/beAuthor.do")
	@ResponseBody
	public NoteResult<Author> verifyAuthor(
			@RequestParam(value = "type") Integer type,
			@RequestParam(value = "IDcard") String IDcard,
			@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "secondName") String secondName,
			HttpSession session){
		NoteResult<Author> noteResult=new NoteResult<Author>();
		
		User user =(User)session.getAttribute("user");
		if(user==null) {
			noteResult.setStatus(0);
			noteResult.setMsg("请先登录");
			return noteResult;
		}
		 if (type == 2) {
				//申请成为作者
				NoteResult<User> noteResult2=verifyService.beAuthor(user, IDcard, firstName, secondName);
				noteResult.setStatus(1);
				noteResult.setMsg("已提交作者认证申请");
				if(noteResult2.getStatus()==1) {
					//初次申请，刷新用户session
					session.setAttribute("user", noteResult2.getData());
				}
			}
		return noteResult;
	}
	
	@RequestMapping("/pass.do")
	@ResponseBody
	public NoteResult<Author> authorVerifyPass(
			@RequestParam("authorid")Integer authorid,
			HttpSession session){
		NoteResult<Author> noteResult=new NoteResult<Author>();
		
		Admin admin=(Admin)session.getAttribute("admin");
		if(admin==null) {
			noteResult.setStatus(0);
			noteResult.setMsg("管理员请登录");
			return noteResult;
		}
		
		//尝试通过作者认证
		noteResult=verifyService.canBeAuthor(admin, authorid);
		noteResult.setStatus(noteResult.getStatus()+1);
		
		return noteResult;
	}

}
