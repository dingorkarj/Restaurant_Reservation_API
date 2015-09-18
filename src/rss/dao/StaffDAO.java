package rss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import rrs.exception.AppException;
import rrs.model.Staff;
import rrs.utils.DBUtils;

public class StaffDAO {

	public Staff getAStaff(int sid) throws AppException {
		Staff staff = null;
		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("select * from staff where sid = " + sid);

			rs = ps.executeQuery();
			if (rs.next()) {
				staff = new Staff();
				staff.setFname(rs.getString("fname"));
				staff.setLname(rs.getString("lname"));
				staff.setId(rs.getInt("sid"));
				staff.setUsername(rs.getString("username"));
				staff.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
		return staff;
	}

}
