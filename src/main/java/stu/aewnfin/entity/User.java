package stu.aewnfin.entity;

import java.sql.Timestamp;

public class User {
	private Integer id;
	private String name;
	private String password;
	private Timestamp date;
	private int type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", date=" + date + ", type=" + type
				+ "]";
	}

	
}
