package com.mitrais.rms.dao;

import java.util.Optional;

import com.mitrais.rms.model.Role;
import com.mitrais.rms.model.RoleMenu;

public interface RoleMenuDao extends Dao<RoleMenu, String>{
	
	Optional<Role> findRoleMenuByRoleId(String roleId);

}
