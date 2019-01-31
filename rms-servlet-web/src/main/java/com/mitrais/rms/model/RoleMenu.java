package com.mitrais.rms.model;

public class RoleMenu {

	private Role role;
	private Menu menu;

	public RoleMenu() {
	}

	public RoleMenu(Role role, Menu menu) {
		super();
		this.role = role;
		this.menu = menu;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}
