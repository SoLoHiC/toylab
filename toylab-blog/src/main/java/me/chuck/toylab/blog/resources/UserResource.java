package me.chuck.toylab.blog.resources;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.sun.jersey.api.NotFoundException;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import me.chuck.toylab.blog.store.UserDAO;
import me.chuck.toylab.blog.store.impl.mysql.user.UserDO;

/**
 * @author chuck
 * @since 4/12/16
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

  private final UserDAO userDAO;

  @Inject
  public UserResource(@Named("userDAO")UserDAO dao) {
    this.userDAO = dao;
  }

  @PUT
  @UnitOfWork
  public Response createUser(UserDO user) {
    Optional<UserDO> created = userDAO.create(user);
    if (created.isPresent()) {
      return Response.status(Response.Status.CREATED).entity(created.get()).build();
    } else {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  @POST
  @UnitOfWork
  public Response updateUser(UserDO user) {
    Optional<UserDO> updated = userDAO.update(user);
    if (updated.isPresent()) {
      return Response.status(Response.Status.ACCEPTED).entity(updated.get()).build();
    } else {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GET
  @Path("/{userId}")
  @UnitOfWork
  public UserDO getUser(@PathParam("userId")IntParam userId) {
    return userDAO.findById(userId.get()).orElseThrow(() -> new NotFoundException("no such user!"));
  }
}
