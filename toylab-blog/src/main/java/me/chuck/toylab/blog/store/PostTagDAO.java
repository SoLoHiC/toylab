package me.chuck.toylab.blog.store;

import java.util.List;
import java.util.Optional;

import me.chuck.toylab.blog.store.impl.mysql.tag.PostTagDO;

/**
 * @author chuck
 * @since 5/18/16
 */
public interface PostTagDAO {

  Optional<PostTagDO> create(PostTagDO postTag);
  Optional<PostTagDO> update(PostTagDO postTag);
  Optional<PostTagDO> findById(int id);
  List<PostTagDO> findAll(int page, int size);
  List<PostTagDO> findByPostId(int id);
  List<PostTagDO> findByTagId(int id);
}
