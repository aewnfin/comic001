package stu.aewnfin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import stu.aewnfin.entity.Author;
import stu.aewnfin.entity.Comic;
import stu.aewnfin.entity.User;
import stu.aewnfin.service.SearchService;

@Controller
public class IndexController {
	@Resource
	private SearchService searchService;

	//返回首页
	@RequestMapping("/index")
	public String homePage(
			ModelMap map, 
			HttpServletRequest request,
			HttpSession session) {

		map.put("indexMsg", "hello,It's a comfortable day, perfect for reading comics.");
		System.out.println("this is homepage!!!");
		
		User user = (User) session.getAttribute("user");
		if (user != null) {
			map.put("hello", "你好！ " + user.getName());
			switch (user.getType()) {
			case 1:
				map.put("verify","作者认证");
				break;
			case 2:
				Author author=(Author)session.getAttribute("author");
				if(author.getVerify()==false) {
					map.put("verify","作者认证中...");
				}else {
					map.put("verify","上传作品");
				}
				break;
			case 3:
				map.put("verify","管理员");
				break;
			default:
				map.put("verify", "？未知用户？");
				break;
			}
		}else {
			map.put("hello", "登陆/注册");
		}
		
		//获取 用于首页展示的漫画信息
		List<Comic> comicTop = searchService.forTopX("update", 8);
		//System.out.println(comicTop.toString());
		map.put("comicTop", comicTop);

		return "comic001";
	}
	
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
	@RequestMapping("/verify")
	public String verifyPage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user==null) {
			return "forward:/login";
		}
		switch (user.getType()) {
		case 1:
			return "verify";
		case 2:
			Author author=(Author)session.getAttribute("author");
			if(author.getVerify()==true) {
				//已认证作者转至上传
				return "upload";
			}else {
				//未认证，继续认证
				return "verify";
			}
		case 3:
			return "admin";
			
		}
		return "falsepay";
	}
//
//	@RequestMapping("/status")
//	public String statusPage() {
//		return "status";
//	}
}
