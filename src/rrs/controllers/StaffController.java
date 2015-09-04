package rrs.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import rrs.exception.AppException;
import rrs.model.Reservation;
import rss.dao.ReservationDAO;

@Path("/staff")
@Api(tags = { "staff" })
public class StaffController {

	@GET
	@Path("/reservations")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find All Reservation", notes = "Find all reservations in the staff")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Reservation> getAllReservation() {
		List<Reservation> reservations = null;

		ReservationDAO rdao = new ReservationDAO();
		try {
			reservations = rdao.getAllReservation();
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}

		return reservations;
	}

	@GET
	@Path("/reservations/{rid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find A Reservation", notes = "Find a reservation by id in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Reservation getAReservation(@PathParam("rid") int rid) {
		Reservation res = null;

		ReservationDAO rdao = new ReservationDAO();
		try {
			res = rdao.getAReservationForStaff(rid);
			if (res == null) {
				throw new WebApplicationException(Status.NO_CONTENT);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}

		return res;

	}

	@POST
	@Path("/reservations")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a reservation", notes = "Creates a reservation record in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Reservation createReservation(Reservation res) {
		ReservationDAO rdao = new ReservationDAO();
		try {
			res = rdao.staffCreatesReservation(res);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return res;
	}

	@PUT
	@Path("/reservations/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update a reservation", notes = "Update a reservation record in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Reservation updateReservation(@PathParam("id") int rid, Reservation res) {
		ReservationDAO rdao = new ReservationDAO();
		try {
			res = rdao.staffUpdatesReservation(rid, res);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return res;
	}

	@DELETE
	@Path("/reservations/{id}")
	@ApiOperation(value = "Delete a reservation", notes = "Delete a reservation record in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void deleteReservation(@PathParam("id") int rid) {
		ReservationDAO rdao = new ReservationDAO();
		try {
			rdao.staffDeletesReservation(rid);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}

	}
}
