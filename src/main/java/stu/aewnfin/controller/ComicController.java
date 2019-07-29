package stu.aewnfin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import stu.aewnfin.entity.Author;
import stu.aewnfin.entity.Chapter;
import stu.aewnfin.entity.Comic;
import stu.aewnfin.entity.Publish;
import stu.aewnfin.service.OffService;
import stu.aewnfin.service.ReadService;
import stu.aewnfin.service.SearchService;
import stu.aewnfin.service.UploadService;
import stu.aewnfin.util.NoteResult;

@Controller
@RequestMapping("/comic")
public class ComicController {

	@Resource
	private UploadService uploadService;
	@Resource
	private SearchService searchService;
	@Resource
	private ReadService readService;
	@Resource
	private OffService offService;

	// 获取漫画主页
	@RequestMapping("/page/{id}") // ,method = RequestMethod.GET)
	public String intoComic(@PathVariable("id") int comicId, ModelMap map) {
		Comic comic = null;
		List<Chapter> chapters = null;

		comic = readService.getComicInfo(comicId);
		chapters = readService.getComicChapters(comicId);

		map.addAttribute("comic", comic);
		map.addAttribute("chapters", chapters);
		return "comicpage";
	}

	// 关键词搜索漫画
	@RequestMapping("/forcomic.do")
	public String searchComic(HttpServletRequest request, ModelMap map) {
		String keyWord = request.getParameter("keyword");
		List<Comic> comics = null;
		comics = searchService.forMoreComic(keyWord);
		System.out.println(comics.size());
		map.addAttribute("comics", comics);
		return "searchpage";
	}

	// 新增漫画
	@RequestMapping("/newcomic.do")
	@ResponseBody
	public NoteResult<Comic> newComic(@RequestParam("title") String title,
			@RequestParam("pename")String pename,
			@RequestParam("hoverpage") MultipartFile file,
			HttpSession session) {
		NoteResult<Comic> noteResult = null;
		
		Author author=(Author)session.getAttribute("author");
		if(author == null) {
			noteResult = new NoteResult<>();
			noteResult.setStatus(3);
			noteResult.setMsg("请先登录");
			return noteResult;
		}
		
		if (!file.isEmpty()) {
			noteResult = uploadService.sameComic(title, file,author.getId(),pename);
			
		} else {
			noteResult = new NoteResult<>();
			noteResult.setStatus(2);
			noteResult.setMsg("空文件");
		}
		return noteResult;
	}

	//上传漫画
	@RequestMapping("/uploadcomic.do")
	@ResponseBody
	public NoteResult<Object> uploadComic(
			@RequestParam("pages")MultipartFile[] files,
			@RequestParam("comicid")Integer comicid,
			@RequestParam("part")Integer part,
			@RequestParam("title")String title,
			@RequestParam("cost")Integer cost,
			HttpSession session) {
		NoteResult<Object> noteResult=new NoteResult<>();;
		
		Author author=(Author)session.getAttribute("author");
		if(author == null) {
			noteResult.setStatus(0);
			noteResult.setMsg("请先登录");
			return noteResult;
		}
		System.out.println("作家"+author.getId()+"申请更新漫画");
		//是否有权更新
		NoteResult<Publish> publishnot=uploadService.publishCan(author, comicid);
		if(publishnot.getStatus()==1) {
			noteResult.setStatus(3);
			noteResult.setMsg(publishnot.getMsg());
			return noteResult;
		}
		//章节是否已经存在
		NoteResult<Chapter> chapnot=uploadService.chapterCan(comicid, part);
		if(chapnot.getStatus()!=0) {
			noteResult.setStatus(4);
			noteResult.setMsg(chapnot.getMsg());
			return noteResult;
		}
	
		//文件多于零个
		if(files!=null &&files.length>0) {
			System.out.println(files.length);
			System.out.println(files.toString());
			
			NoteResult<Chapter> chapternot=uploadService.uploadChapter(comicid, part, title, cost, files);
			if(chapternot.getStatus()==1) {
				noteResult.setStatus(5);
				noteResult.setMsg("新章节已发布");
			}else {
				noteResult.setStatus(6);
				noteResult.setMsg("章节发布失败");
			}
		} else {
			System.out.println(files.length);
			noteResult.setStatus(2);
			noteResult.setMsg("空文件");
		}
		
		
		return noteResult;
	}

}
