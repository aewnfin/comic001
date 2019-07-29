package stu.aewnfin.entity;

public class Share {
	//用户id
	private Integer user_id;
	//漫画id
	private Integer comic_id;
	//章节序号
	private Integer part_num;
	//分享用户的id
	private Integer share_user;
	//用户支付记录
	private Integer pay;
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getComic_id() {
		return comic_id;
	}
	public void setComic_id(Integer comic_id) {
		this.comic_id = comic_id;
	}
	public Integer getPart_num() {
		return part_num;
	}
	public void setPart_num(Integer part_num) {
		this.part_num = part_num;
	}
	public Integer getShare_user() {
		return share_user;
	}
	public void setShare_user(Integer share_user) {
		this.share_user = share_user;
	}
	public Integer getPay() {
		return pay;
	}
	public void setPay(Integer pay) {
		this.pay = pay;
	}
	@Override
	public String toString() {
		return "Share [user_id=" + user_id + ", comic_id=" + comic_id + ", part_num=" + part_num + ", share_user="
				+ share_user + ", pay=" + pay + "]";
	}
	
	
}
