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

  private Status status;

  private Date gmtCreated;

  private Date gmtUpdated;

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("postId", postId)
        .add("tagId", tagId)
        .add("tag", tag.toString())
        .add("status", status.name())
        .add("gmtCreated", gmtCreated)
        .add("gmtUpdated", gmtUpdated)
        .toString();
  }

  public enum Status {
    UNKNOWN(0),
    CREATED(1),
    DELETED(-1),
    ;

    private final int value;
    Status(int value) {
      this.value = value;
    }

    public static Status of(int value) {
      for (Status s : Status.values()) {
        if (s.value() == value) {
          return s;
        }
      }
      return UNKNOWN;
    }

    public int value() {
      return this.value;
    }
  }
}
