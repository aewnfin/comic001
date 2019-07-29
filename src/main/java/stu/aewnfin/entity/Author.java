package stu.aewnfin.entity;

public class Author {
	private Integer id;
	private String IDcard;
	private String firstName;
	private String secondName;
	private Boolean verify;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIDcard() {
		return IDcard;
	}
	public void setIDcard(String iDcard) {
		IDcard = iDcard;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public Boolean getVerify() {
		return verify;
	}
	public void setVerify(Boolean verify) {
		this.verify = verify;
	}
	@Override
	public String toString() {
		return "Author [id=" + id + ", IDcard=" + IDcard + ", firstName=" + firstName + ", secondName=" + secondName
				+ ", verify=" + verify + "]";
	}
	
	
}
