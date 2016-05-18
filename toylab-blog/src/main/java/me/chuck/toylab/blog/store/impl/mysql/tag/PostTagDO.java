package me.chuck.toylab.blog.store.impl.mysql.tag;

import java.util.Date;

import lombok.Data;

/**
 * @author chuck
 * @since 5/18/16
 */
@Data
public class PostTagDO {

  // TODO: mapping db table
  private int id;

  private int postId;

  private int tagId;

  private Date gmtCreated;

  private Date gmtUpdated;
}
