package stu.aewnfin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import stu.aewnfin.entity.Share;

public interface ShareDao {
	//获取收藏权限-取得分享记录
	public Share getShare(@Param("user_id")int user_id,@Param("comic_id")int comic_id,@Param("part_num")int part_num);
	
	//添加收藏记录
	public void addShare(@Param("user_id")int user_id,@Param("comic_id")int comic_id,@Param("part_num")int part_num,@Param("share_user")Integer share_user,@Param("pay")Integer pay);
	
	//更新分享-向其它用户分享
	public void share(@Param("user_id")int user_id,@Param("comic_id")int comic_id,@Param("part_num")int part_num,@Param("share_user")Integer share_user);
	
	//查询被分享
	public List<Share> ifShared(@Param("comic_id")int comic_id,@Param("part_num")int part_num,@Param("share_user")int share_user);
}
