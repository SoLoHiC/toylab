package me.chuck.toylab.blog.core.repos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import me.chuck.toylab.blog.core.PostTag;
import me.chuck.toylab.blog.store.PostTagDAO;

/**
 * @author chuck
 * @since 5/28/16
 */
public class PostTagRepo {

  @Inject
  private ObjectMapper mapper;

  private PostTagDAO postTagDAO;

  @Inject
  public PostTagRepo(@Named("postTagDAO")PostTagDAO postTagDAO) {
    this.postTagDAO = postTagDAO;
  }

  // TODO: rethink about post-tag relations and how to do about it
  public PostTag createPostTag(PostTag postTag) {
    return null;
  }
}
