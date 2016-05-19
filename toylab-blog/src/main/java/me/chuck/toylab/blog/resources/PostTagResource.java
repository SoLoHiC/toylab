package me.chuck.toylab.blog.resources;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.sun.jersey.api.NotFoundException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import lombok.extern.slf4j.Slf4j;
import me.chuck.toylab.blog.core.PostTag;
import me.chuck.toylab.blog.store.PostTagDAO;
import me.chuck.toylab.blog.store.impl.mysql.tag.PostTagDO;

/**
 * @author chuck
 * @since 5/19/16
 */
@Slf4j
@Path("/postTag")
@Produces(MediaType.APPLICATION_JSON)
public class PostTagResource {

  private PostTagDAO postTagDAO;

  @Inject
  public PostTagResource(@Named("postTagDAO") PostTagDAO postTagDAO) {
    this.postTagDAO = postTagDAO;
  }

  // TODO: postTag resource methods

  @PUT
  @UnitOfWork
  public Response createPostTag(PostTag postTag) {
    return null;
  }

  @GET
  @Path("/{postTagId}")
  @UnitOfWork
  public PostTagDO getPostTag(@PathParam("postTagId")IntParam postTagId) {
    return postTagDAO.findById(postTagId.get()).orElseThrow(
        () -> new NotFoundException("no such tag for this post")
    );
  }

  @DELETE
  @Path("/{postTagId}")
  @UnitOfWork
  public Response deletePostTag(@PathParam("postTagId")IntParam postTagId) {
    return null;
  }
}
