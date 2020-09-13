package com.rsi.bean;

import java.sql.Date;

public class Task {
	
	private int id;
	private String taskname;
	private String taskd;
	private String tasks;
	private int uid;
	private Date tasKdate;
	public Date getTasKdate() {
		return tasKdate;
	}
	public void setTasKdate(Date tasKdate) {
		this.tasKdate = tasKdate;
	}
	
	public Task(int id, String taskname, String taskd, String tasks, int uid, Date tasKdate) {
		super();
		this.id = id;
		this.taskname = taskname;
		this.taskd = taskd;
		this.tasks = tasks;
		this.uid = uid;
		this.tasKdate = tasKdate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getTaskd() {
		return taskd;
	}
	public void setTaskd(String taskd) {
		this.taskd = taskd;
	}
	public String getTasks() {
		return tasks;
	}
	public void setTasks(String tasks) {
		this.tasks = tasks;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	
	

}
