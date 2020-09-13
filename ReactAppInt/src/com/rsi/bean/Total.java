package com.rsi.bean;

public class Total {
	
	private int activeuser;
	private int newuser;
	private int totaltask;
	public int getActiveuser() {
		return activeuser;
	}
	public void setActiveuser(int activeuser) {
		this.activeuser = activeuser;
	}
	public int getNewuser() {
		return newuser;
	}
	public void setNewuser(int newuser) {
		this.newuser = newuser;
	}
	public int getTotaltask() {
		return totaltask;
	}
	public void setTotaltask(int totaltask) {
		this.totaltask = totaltask;
	}
	public Total(int activeuser, int newuser, int totaltask) {
		super();
		this.activeuser = activeuser;
		this.newuser = newuser;
		this.totaltask = totaltask;
	}
	
}
