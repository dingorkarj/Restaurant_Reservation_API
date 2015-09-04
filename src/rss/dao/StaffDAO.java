package rss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import rrs.exception.AppException;
import rrs.model.Staff;
import rrs.utils.DBUtils;

public class StaffDAO {

	public Staff login(String username, String password) throws AppException {
		Staff staff = null;
		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("select * from staff where username = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();
			if (rs.next()) {
				staff = new Staff();
				staff.setFname(rs.getString("fname"));
				staff.setLname(rs.getString("lname"));
				staff.setId(rs.getInt("id"));
				staff.setUsername(username);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(),e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
		return staff;
	}

}
