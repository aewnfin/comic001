package stu.aewnfin.service;

import stu.aewnfin.entity.Reader;
import stu.aewnfin.entity.Share;
import stu.aewnfin.util.NoteResult;

public interface ShareService {

	/*
	 * 分享
	 */
	// 分享漫画
	public void shareComic(Share share, int share_user);

	// 获取付款记录
	public NoteResult<Share> getShare(Reader reader, int comic_id, int part_num);

	// 是否被分享
	public boolean ifShared(int comic_id, int part_num,Reader reader);

}
