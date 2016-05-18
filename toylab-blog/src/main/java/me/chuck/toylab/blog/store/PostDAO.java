package me.chuck.toylab.blog.store;

import java.util.List;
import java.util.Optional;

import me.chuck.toylab.blog.core.Post;

/**
 * @author chuck
 * @since 5/1/16
 */
public interface PostDAO {

  // TODO: change return type from Post to PostDO
  Optional<Post> create(Post post);
  Optional<Post> update(Post post);
  Optional<Post> findById(int id);
  List<Post> findAll(int page, int pageSize);

}
