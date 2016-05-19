package me.chuck.toylab.blog.core.repos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import me.chuck.toylab.blog.core.Post;
import me.chuck.toylab.blog.core.PostTag;
import me.chuck.toylab.blog.store.PostDAO;
import me.chuck.toylab.blog.store.PostTagDAO;
import me.chuck.toylab.blog.store.impl.mysql.post.PostDO;
import me.chuck.toylab.blog.store.impl.mysql.tag.PostTagDO;

/**
 * @author chuck
 * @since 5/19/16
 */
@Slf4j
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
    PostDO postDO = mapper.convertValue(post, PostDO.class);
    Optional<PostDO> createdDO = postDAO.create(postDO);
    if (createdDO.isPresent()) {
      Post created = mapper.convertValue(createdDO.get(), Post.class);
      created.setTags(post.getTags());
      created.getTags().stream()
          .forEach(postTag -> {
            postTag.setPostId(created.getId());
            PostTagDO postTagDO = mapper.convertValue(postTag, PostTagDO.class);
            Optional<PostTagDO> createdTagDO = postTagDAO.create(postTagDO);
            if (createdTagDO.isPresent()) {
              postTag = mapper.convertValue(createdTagDO, PostTag.class);
            } else {
              log.warn(String.format("failed to create postTag: %s for post: %s", postTag, created));
            }
          });
      return Optional.of(created);
    }
    return Optional.empty();
  }

  public Optional<Post> update(Post post) {
    PostDO postDO = mapper.convertValue(post, PostDO.class);
    Optional<PostDO> createdDO = postDAO.update(postDO);
    if (createdDO.isPresent()) {
      Post created = mapper.convertValue(createdDO, Post.class);
      return Optional.ofNullable(created);
    }
    return Optional.empty();
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
