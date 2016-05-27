package me.chuck.toylab.blog.store;

import java.util.List;
import java.util.Optional;

import me.chuck.toylab.blog.store.impl.mysql.tag.TagDO;

/**
 * @author chuck
 * @since 5/18/16
 */
public interface TagDAO {

  Optional<TagDO> create(TagDO tag);
  Optional<TagDO> update(TagDO tag);
  Optional<TagDO> findById(int id);
  Optional<TagDO> findByName(String name);
  List<TagDO> findAll(int page, int size);
}
