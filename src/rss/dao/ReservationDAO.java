package rss.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rrs.exception.AppException;
import rrs.model.Reservation;
import rrs.utils.DBUtils;

public class ReservationDAO {

	// This method returns all the reservation done by a customer
	public List<Reservation> getAllReservationForCustomer(int cid) throws AppException {
		ArrayList<Reservation> reservations = new ArrayList<>();

		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("select * from reservation where cid = ?");
			ps.setInt(1, cid);
			rs = ps.executeQuery();

			while (rs.next()) {
				Reservation res = new Reservation();
				res.setCid(cid);
				res.setTid(rs.getInt("tid"));
				res.setRid(rs.getInt("rid"));
				res.setConfirmationCode(rs.getInt("confirmationcode"));
				res.setDate(rs.getDate("date"));
				reservations.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
		return reservations;
	}

	// Returns all the reservations from all the customer
	// Use for ORDER BY can be done in sql query
	public List<Reservation> getAllReservation() throws AppException {
		ArrayList<Reservation> reservations = new ArrayList<>();

		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("select * from reservation");
			rs = ps.executeQuery();

			while (rs.next()) {
				Reservation res = new Reservation();
				res.setCid(rs.getInt("cid"));
				res.setTid(rs.getInt("tid"));
				res.setRid(rs.getInt("rid"));
				res.setConfirmationCode(rs.getInt("confirmationcode"));
				res.setDate(rs.getDate("date"));
				reservations.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
		return reservations;
	}

	public Reservation getAReservationForCustomer(int cid, int rid) throws AppException {
		Reservation res = null;

		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("select * from reservation where rid = ?");
			ps.setInt(1, rid);
			rs = ps.executeQuery();

			if (rs.next()) {
				res = new Reservation();
				res.setCid(cid);
				res.setTid(rs.getInt("tid"));
				res.setRid(rid);
				res.setConfirmationCode(rs.getInt("confirmationcode"));
				res.setDate(rs.getDate("date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}

		return res;

	}

	public Reservation customerCreatesReservation(int cid, Reservation res) throws AppException {
		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"insert into reservation (cid, tid, date, confirmationcode) values (?, ?, ?, ?) ",
					PreparedStatement.RETURN_GENERATED_KEYS);

			// CAN GENERATE RANDOM NUMBER FOR CONFIRMATION CODE
			// FOR SIMPLICITY USED A CONSTANT NUMBER 1

			ps.setInt(4, 1); // CAN PROVIDE RAND
			ps.setInt(1, cid);
			ps.setInt(2, res.getTid());
			ps.setDate(3, (Date) res.getDate());

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				res.setRid(rs.getInt(1));
				res.setCid(cid);
				res.setConfirmationCode(1); // CAN PROVIDE RAND
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}

		return res;

	}

	public Reservation customerUpdateReservation(int cid, int rid, Reservation res) throws AppException {
		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"update reservation set tid = ?, date = ? where rid = ? and cid = ? and confirmationcode = ?");

			ps.setInt(1, res.getTid());
			ps.setInt(3, rid);
			ps.setInt(4, cid);
			ps.setInt(5, res.getConfirmationCode());

			// TODO
			// Date format conversion from java.util.Date to java.sql.Date
			// ps.setDate(2, new java.sql.Date(res.getDate().getTime()));
			// USE THIS TENTATIVELY
			ps.setDate(2, null);

			int check = ps.executeUpdate();

			if (check != 0) {
				res.setCid(cid);
				res.setRid(rid);
			} else {
				// TODO
				// THROW EXCEPTION
				// UPDATE FAILED
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}

		return res;
	}

	public void customerDeletesReservation(int cid, int rid, int ccode) throws AppException {
		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("delete from reservation where rid = ?");
			ps.setInt(1, rid);

			int check = ps.executeUpdate();

			if (check == 0) {
				// TO DO
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

	public Reservation getAReservationForStaff(int rid) throws AppException {
		Reservation res = null;

		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("select * from reservation where rid = ?");
			ps.setInt(1, rid);
			rs = ps.executeQuery();

			if (rs.next()) {
				res = new Reservation();
				res.setCid(rs.getInt("cid"));
				res.setTid(rs.getInt("tid"));
				res.setRid(rid);
				res.setConfirmationCode(rs.getInt("confirmationcode"));
				res.setDate(rs.getDate("date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}
		return res;
	}

	public Reservation staffCreatesReservation(Reservation res) throws AppException {

		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"insert into reservation (cid, tid, date, confirmationcode) values (?, ?, ?, ?) ",
					PreparedStatement.RETURN_GENERATED_KEYS);

			// CAN GENERATE RANDOM NUMBER FOR CONFIRMATION CODE
			// FOR SIMPLICITY USED A CONSTANT NUMBER 1

			ps.setInt(4, 1); // CAN PROVIDE RAND
			ps.setInt(1, res.getCid());
			ps.setInt(2, res.getTid());
			ps.setDate(3, (Date) res.getDate());

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				res.setRid(rs.getInt(1));
				res.setConfirmationCode(1); // CAN PROVIDE RAND
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}

		return res;
	}

	public Reservation staffUpdatesReservation(int rid, Reservation res) throws AppException {

		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"update reservation set tid = ?, date = ?, cid = ?, confirmationcode = ? where rid = ?");

			ps.setInt(1, res.getTid());
			ps.setInt(3, res.getCid());
			ps.setInt(4, res.getConfirmationCode());
			ps.setInt(5, rid);

			// TODO
			// Date format conversion from java.util.Date to java.sql.Date
			// ps.setDate(2, new java.sql.Date(res.getDate().getTime()));
			// USE THIS TENTATIVELY
			ps.setDate(2, null);

			int check = ps.executeUpdate();

			if (check != 0) {
				res.setRid(rid);
			} else {
				// TODO
				// THROW EXCEPTION
				// UPDATE FAILED
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		} finally {
			DBUtils.closeConnection(conn, ps, rs);
		}

		return res;
	}

	public void staffDeletesReservation(int rid) throws AppException {
		Connection conn = DBUtils.startConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("delete from reservation where rid = ?", PreparedStatement.SUCCESS_NO_INFO);
			ps.setInt(1, rid);

			ps.executeUpdate();
			int check = ps.getUpdateCount();
			if (check == 0) {
				// TO DO
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

}
