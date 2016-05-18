package me.chuck.toylab.blog.resources;

import com.google.inject.Inject;
import com.sun.jersey.api.NotFoundException;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import me.chuck.toylab.blog.core.Post;
import me.chuck.toylab.blog.core.repos.PostRepo;

/**
 * @author chuck
 * @since 4/9/16
 */
@Path("/post")
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

  private PostRepo postRepo;

  @Inject
  public PostResource(PostRepo postRepo) {
    this.postRepo = postRepo;
  }

  @PUT
  @UnitOfWork
  public Response createPost(Post post) {
    Optional<Post> created = postRepo.create(post);
    if (created.isPresent()) {
      return Response.status(Response.Status.CREATED).entity(created.get()).build();
    } else {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  @POST
  @UnitOfWork
  public Response updatePost(Post post) {
    Optional<Post> updated = postRepo.update(post);
    if (updated.isPresent()) {
      return Response.status(Response.Status.ACCEPTED).entity(updated.get()).build();
    } else {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GET
  @Path("/{postId}")
  @UnitOfWork
  public Post getPost(@PathParam("postId")IntParam postId) {
    return findSafely(postId.get());
  }

  @GET
  @UnitOfWork
  public List<Post> paginatePost(
      @QueryParam("page") IntParam page, @QueryParam("pageSize") IntParam pageSize) {
    return postRepo.findAll(page.get(), pageSize.get());
  }

  private Post findSafely(int postId) {
    return postRepo.findById(postId).orElseThrow(() -> new NotFoundException("no such post!"));
  }
}
