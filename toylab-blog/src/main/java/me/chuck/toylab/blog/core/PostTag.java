package me.chuck.toylab.blog.core;

import com.google.common.base.MoreObjects;

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

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("postId", postId)
        .add("tagId", tagId)
        .add("tag", tag.toString())
        .add("gmtCreated", gmtCreated)
        .add("gmtUpdated", gmtUpdated)
        .toString();
  }
}
