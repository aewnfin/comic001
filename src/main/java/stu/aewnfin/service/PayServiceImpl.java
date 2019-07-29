package stu.aewnfin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import stu.aewnfin.dao.ChapterDao;
import stu.aewnfin.dao.ReaderDao;
import stu.aewnfin.dao.ShareDao;
import stu.aewnfin.entity.Chapter;
import stu.aewnfin.entity.Reader;
import stu.aewnfin.util.NotePay;

@Service("payService")
public class PayServiceImpl implements PayService{
	@Resource
	private ChapterDao chapterDao;
	@Resource
	private ReaderDao readerDao;
	@Resource
	private ShareDao shareDao;
	// 有效支付
	public NotePay pay(Reader reader, int comicid,int chapterid) {
		NotePay notePay = new NotePay();
		int price = reader.getPrice();
		Chapter chapter=chapterDao.getCost(comicid, chapterid);
		int cost=chapter.getCost();
		if (cost==0) {
			notePay.setSuccess(true);
			return notePay;
		}
		if (price - cost >= 0) {
			// 有偿支付达成
			notePay.setReprice(0);
			notePay.setPrice(price - cost);
			// 更新数据库
			readerDao.updatePrice( price - cost,reader.getId());
			shareDao.addShare(reader.getId(), comicid, chapterid, null, cost);
			// 成功
			notePay.setSuccess(true);

		} else {
			// 不幸支付失败
			notePay.setPrice(0);
			notePay.setSuccess(false);
		}
		System.out.println("支付信息：" + notePay.toString());
		return notePay;
	};

	// 免费支付
	public NotePay freePay(Reader reader, int comicid,int chapterid) {
		NotePay notePay = new NotePay();
		int reprice = reader.getFreeprice();
		Chapter chapter=chapterDao.getCost(comicid, chapterid);
		int cost=chapter.getCost();
		if (cost==0) {
			notePay.setSuccess(true);
			return notePay;
		}
		// 尝试免费支付
		if (reprice - cost >= 0) {
			// 免费支付达成
			notePay.setReprice(reprice - cost);
			notePay.setPrice(0);
			// 更新数据库
			readerDao.updateFreeprice(reprice - cost, reader.getId());
			// 成功
			notePay.setSuccess(true);

		} else {
			// 不幸支付失败
			notePay.setReprice(0);
			notePay.setSuccess(false);
		}
		System.out.println("支付信息：" + notePay.toString());
		return notePay;
	};
	
	//获取漫画花费
	@Override
	public int getChapterCost(int comic_id,int chapter_part) {
		Chapter chapter=chapterDao.getCost(comic_id, chapter_part);
		
		return chapter.getCost();
	}
	
}
