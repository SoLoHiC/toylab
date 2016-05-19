package me.chuck.toylab.blog.resources;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.sun.jersey.api.NotFoundException;

import java.util.List;

import javax.ws.rs.DELETE;
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

  // TODO: tag resource methods

  @PUT
  @UnitOfWork
  public Response createTag(TagDO tagDO) {
    return null;
  }

  @GET
  @Path("/{tagId}")
  @UnitOfWork
  public TagDO getTag(@PathParam("tagId")IntParam tagId) {
    return tagDAO.findById(tagId.get()).orElseThrow(() -> new NotFoundException("no such tag"));
  }

  @DELETE
  @Path("/{tagId}")
  @UnitOfWork
  public Response deleteTag(@PathParam("tagId")IntParam tagId) {
    return null;
  }

  @GET
  @UnitOfWork
  public List<TagDO> getTags(
      @QueryParam("page")IntParam page,
      @QueryParam("pageSize")IntParam pageSize) {
    return tagDAO.findAll(page.get(), pageSize.get());
  }
}
