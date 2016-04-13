package me.chuck.toylab.blog.db.user;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.SessionFactory;

import java.util.Optional;

import io.dropwizard.hibernate.AbstractDAO;
import me.chuck.toylab.blog.core.User;

/**
 * @author chuck
 * @since 4/12/16
 */
public class UserDAO extends AbstractDAO<UserDO> {

  private static final ObjectMapper mapper = new ObjectMapper();
  public UserDAO(SessionFactory factory) {
    super(factory);
  }

  public Optional<User> create(User user) {
    UserDO userDO = mapper.convertValue(user, UserDO.class);
    return Optional.ofNullable(mapper.convertValue(persist(userDO), User.class));
  }

  public Optional<User> findById(int id) {
    return Optional.ofNullable(mapper.convertValue(get(id), User.class));
  }
}
