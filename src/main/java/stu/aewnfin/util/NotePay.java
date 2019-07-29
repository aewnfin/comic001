package stu.aewnfin.util;

public class NotePay {
	//是否支付成功
	private Boolean success;
	//赠币花费
	private int reprice;
	//钱币花费
	private int price;
	

	public int getReprice() {
		return reprice;
	}
	public void setReprice(int reprice) {
		this.reprice = reprice;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "NotePay [success=" + success + ", reprice=" + reprice + ", price=" + price + "]";
	}
}
