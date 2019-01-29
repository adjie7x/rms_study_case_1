package com.mitrais.rms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mitrais.rms.dao.CustomerDao;
import com.mitrais.rms.dao.DataSourceFactory;
import com.mitrais.rms.model.Customer;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public Optional<Customer> find(Long id) {
		try (Connection connection = DataSourceFactory.getConnection()) {
			PreparedStatement stmt = connection
					.prepareStatement("SELECT * FROM customer WHERE id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Customer customer = new Customer(rs.getLong("id"), rs.getString("firstname"),
						rs.getString("firstname"), rs.getString("email"),
						rs.getString("mobile"));

				return Optional.of(customer);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return Optional.empty();
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> result = new ArrayList<>();
		try (Connection connection = DataSourceFactory.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM customer");
			while (rs.next()) {
				Customer customer = new Customer(rs.getLong("id"), rs.getString("firstname"),
						rs.getString("firstname"), rs.getString("email"),
						rs.getString("mobile"));
				result.add(customer);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean save(Customer o) {
		try (Connection connection = DataSourceFactory.getConnection())
        {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO customer VALUES (NULL, ?, ?, ?, ?)");
            stmt.setString(1, o.getFirstName());
            stmt.setString(2, o.getLastName());
            stmt.setString(3, o.getEmail());
            stmt.setString(4, o.getMobile());
            int i = stmt.executeUpdate();
            if(i == 1) {
                return true;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
	}

	@Override
	public boolean update(Customer o) {
		try (Connection connection = DataSourceFactory.getConnection())
        {
            PreparedStatement stmt = connection.prepareStatement("UPDATE customer SET firstname=?, lastname=?, email=?, mobile=? WHERE id=?");
            stmt.setString(1, o.getFirstName());
            stmt.setString(2, o.getLastName());
            stmt.setString(3, o.getEmail());
            stmt.setString(4, o.getMobile());
            int i = stmt.executeUpdate();
            if(i == 1) {
                return true;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
	}

	@Override
	public boolean delete(Customer o) {
		try (Connection connection = DataSourceFactory.getConnection())
        {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM customer WHERE id=?");
            stmt.setLong(1, o.getId());
            int i = stmt.executeUpdate();
            if(i == 1) {
                return true;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
	}
	
	private static class SingletonHelper
    {
        private static final CustomerDaoImpl INSTANCE = new CustomerDaoImpl();
    }

    public static CustomerDao getInstance()
    {
        return SingletonHelper.INSTANCE;
    }

}
