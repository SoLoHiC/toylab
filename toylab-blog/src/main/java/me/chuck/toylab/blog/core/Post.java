package me.chuck.toylab.blog.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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

  private Status status;

  private Date gmtCreated;

  private Date gmtUpdated;

  public enum Status {
    UNKNOWN(-1),
    DRAFT(0),
    PUBLIC(1),
    PRIVATE(2),
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
  }
}
