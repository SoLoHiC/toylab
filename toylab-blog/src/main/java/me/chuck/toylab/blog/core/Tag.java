package me.chuck.toylab.blog.core;

import com.google.common.base.MoreObjects;

import java.util.Date;

import lombok.Data;

/**
 * @author chuck
 * @since 5/18/16
 */
@Data
public class Tag {

  private int id;

  private String name;

  // TODO: color mapping need to be set
  private int color;

  private Date gmtCreated;

  private Date gmtUpdated;

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("name", name)
        .add("color", color)
        .add("gmtCreated", gmtCreated)
        .add("gmtUpdated", gmtUpdated)
        .toString();
  }
}
