package se.bth.softhouse.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Preconditions;

import se.bth.softhouse.db.UserDAO;
import se.bth.softhouse.entities.Users;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("users")
public class UserResource {
	private UserDAO userDao;

	public UserResource(UserDAO userDao) {
		Preconditions.checkNotNull(userDao);
		this.userDao = userDao;
	}

	@GET
	@Path("/all/")
	public Users getBy() {
		return userDao.getBy();
	}

	@POST
	@Path("/name/{uname}")
	public Users getBy(@PathParam("uname") String username) {
		return userDao.getBy(username);
	}

	@GET
	@Path("/{id}")
	public Users getBy(@PathParam("id") int id) {
		return userDao.getBy(id);
	}

	@POST
	@Timed
	public void insertUsers(Users users) {
		userDao.insertUser(users);
	}

}
