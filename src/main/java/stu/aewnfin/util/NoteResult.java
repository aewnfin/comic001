package stu.aewnfin.util;

import java.io.Serializable;

public class NoteResult<T> implements Serializable{
	private static final long serialVersionUID = 4008358442045771468L;
	
	private int status;
	private String msg;
	private T data;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int ststus) {
		this.status = ststus;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "NoteResult [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}

}
