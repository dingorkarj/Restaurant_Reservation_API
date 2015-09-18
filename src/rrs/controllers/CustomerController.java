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
import rrs.model.Customer;
import rrs.model.Reservation;
import rss.dao.CustomerDAO;
import rss.dao.ReservationDAO;

@Path("/customers")
@Api(tags = { "customers" })
public class CustomerController {

	// GET ALL CUSTOMERS
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find All Customers", notes = "Finds all employees in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Customer> getAllCustomers() {
		List<Customer> customers = null;

		CustomerDAO custDAO = new CustomerDAO();
		try {
			customers = custDAO.getAllCustomers();
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return customers;
	}

	// GET A CUSTOMER BY ID
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find A Customer", notes = "Finds a customer with its id in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Customer getCustomer(@PathParam("id") int cid) {
		Customer cust = null;

		CustomerDAO custDAO = new CustomerDAO();
		try {
			cust = custDAO.getById(cid);
			if (cust == null) {
				throw new WebApplicationException(Status.NOT_FOUND);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return cust;
	}

	// CREATE A CUSTOMER RECORD IN DB
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create Customer", notes = "Creates a customer record in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Customer createCustomer(Customer cust) {
		CustomerDAO custDAO = new CustomerDAO();
		try {
			cust = custDAO.create(cust);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return cust;
	}

	// UPDATE AN EXISTING CUSTOMER RECORD IN DB
	@PUT
	@Path("/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Edit Customer", notes = "Update existing customer record in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Customer updateCustomer(@PathParam("username") String username, Customer cust) {
		CustomerDAO custDAO = new CustomerDAO();
		try {
			cust = custDAO.update(username, cust);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return cust;
	}

	// DELETE A CUSTOMER FROM DB BY USERNAME
	@DELETE
	@Path("/{cid}")
	@ApiOperation(value = "Delete Customer", notes = "Delete a customer record in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void delete(@PathParam("cid") int cid) {
		CustomerDAO custDAO = new CustomerDAO();
		try {
			custDAO.delete(cid);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}

	// GET LIST OF ALL THE RESERVATIONS FOR A CUSTOMER
	@GET
	@Path("/{cid}/reservations")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find All Reservations", notes = "Finds all table reservations in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Reservation> getAllReservation(@PathParam("cid") int cid) {
		List<Reservation> reservations = null;

		ReservationDAO rdao = new ReservationDAO();
		try {
			reservations = rdao.getAllReservationForCustomer(cid);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}

		return reservations;
	}

	// GET A RESERVATION FOR A PARTICULAR CUSTOMER BY RESERVATION AND CUSTOMER
	// ID
	@GET
	@Path("/{cid}/reservations/{rid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find a Reservations", notes = "Finds a table reservation for a customer in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Reservation getAReservation(@PathParam("cid") int cid, @PathParam("rid") int rid) {
		Reservation res = null;

		ReservationDAO rdao = new ReservationDAO();
		try {
			res = rdao.getAReservationForCustomer(cid, rid);
			if (res == null) {
				throw new WebApplicationException(Status.NO_CONTENT);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}

		return res;
	}

	// CREATE A RESERVATION RECORD IN DB FOR A SPECIFIC CUSTOMER
	@POST
	@Path("/{cid}/reservations")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create Reservation", notes = "Creates a table reservation record.<br/> Confirmation code will be auto generated; any input from user from user will be over-ridden.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 409, message = "Conflict, record already exists"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Reservation createReservation(@PathParam("cid") int cid, Reservation res) {
		Reservation reservation = null;
		ReservationDAO rdao = new ReservationDAO();
		try {

			//TODO FIX rdao.checkIfReservationExists(), IT GIVING OUT ALWAYS TRUE!
			if (
					//rdao.checkIfReservationExists(cid, res) == true
					true) {
				reservation = rdao.customerCreatesReservation(cid, res);
			} else {
				throw new WebApplicationException(Status.CONFLICT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}

		return reservation;
	}

	// UPDATE AN EXISTING RESERVATION RECORD
	@PUT
	@Path("/{cid}/reservations/{rid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Update table reservation", notes = "Update table reservation record for a customer in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Reservation updateReservation(@PathParam("cid") int cid, @PathParam("rid") int rid, Reservation res) {
		ReservationDAO rdao = new ReservationDAO();
		try {
			res = rdao.customerUpdateReservation(cid, rid, res);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return res;
	}

	@DELETE
	@Path("/{cid}/reservations/{rid}/confirmation-code/{ccode}")
	@ApiOperation(value = "Delete reservation", notes = "Delete a reservation record for a customer using generated confirmation code")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public void deleteReservation(@PathParam("cid") int cid, @PathParam("rid") int rid,
			@PathParam("confirmation-code") int ccode) {
		ReservationDAO rdao = new ReservationDAO();
		try {
			rdao.customerDeletesReservation(cid, rid, ccode);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}

}
