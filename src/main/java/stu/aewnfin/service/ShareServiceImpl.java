package stu.aewnfin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import stu.aewnfin.dao.ChapterDao;
import stu.aewnfin.dao.ReaderDao;
import stu.aewnfin.dao.ShareDao;
import stu.aewnfin.entity.Reader;
import stu.aewnfin.entity.Share;
import stu.aewnfin.util.NoteResult;

@Service("shareService")
public class ShareServiceImpl implements ShareService{
	@Resource
	private ChapterDao chapterDao;
	@Resource
	private ReaderDao readerDao;
	@Resource
	private ShareDao shareDao;
	// 分享漫画
	public void shareComic(Share share, int share_user) {
		shareDao.share(share.getUser_id(), share.getComic_id(), share.getPart_num(), share_user);
	}

	// 获取付款记录
	public NoteResult<Share> getShare(Reader reader, int comic_id, int part_num) {
		NoteResult<Share> noteResult = new NoteResult<Share>();
		Share share = shareDao.getShare(reader.getId(), comic_id, part_num);
		if (share == null) {
			noteResult.setStatus(0);
			noteResult.setMsg("没有记录");
		} else {
			noteResult.setStatus(1);
			noteResult.setMsg("已查询到支付结果");
			noteResult.setData(share);
		}
		return noteResult;
	}

	// 是否被分享
	public boolean ifShared(int comic_id, int part_num,Reader reader) {
		boolean shered;
		List<Share> shares = shareDao.ifShared(comic_id, part_num, reader.getId());

		if (shares.isEmpty()) {
			shered = false;
		} else {
			shered = true;
		}

		return shered;
	}

}
