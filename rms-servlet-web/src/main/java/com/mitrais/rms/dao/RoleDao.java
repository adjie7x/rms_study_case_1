package com.mitrais.rms.dao;

import java.util.List;

import com.mitrais.rms.model.Role;

public interface RoleDao extends Dao<Role, String> {
	
	List<String> findAllRoleID();

}
