package stu.aewnfin.entity;

public class Admin {
	private Integer id;
	private Integer employeeID;
	private String trueName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", employeeID=" + employeeID + ", trueName=" + trueName + "]";
	}

	
}
