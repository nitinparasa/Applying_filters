package se.bth.softhouse.resources;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import se.bth.softhouse.entities.Filter;
import se.bth.softhouse.process.FilterProcess;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("filter")
public class FilterResource {

	final private FilterProcess process;

	public FilterResource(FilterProcess process) {
		this.process = process;
	}
	
	@GET
	public List<Filter> getAll() {
		return process.getAll();
	}
	
	@GET
	@Path("/{id}")
	public Filter getBy(@PathParam("id") int id) {
		return process.getBy(id);
	}
	
	@POST
	public void createFilter(Filter filter) {
		process.create(filter);
	}
}
