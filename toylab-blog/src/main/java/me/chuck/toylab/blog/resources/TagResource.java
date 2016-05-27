package me.chuck.toylab.blog.resources;

import com.google.inject.Inject;
import com.google.inject.name.Named;
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
import lombok.extern.slf4j.Slf4j;
import me.chuck.toylab.blog.store.TagDAO;
import me.chuck.toylab.blog.store.impl.mysql.tag.TagDO;

/**
 * @author chuck
 * @since 5/19/16
 */
@Slf4j
@Path("/tag")
@Produces(MediaType.APPLICATION_JSON)
public class TagResource {

  private TagDAO tagDAO;

  @Inject
  public TagResource(@Named("tagDAO") TagDAO tagDAO) {
    this.tagDAO = tagDAO;
  }

  @PUT
  @UnitOfWork
  public Response createTag(TagDO tagDO) {
    Optional<TagDO> created = tagDAO.create(tagDO);
    if (created.isPresent()) {
      return Response.status(Response.Status.OK).entity(created.get()).build();
    } else {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GET
  @Path("/{tagId}")
  @UnitOfWork
  public TagDO getTag(@PathParam("tagId")IntParam tagId) {
    return tagDAO.findById(tagId.get()).orElseThrow(() -> new NotFoundException("no such tag"));
  }

  // TODO: tag resource delete method
//  @DELETE
//  @Path("/{tagId}")
//  @UnitOfWork
//  public Response deleteTag(@PathParam("tagId")IntParam tagId) {
//    return Response.status(Response.Status.NO_CONTENT)
//  }

  @GET
  @UnitOfWork
  public List<TagDO> getTags(
      @QueryParam("page")IntParam page,
      @QueryParam("pageSize")IntParam pageSize) {
    return tagDAO.findAll(page.get(), pageSize.get());
  }
}
