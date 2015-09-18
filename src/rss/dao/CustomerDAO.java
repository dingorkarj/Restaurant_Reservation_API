package rss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rrs.exception.AppException;
import rrs.model.Customer;
import rrs.utils.DBUtils;

public class CustomerDAO {

	public List<Customer> getAllCustomers() throws AppException {
		ArrayList<Customer> customers = new ArrayList<>();

		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		// Statement st = null;
		ResultSet rs = null;

		try {
			// st = conn.createStatement();
			ps = conn.prepareStatement("select * from customer");
			rs = ps.executeQuery();

			while (rs.next()) {
				Customer cust = new Customer();
				cust.setId(rs.getInt("cid"));
				cust.setFname(rs.getString("fname"));
				cust.setLname(rs.getString("lname"));
				cust.setAddress(rs.getString("address"));
				cust.setPhone(rs.getString("phone"));
				cust.setEmail(rs.getString("email"));
				customers.add(cust);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
		return customers;
	}

	public Customer getById(int id) throws AppException {
		Customer cust = null;

		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		// Statement statement = null;

		try {
			/*
			 * statement = conn.createStatement(); rs=
			 * statement.executeQuery("select * from customer where cid = "+
			 * id);
			 */
			ps = conn.prepareStatement("select * from customer where cid = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				cust = new Customer();
				cust.setId(rs.getInt("cid"));
				cust.setFname(rs.getString("fname"));
				cust.setLname(rs.getString("lname"));
				cust.setAddress(rs.getString("address"));
				cust.setPhone(rs.getString("phone"));
				cust.setEmail(rs.getString("email"));
				cust.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
		return cust;
	}

	public Customer update(String username, Customer cust) throws AppException {
		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"update customer set fname = ?, lname = ?, address = ?, phone = ?, email = ? where username = ?");
			ps.setString(1, cust.getFname());
			ps.setString(2, cust.getLname());
			ps.setString(3, cust.getAddress());
			ps.setString(4, cust.getPhone());
			ps.setString(5, cust.getEmail());
			ps.setString(6, username);

			int check = ps.executeUpdate();

			if (check == 0) {
				// TO DO
				// THROW EXCEPTION
				// UPDATE UNSUCCESSFUL
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
		return cust;
	}

	// TO DO:
	// CHECK IF DUPLICATE USERNAME FOR CREATING A CUSTOMER PROFILE
	// ID IS NOT SET
	public Customer create(Customer cust) throws AppException {
		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"insert into customer (fname, lname, address, phone, email, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)",
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, cust.getFname());
			ps.setString(2, cust.getLname());
			ps.setString(3, cust.getAddress());
			ps.setString(4, cust.getPhone());
			ps.setString(5, cust.getEmail());
			ps.setString(6, cust.getUsername());
			ps.setString(7, cust.getPassword());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				cust.setId(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
		return cust;
	}

	public void delete(int cid) throws AppException {
		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("delete from customer where cid = ?");
			ps.setInt(1, cid);
			int check = ps.executeUpdate();
			if (check == 0) {
				// TODO
				// THROW EXCEPTION
				// DELETE UNSUCCESSFUL
				System.out.println("Delete operation failed!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
	}

	public Customer login(String username, String password) throws AppException {
		Customer cust = null;
		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("select * from staff where username = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();
			if (rs.next()) {
				cust = new Customer();
				cust.setFname(rs.getString("fname"));
				cust.setLname(rs.getString("lname"));
				cust.setId(rs.getInt("id"));
				cust.setUsername(username);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
		return cust;
	}

}