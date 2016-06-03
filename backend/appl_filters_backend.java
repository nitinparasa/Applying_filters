package se.bth.softhouse.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.example.helloworld.core.Users;

import se.bth.softhouse.db.UserDAO;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/users")
public class UserResource {

	public UserDAO userDAO;

	public UserResource(UserDAO dao) {
		userDAO = dao;
	}

	@POST
	@Timed
	public void insertUsers(Users users) {
		userDAO.insertUser(users);
	}
}
