package me.chuck.toylab.blog.resources;

import com.sun.jersey.api.NotFoundException;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import me.chuck.toylab.blog.db.post.PostDAO;
import me.chuck.toylab.blog.db.post.PostDO;

/**
 * @author chuck
 * @since 4/9/16
 */
@Path("/post")
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

  private final PostDAO postDAO;

  public PostResource(PostDAO postDAO) {
    this.postDAO = postDAO;
  }

  @PUT
  @UnitOfWork
  public Response createPost(PostDO postDO) {
    Optional<PostDO> created = postDAO.create(postDO);
    if (created.isPresent()) {
      return Response.status(Response.Status.CREATED).entity(created.get()).build();
    } else {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GET
  @Path("/{postId}")
  @UnitOfWork
  public PostDO getPost(@PathParam("postId")IntParam postId) {
    return findSafely(postId.get());
  }

  @GET
  @UnitOfWork
  public List<PostDO> paginatePost(
      @QueryParam("page") IntParam page, @QueryParam("pageSize") IntParam pageSize) {
    return postDAO.findAll(page.get(), pageSize.get());
  }

  private PostDO findSafely(int postId) {
    return postDAO.findById(postId).orElseThrow(() -> new NotFoundException("no such post!"));
  }
}
