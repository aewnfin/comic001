package stu.aewnfin.entity;


public class Reader {
	private Integer id;
	private String email;
	private Integer price;
	private Integer freeprice;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getFreeprice() {
		return freeprice;
	}
	public void setFreeprice(Integer freeprice) {
		this.freeprice = freeprice;
	}
	@Override
	public String toString() {
		return "Reader [id=" + id + ", email=" + email + ", price=" + price + ", freeprice=" + freeprice + "]";
	}
	
	
}
