package com.mitrais.rms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mitrais.rms.dao.DataSourceFactory;
import com.mitrais.rms.dao.RoleMenuDao;
import com.mitrais.rms.model.Menu;
import com.mitrais.rms.model.Role;
import com.mitrais.rms.model.RoleMenu;

public class RoleMenuDaoImpl implements RoleMenuDao {

	private static class SingletonHelper {
		private static final RoleMenuDaoImpl INSTANCE = new RoleMenuDaoImpl();
	}

	public static RoleMenuDao getInstance() {
		return SingletonHelper.INSTANCE;
	}

	@Override
	public Optional<RoleMenu> find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleMenu> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(RoleMenu o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(RoleMenu o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(RoleMenu o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Role> findRoleMenuByRoleId(String roleId) {

		String sql = "SELECT " + "rm.role_id, " + "r.role_name, "
				+ "rm.menu_id, " + "m.title, " + "m.link, " + "m.desc "
				+ "FROM role_menu rm "
				+ "LEFT JOIN role r ON rm.role_id = r.role_id "
				+ "LEFT JOIN menu m ON m.id = rm.menu_id "
				+ "WHERE rm.role_id = ? ";
		try (Connection connection = DataSourceFactory.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, roleId);
			ResultSet rs = stmt.executeQuery();
			int idx = 0;
			Role result = null;
			while (rs.next()) {
				if (idx == 0) {
					result = new Role(rs.getString("role_id"),
							rs.getString("role_name"));
				}

				Menu menu = new Menu(rs.getLong("menu_id"),
						rs.getString("title"), rs.getString("link"),
						rs.getString("desc"));

				result.getMenus().add(menu);
				result.getUrlPatterns().add(rs.getString("link"));

				idx++;
			}

			if (idx > 0) {

				return Optional.of(result);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return Optional.empty();
	}

}
