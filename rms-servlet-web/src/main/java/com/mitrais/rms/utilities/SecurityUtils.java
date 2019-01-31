package com.mitrais.rms.utilities;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.mitrais.rms.dao.RoleDao;
import com.mitrais.rms.dao.RoleMenuDao;
import com.mitrais.rms.dao.impl.RoleDaoImpl;
import com.mitrais.rms.dao.impl.RoleMenuDaoImpl;
import com.mitrais.rms.model.Role;
import com.mitrais.rms.model.RoleMenu;

public class SecurityUtils {

	public static boolean isSecurityPage(HttpServletRequest request) {

		String urlPattern = UrlPatternUtils.getUrlPattern(request);
		
		RoleDao roleDao = RoleDaoImpl.getInstance();
		RoleMenuDao roleMenuDao = RoleMenuDaoImpl.getInstance();
		
		List<String> roleStrings = roleDao.findAllRoleID();
		for (String roleStr : roleStrings) {
			Optional<Role> optional = roleMenuDao.findRoleMenuByRoleId(roleStr);
			if (!optional.isPresent()) {
				continue;
			}
			List<String> urlPatterns = optional.get().getUrlPatterns();
			if (urlPatterns.contains(urlPattern)) {
				return true;
			}
		}
		
		
		return false;
	}
	
	public static boolean hasPermission(HttpServletRequest request){
		String urlPattern = UrlPatternUtils.getUrlPattern(request);
		
		RoleDao roleDao = RoleDaoImpl.getInstance();
		List<String> roleStrings = roleDao.findAllRoleID();
		for (String roleStr : roleStrings) {
			
		}
		
		return false;
	}

}
