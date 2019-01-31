package com.mitrais.rms.model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	
	private Long id;
	private String title;
	private String link;
	private String desc;
	private List<Role> roles = new ArrayList<Role>();	
	
	public Menu(Long id, String title, String link, String desc) {
		super();
		this.id = id;
		this.title = title;
		this.link = link;
		this.desc = desc;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	

}
