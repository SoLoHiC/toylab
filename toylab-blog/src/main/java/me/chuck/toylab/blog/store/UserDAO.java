package me.chuck.toylab.blog.store;

import java.util.Optional;

import me.chuck.toylab.blog.core.User;

/**
 * @author chuck
 * @since 5/1/16
 */
public interface UserDAO {

  Optional<User> create(User user);
  Optional<User> update(User user);
  Optional<User> findById(int id);
}
