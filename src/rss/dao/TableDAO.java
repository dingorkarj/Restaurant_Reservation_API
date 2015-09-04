package rss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rrs.exception.AppException;
import rrs.model.Table;
import rrs.utils.DBUtils;

public class TableDAO {

	public List<Table> getAllTables() throws AppException {
		ArrayList<Table> tables = new ArrayList<Table>();
		Table table = null;
		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("select * from seating_table");
			rs = ps.executeQuery();

			while (rs.next()) {
				table = new Table();
				table.setId(rs.getInt("tid"));
				table.setTableNumber(rs.getInt("tnumber"));
				table.setTableSeating(rs.getInt("tseating"));
				tables.add(table);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(),e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
		return tables;
	}

	public Table getATable(int tid) throws AppException {
		Table table = null;

		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("select * from seating_table where tid = ?");
			ps.setInt(1, tid);
			rs = ps.executeQuery();

			if (rs.next()) {
				table = new Table();
				table.setId(tid);
				table.setTableNumber(rs.getInt("tnumber"));
				table.setTableSeating(rs.getInt("tseating"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(),e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
		return table;
	}

}
