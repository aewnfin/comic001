package stu.aewnfin.entity;

public class Page {
	//所属漫画_id
	private Integer id;
	//所属章节
	private Integer part;
	//页码
	private Integer num;
	//内容地址
	private String content;
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Page [id=" + id + ", part=" + part + ", num=" + num + ", content=" + content + "]";
	}
	
	
}
