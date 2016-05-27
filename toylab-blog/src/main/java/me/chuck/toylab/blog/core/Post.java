package me.chuck.toylab.blog.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.MoreObjects;

import java.util.Date;

import lombok.Data;

/**
 * @author chuck
 * @since 4/9/16
 */
@Data
public class Post {

  private int id;

  private String title;

  private String body;

  private int authorId;

  private String authorName;

//  private List<PostTag> tags;

  private Status status;

  private Date gmtCreated;

  private Date gmtUpdated;

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("title", title)
        .add("body", body)
        .add("authorId", authorId)
        .add("authorName", authorName)
//        .add("tags", tags)
        .add("status", status)
        .add("gmtCreated", gmtCreated)
        .add("gmtUpdated", gmtUpdated)
        .toString();
  }

  public enum Status {
    UNKNOWN(-1),
    DRAFT(0),
    PRIVATE(1),
    PUBLIC(2),
    DELETED(3),
    ;

    public final int value;

    Status(int value) {
      this.value = value;
    }

    @JsonCreator
    public static Status of(int value) {
      for (Status s : Status.values()) {
        if (s.value == value) {
          return s;
        }
      }
      return UNKNOWN;
    }

    @JsonValue
    public int value() {
      return this.value;
    }

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this)
          .add("name", name())
          .add("value", value)
          .toString();
    }
  }
}
