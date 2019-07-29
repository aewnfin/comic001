package stu.aewnfin.entity;

import java.sql.Date;

public class Comic {
	private Integer id;
	private String title;
	private String img_uri;
	private Integer length;
	private Date update;
	private Date begin;
	private boolean off;
	
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg_uri() {
		return img_uri;
	}
	public void setImg_uri(String img_uri) {
		this.img_uri = img_uri;
	}
	public int getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Date getUpdate() {
		return update;
	}
	public void setUpdate(Date update) {
		this.update = update;
	}
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public boolean getOff() {
		return off;
	}
	public void setOff(boolean off) {
		this.off = off;
	}
	@Override
	public String toString() {
		return "Comic [id=" + id + ", title=" + title + ", img_uri=" + img_uri + ", length=" + length + ", update="
				+ update + ", begin=" + begin + ", off=" + off + "]";
	}
	
}
