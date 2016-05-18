package me.chuck.toylab.blog.store.impl.mysql.tag;

import com.google.inject.Inject;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

import io.dropwizard.hibernate.AbstractDAO;
import me.chuck.toylab.blog.store.TagDAO;

/**
 * @author chuck
 * @since 5/18/16
 */
public class TagDAOImpl extends AbstractDAO<TagDO> implements TagDAO {

  /**
   * Creates a new DAO with a given session provider.
   *
   * @param sessionFactory a session provider
   */
  @Inject
  public TagDAOImpl(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  // TODO: implement methods
  @Override
  public Optional<TagDO> create(TagDO tag) {
    return null;
  }

  @Override
  public Optional<TagDO> update(TagDO tag) {
    return null;
  }

  @Override
  public Optional<TagDO> findById(int id) {
    return null;
  }

  @Override
  public List<TagDO> paginate(int page, int size) {
    return null;
  }
}
