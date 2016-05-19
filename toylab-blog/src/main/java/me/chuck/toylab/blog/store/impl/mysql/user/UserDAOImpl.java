package me.chuck.toylab.blog.store.impl.mysql.user;

import com.google.inject.Inject;

import org.hibernate.SessionFactory;

import java.util.Optional;

import io.dropwizard.hibernate.AbstractDAO;
import me.chuck.toylab.blog.store.UserDAO;

/**
 * @author chuck
 * @since 4/12/16
 */
public class UserDAOImpl extends AbstractDAO<UserDO> implements UserDAO {

  @Inject
  public UserDAOImpl(SessionFactory factory) {
    super(factory);
  }

  @Override
  public Optional<UserDO> create(UserDO user) {
    return Optional.ofNullable(persist(user));
  }

  @Override
  public Optional<UserDO> update(UserDO user) {
    return Optional.ofNullable(persist(user));
  }

  @Override
  public Optional<UserDO> findById(int id) {
    return Optional.ofNullable(get(id));
  }
}
