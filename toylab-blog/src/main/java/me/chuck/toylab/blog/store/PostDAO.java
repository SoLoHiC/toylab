package me.chuck.toylab.blog.store;

import java.util.List;
import java.util.Optional;

import me.chuck.toylab.blog.store.impl.mysql.post.PostDO;

/**
 * @author chuck
 * @since 5/1/16
 */
public interface PostDAO {

  Optional<PostDO> create(PostDO post);
  Optional<PostDO> update(PostDO post);
  Optional<PostDO> findById(int id);
  List<PostDO> findAll(int page, int pageSize);

}
