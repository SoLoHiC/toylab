package me.chuck.toylab.blog.store.impl.mysql.tag;

import com.google.inject.Inject;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

import io.dropwizard.hibernate.AbstractDAO;
import me.chuck.toylab.blog.store.PostTagDAO;

/**
 * @author chuck
 * @since 5/18/16
 */
public class PostTagDAOImpl extends AbstractDAO<PostTagDO> implements PostTagDAO {

  /**
   * Creates a new DAO with a given session provider.
   *
   * @param sessionFactory a session provider
   */
  @Inject
  public PostTagDAOImpl(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public Optional<PostTagDO> create(PostTagDO postTag) {
    return Optional.ofNullable(persist(postTag));
  }

  @Override
  public Optional<PostTagDO> update(PostTagDO postTag) {
    return Optional.ofNullable(persist(postTag));
  }

  @Override
  public Optional<PostTagDO> findById(int id) {
    return Optional.ofNullable(get(id));
  }

  @Override
  public List<PostTagDO> findAll(int page, int size) {
    return list(
        namedQuery("me.chuck.toylab.blog.store.impl.mysql.tag.findAll")
            .setFirstResult(page * size)
            .setMaxResults(size)
    );
  }

  @Override
  public List<PostTagDO> findByPostId(int id) {
    return list(
        namedQuery("me.chuck.toylab.blog.store.impl.mysql.tag.findByPostId")
            .setParameter("postId", id)
    );
  }

  @Override
  public List<PostTagDO> findByTagId(int id) {
    return list(
        namedQuery("me.chuck.toylab.blog.store.impl.mysql.tag.findByTagId")
            .setParameter("tagId", id)
    );
  }
}
