package me.chuck.toylab.blog.db;

import java.util.List;
import java.util.Optional;

import me.chuck.toylab.blog.core.Post;

/**
 * @author chuck
 * @since 5/1/16
 */
public interface PostDAO {

  Optional<Post> create(Post post);
  Optional<Post> update(Post post);
  Optional<Post> findById(int id);
  List<Post> findAll(int page, int pageSize);

}
