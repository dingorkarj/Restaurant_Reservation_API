package rrs.controllers;

import java.util.List;

import javax.ws.rs.GET;
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
import rrs.model.Table;
import rss.dao.TableDAO;

@Path("/tables")
@Api(tags = { "tables" })
public class TableController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find All Tables", notes = "Finds all dining tables in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Table> getAllTables() {
		List<Table> tables = null;
		TableDAO tdao = new TableDAO();
		try {
			tables = tdao.getAllTables();
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return tables;
	}

	@GET
	@Path("/{tid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find a table", notes = "Find a dining table by id in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	public Table getATable(@PathParam("tid") int tid) {
		Table table = null;
		TableDAO tdao = new TableDAO();
		try {
			table = tdao.getATable(tid);
			if (table == null) {
				throw new WebApplicationException(Status.NO_CONTENT);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return table;
	}
}
