package me.chuck.toylab.blog.store.impl.mysql.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.dropwizard.hibernate.AbstractDAO;
import me.chuck.toylab.blog.core.Post;
import me.chuck.toylab.blog.store.PostDAO;

/**
 * @author chuck
 * @since 4/3/16
 */
public class PostDAOImpl extends AbstractDAO<PostDO> implements PostDAO {

  @Inject
  private ObjectMapper mapper;

  @Inject
  public PostDAOImpl(SessionFactory factory) {
    super(factory);
  }

  @Override
  public Optional<Post> create(Post post) {
    PostDO postDO = mapper.convertValue(post, PostDO.class);
    return Optional.ofNullable(mapper.convertValue(persist(postDO), Post.class));
  }

  @Override
  public Optional<Post> update(Post post) {
    PostDO postDO = mapper.convertValue(post, PostDO.class);
    return Optional.ofNullable(mapper.convertValue(persist(postDO), Post.class));
  }

  @Override
  public Optional<Post> findById(int id) {
    return Optional.ofNullable(mapper.convertValue(get(id), Post.class));
  }

  @Override
  public List<Post> findAll(int page, int pageSize) {
    return list(
        namedQuery("me.chuck.toylab.blog.db.impl.post.PostDO.findAll")
            .setFirstResult(page * pageSize)
            .setMaxResults(pageSize)
    )
        .stream()
        .map(postDO -> mapper.convertValue(postDO, Post.class))
        .collect(Collectors.toList());
  }
}
