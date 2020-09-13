package com.rsi.bean;

public class Login {
	private int id;
	private int isactivate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIsactivate() {
		return isactivate;
	}
	public void setIsactivate(int isactivate) {
		this.isactivate = isactivate;
	}
	public Login(int id, int isactivate) {
		super();
		this.id = id;
		this.isactivate = isactivate;
	}
	

}
