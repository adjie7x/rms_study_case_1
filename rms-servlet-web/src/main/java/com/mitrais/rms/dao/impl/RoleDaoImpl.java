package com.mitrais.rms.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mitrais.rms.dao.DataSourceFactory;
import com.mitrais.rms.dao.RoleDao;
import com.mitrais.rms.model.Role;

public class RoleDaoImpl implements RoleDao{

	@Override
	public Optional<Role> find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findAll() {
		List<Role> result = new ArrayList<>();
		try (Connection connection = DataSourceFactory.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM role");
			while (rs.next()) {
				Role role = new Role(rs.getString("role_id"), rs.getString("role_name"));
				result.add(role);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean save(Role o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Role o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Role o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> findAllRoleID() {
		List<String> result = new ArrayList<>();
		try (Connection connection = DataSourceFactory.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM role");
			while (rs.next()) {
				result.add(rs.getString("role_id"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	private static class SingletonHelper {
		private static final RoleDaoImpl INSTANCE = new RoleDaoImpl();
	}

	public static RoleDao getInstance() {
		return SingletonHelper.INSTANCE;
	}

}
