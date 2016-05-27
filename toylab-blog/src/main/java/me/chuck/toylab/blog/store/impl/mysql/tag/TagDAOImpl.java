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

  @Override
  public Optional<TagDO> create(TagDO tag) {
    return Optional.ofNullable(persist(tag));
  }

  @Override
  public Optional<TagDO> update(TagDO tag) {
    return Optional.ofNullable(persist(tag));
  }

  @Override
  public Optional<TagDO> findById(int id) {
    return Optional.ofNullable(get(id));
  }

  @Override
  public Optional<TagDO> findByName(String name) {
    return Optional.ofNullable(
        list(
            namedQuery("me.chuck.toylab.blog.store.impl.mysql.tag.TagDO.findByName").setFetchSize(1)
        ).get(0)
    );
  }
  @Override
  public List<TagDO> findAll(int page, int size) {
    return list(
        namedQuery("me.chuck.toylab.blog.store.impl.mysql.tag.TagDO.findAll")
            .setFirstResult(page * size)
            .setMaxResults(size)
    );
  }
}
