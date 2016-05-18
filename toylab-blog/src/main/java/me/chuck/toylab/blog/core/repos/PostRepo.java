package me.chuck.toylab.blog.core.repos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import me.chuck.toylab.blog.core.Post;
import me.chuck.toylab.blog.core.PostTag;
import me.chuck.toylab.blog.store.PostDAO;
import me.chuck.toylab.blog.store.PostTagDAO;
import me.chuck.toylab.blog.store.impl.mysql.post.PostDO;

/**
 * @author chuck
 * @since 5/19/16
 */
public class PostRepo {

  @Inject
  private ObjectMapper mapper;

  private PostDAO postDAO;

  private PostTagDAO postTagDAO;

  @Inject
  public PostRepo(@Named("postDAO") PostDAO postDAO, @Named("postTagDAO") PostTagDAO postTagDAO) {
    this.postDAO = postDAO;
    this.postTagDAO = postTagDAO;
  }

  public Optional<Post> create(Post post) {
    // TODO: create post, should check tag existence when adding tags for post
    return null;
  }

  public Optional<Post> update(Post post) {
    // TODO: update post, should check tag existence when adding tags for post
    return null;
  }

  public Optional<Post> findById(int id) {
    Optional<PostDO> postDO = postDAO.findById(id);
    if (!postDO.isPresent()) {
      return Optional.empty();
    } else {
      Post post = mapper.convertValue(postDO.get(), Post.class);
      post.setTags(
          postTagDAO.findByPostId(post.getId())
              .stream()
              .map(postTagDO -> mapper.convertValue(postTagDO, PostTag.class))
              .collect(Collectors.toList())
      );
      return Optional.of(post);
    }
  }

  public List<Post> findAll(int page, int size) {
    List<Post> posts = postDAO.findAll(page, size)
        .stream()
        .map(postDO -> mapper.convertValue(postDO, Post.class))
        .collect(Collectors.toList());
    posts.stream().forEach(post -> {
      List<PostTag> tags = postTagDAO.findByPostId(post.getId())
          .stream()
          .map(postTagDO -> mapper.convertValue(postTagDO, PostTag.class))
          .collect(Collectors.toList());
      post.setTags(tags);
    });
    return posts;
  }
}
