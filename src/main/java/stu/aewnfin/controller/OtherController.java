package stu.aewnfin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import stu.aewnfin.entity.Page;
import stu.aewnfin.entity.Reader;
import stu.aewnfin.entity.Share;
import stu.aewnfin.service.PayService;
import stu.aewnfin.service.ReadService;
import stu.aewnfin.service.ReviewService;
import stu.aewnfin.service.ShareService;
import stu.aewnfin.util.NotePay;
import stu.aewnfin.util.NoteResult;

@Controller
@RequestMapping("/for")
public class OtherController {
	@Resource
	private PayService payService;
	@Resource
	private ShareService shareService;
	@Resource
	private ReadService readService;
	@Resource
	private ReviewService reviewService;

//	//搜索提示服务
//	@RequestMapping("/fortips.do")
//	@ResponseBody
//	public List<String> forSearchTips(String keyWord){
//		List<String> tips=null;
//		tips =searchService.forTips(keyWord);
//		return tips;
//	}

	// 支付。分享
	// 免费支付
	@RequestMapping("/freepay.do")
	public String freePay(@RequestParam("comicid") String comicid, @RequestParam("chapterid") String chapterid,
			HttpSession session, ModelMap map) {
		Integer comic_id = Integer.parseInt(comicid);
		Integer chapter_id = Integer.parseInt(chapterid);

		Reader reader = (Reader) session.getAttribute("reader");
		if (reader == null) {
			return "redirect:/login";
		}

		NotePay notePay = payService.freePay(reader, comic_id, chapter_id);
		// 支付成功
		if (notePay.getSuccess()) {
			// 取得漫画资源
			List<Page> pages = null;
			reader.setFreeprice(notePay.getReprice());
			pages = readService.getComicChapterPages(comic_id, chapter_id);

			map.addAttribute("pages", pages);

			return "comicread";
		} else {
			// 支付失败
			return "falsepay";
		}

	}

	// 有效支付
	@RequestMapping("/pay.do")
	public String Pay(@RequestParam("comicid") String comicid, @RequestParam("chapterid") String chapterid,
			HttpSession session) {
		Integer comic_id = Integer.parseInt(comicid);
		Integer chapter_id = Integer.parseInt(chapterid);

		Reader reader = (Reader) session.getAttribute("reader");
		if (reader == null) {
			return "redirect:/login";
		}

		NotePay notePay = payService.pay(reader, comic_id, chapter_id);

		// 支付成功
		if (notePay.getSuccess()) {
			reader.setPrice(notePay.getPrice());
			return "forward:/for/read/" + comic_id + "/" + chapter_id;
		} else {
			// 支付失败
			return "falsepay";
		}

	}

	@RequestMapping("/pay")
	@ResponseBody
	public NoteResult<Reader> payComic(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		NoteResult<Reader> noteResult = new NoteResult<Reader>();
		// 漫画id
		Integer comicId = Integer.parseInt(request.getParameter("comicid"));
		// 漫画章节
		Integer part = Integer.parseInt(request.getParameter("part"));
		// 验证session
		Reader reader = (Reader) session.getAttribute("reader");
		if (reader == null) {
			noteResult.setStatus(3);
			noteResult.setMsg("请先登录");
			return noteResult;
		}

		System.out.println("用户" + reader.getId() + "请求阅读漫画:" + comicId + "," + part);

		// 查询-支付记录
		NoteResult<Share> shareResult = shareService.getShare(reader, comicId, part);
		noteResult.setStatus(shareResult.getStatus());
		noteResult.setMsg(shareResult.getMsg());
		if (shareResult.getStatus() == 0 && !shareService.ifShared(comicId, part, reader)) {
			// 没有权限
			// 浏览器端需要提示余额
			noteResult.setData(reader);

		}
		if (shareService.ifShared(comicId, part, reader)) {
			noteResult.setStatus(1);
			noteResult.setMsg("被其他用户分享了权限");
		}

		return noteResult;
	}

	// 漫画阅读请求
	@RequestMapping("/read/{id}/{part_num}")
	public String readComicPart(HttpSession session, @PathVariable("id") int comicId,
			@PathVariable("part_num") int chapter_part, ModelMap map) {

		List<Page> pages = null;

		// 验证登陆
		Reader reader = (Reader) session.getAttribute("reader");
		if (reader == null) {
			return "redirect:/login";
		}

		// 查询-获取权限
		NoteResult<Share> noteResult = shareService.getShare(reader, comicId, chapter_part);
		Boolean can = false;

		// 没有权限-则
		if (noteResult.getStatus() == 0) {
			// 查询分享
			Boolean shared = shareService.ifShared(comicId, chapter_part, reader);
			if (shared) {
				can = true;
			} else {
				can = false;
			}

		} else {
			can = true;
		}

		// 是否免费
		int cost = payService.getChapterCost(comicId, chapter_part);
		if (cost == 0) {
			can = true;
		}

		if (can) {
			// 取得漫画资源
			pages = readService.getComicChapterPages(comicId, chapter_part);

			map.addAttribute("pages", pages);

			return "comicread";
		} else {
			// 请充值页
			return "falsepay";
		}
	}

	// 分享操作
	@RequestMapping("/share.do")
	@ResponseBody
	public NoteResult<Share> shareComic(HttpServletRequest request, HttpSession session) {
		NoteResult<Share> sResult = null;
		// 漫画id
		Integer comicId = Integer.parseInt(request.getParameter("comicid"));
		// 漫画章节
		Integer part = Integer.parseInt(request.getParameter("part"));
		// 向用户 某某 分享-修复bug-没有输入时
		String struse = request.getParameter("shareUser");
		if (struse == "") {
			// 不做操作
			sResult = new NoteResult<Share>();
			sResult.setStatus(2);
			sResult.setMsg("没有指明分享用户");
			return sResult;
		}
		Integer share_user = Integer.parseInt(request.getParameter("shareUser"));
		Reader reader = (Reader) session.getAttribute("reader");

		if (reader == null) {
			sResult = new NoteResult<Share>();
			sResult.setStatus(3);
			sResult.setMsg("请先登录");
			return sResult;
		}

		System.out.println("用户" + reader.getId() + "分享漫画:" + comicId + "," + part);

		// 查询-获取权限
		sResult = shareService.getShare(reader, comicId, part);
		if (sResult.getStatus() == 1) {
			// 执行授权分享
			shareService.shareComic(sResult.getData(), share_user);
			sResult.setMsg("完成分享！");

		}

		return sResult;
	}
}
