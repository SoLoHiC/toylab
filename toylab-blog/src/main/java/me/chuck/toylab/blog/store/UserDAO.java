package me.chuck.toylab.blog.store;

import java.util.Optional;

import me.chuck.toylab.blog.store.impl.mysql.user.UserDO;

/**
 * @author chuck
 * @since 5/1/16
 */
public interface UserDAO {

  // TODO: change return type from User to UserDO
  Optional<UserDO> create(UserDO user);
  Optional<UserDO> update(UserDO user);
  Optional<UserDO> findById(int id);
}
