package me.chuck.toylab.blog.db.post;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

import io.dropwizard.hibernate.AbstractDAO;

/**
 * @author chuck
 * @since 4/3/16
 */
public class PostDAO extends AbstractDAO<PostDO> {

  public PostDAO(SessionFactory factory) {
    super(factory);
  }

  public Optional<PostDO> create(PostDO postDO) {
    return Optional.ofNullable(persist(postDO));
  }

  public Optional<PostDO> findById(int id) {
    return Optional.ofNullable(get(id));
  }

  public List<PostDO> findAll(int page, int pageSize) {
    return list(
        namedQuery("me.chuck.toylab.blog.db.post.PostDO.findAll")
            .setFirstResult(page * pageSize)
            .setMaxResults(pageSize)
    );
  }
}
