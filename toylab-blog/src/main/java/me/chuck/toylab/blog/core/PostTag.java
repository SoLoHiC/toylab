package me.chuck.toylab.blog.core;

import java.util.Date;

import lombok.Data;

/**
 * @author chuck
 * @since 5/18/16
 */
@Data
public class PostTag {

  private int id;

  private int postId;

  private int tagId;

  private Tag tag;

  private Date gmtCreated;

  private Date gmtUpdated;
}
