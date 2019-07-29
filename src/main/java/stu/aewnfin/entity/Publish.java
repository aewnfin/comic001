package stu.aewnfin.entity;


public class Publish {
	private Integer author_id;
	private String penName;
	private Integer comic_id;
	private Integer reprice;
	public Integer getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(Integer author_id) {
		this.author_id = author_id;
	}
	public String getPenName() {
		return penName;
	}
	public void setPenName(String penName) {
		this.penName = penName;
	}
	public Integer getComic_id() {
		return comic_id;
	}
	public void setComic_id(Integer comic_id) {
		this.comic_id = comic_id;
	}
	public Integer getReprice() {
		return reprice;
	}
	public void setReprice(Integer reprice) {
		this.reprice = reprice;
	}
	@Override
	public String toString() {
		return "Publish [author_id=" + author_id + ", penName=" + penName + ", comic_id=" + comic_id + ", reprice="
				+ reprice + "]";
	}
	
	
}
