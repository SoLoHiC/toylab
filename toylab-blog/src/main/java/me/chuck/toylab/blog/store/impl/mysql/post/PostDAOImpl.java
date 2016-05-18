package me.chuck.toylab.blog.store.impl.mysql.post;

import com.google.inject.Inject;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

import io.dropwizard.hibernate.AbstractDAO;
import me.chuck.toylab.blog.store.PostDAO;

/**
 * @author chuck
 * @since 4/3/16
 */
public class PostDAOImpl extends AbstractDAO<PostDO> implements PostDAO {

  @Inject
  public PostDAOImpl(SessionFactory factory) {
    super(factory);
  }

  @Override
  public Optional<PostDO> create(PostDO post) {
    return Optional.ofNullable((persist(post)));
  }

  @Override
  public Optional<PostDO> update(PostDO post) {
    return Optional.ofNullable(persist(post));
  }

  @Override
  public Optional<PostDO> findById(int id) {
    return Optional.ofNullable(get(id));
  }

  @Override
  public List<PostDO> findAll(int page, int pageSize) {
    return list(
        namedQuery("me.chuck.toylab.blog.db.impl.post.PostDO.findAll")
            .setFirstResult(page * pageSize)
            .setMaxResults(pageSize)
    );
  }
}
