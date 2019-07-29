package stu.aewnfin.service;

import stu.aewnfin.entity.Reader;
import stu.aewnfin.util.NotePay;

public interface PayService {
	/*
	 * 支付
	 */
	// 有效支付
	public NotePay pay(Reader reader, int comicid, int chapterid);

	// 免费支付
	public NotePay freePay(Reader reader, int comicid, int chapterid);

	//获取漫画花费
			public int getChapterCost(int comic_id,int chapter_part);
}
