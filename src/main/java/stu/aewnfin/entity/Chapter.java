package stu.aewnfin.entity;

public class Chapter {
	//所属漫画_id
	private Integer id;
	//本章节序号
	private Integer part;
	//本章节标题
	private String title;
	//本章价格
	private Integer cost;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPart() {
		return part;
	}
	public void setPart(Integer part) {
		this.part = part;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "Chapter [id=" + id + ", part=" + part + ", title=" + title + ", cost=" + cost + "]";
	}

	
	
}
