package me.chuck.toylab.blog.store.impl.mysql.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import org.hibernate.SessionFactory;

import java.util.Optional;

import io.dropwizard.hibernate.AbstractDAO;
import me.chuck.toylab.blog.core.User;
import me.chuck.toylab.blog.store.UserDAO;

/**
 * @author chuck
 * @since 4/12/16
 */
public class UserDAOImpl extends AbstractDAO<UserDO> implements UserDAO {

  @Inject
  private ObjectMapper mapper;

  @Inject
  public UserDAOImpl(SessionFactory factory) {
    super(factory);
  }

  @Override
  public Optional<User> create(User user) {
    UserDO userDO = mapper.convertValue(user, UserDO.class);
    return Optional.ofNullable(mapper.convertValue(persist(userDO), User.class));
  }

  @Override
  public Optional<User> update(User user) {
    UserDO userDO = mapper.convertValue(user, UserDO.class);
    return Optional.ofNullable(mapper.convertValue(persist(userDO), User.class));
  }

  @Override
  public Optional<User> findById(int id) {
    return Optional.ofNullable(mapper.convertValue(get(id), User.class));
  }
}
