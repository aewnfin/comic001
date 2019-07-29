package stu.aewnfin.entity;

import java.sql.Timestamp;

public class Review {
	private Integer user_id;
	private Integer comic_id;
	private String comment;
	private Timestamp date;
	private Integer type;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Review [user_id=" + user_id + ", comic_id=" + comic_id + ", comment=" + comment + ", date=" + date
				+ ", type=" + type + "]";
	}

}
