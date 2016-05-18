package me.chuck.toylab.blog.store.impl.mysql.tag;

import com.google.inject.Inject;

import org.hibernate.SessionFactory;

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

  // TODO: implement methods
  @Override
  public Optional<PostTagDO> create(PostTagDO postTag) {
    return null;
  }

  @Override
  public Optional<PostTagDO> update(PostTagDO postTag) {
    return null;
  }

  @Override
  public Optional<PostTagDO> findById(int id) {
    return null;
  }

  @Override
  public Optional<PostTagDO> findAll(int page, int size) {
    return null;
  }

  @Override
  public Optional<PostTagDO> findByPostId(int id) {
    return null;
  }

  @Override
  public Optional<PostTagDO> findByTagId(int id) {
    return null;
  }
}
