package com.mitrais.rms.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private Long id;
	private String userName;
	private String password;
	
	private List<String> roles = new ArrayList<>();
	
	public User() {
		
	}	
	
	public User(Long id, String userName, String password) {
		this.id = id;
		this.userName = userName;
		this.password = password;
	}
	
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public User(Long id, String userName, String password, String... roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		
		this.roles = new ArrayList<String>();
	      if (roles != null) {
	         for (String r : roles) {
	            this.roles.add(r);
	         }
	      }
	}
	
	public User(Long id, String userName, String password, List<String> roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		
		this.roles = roles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
