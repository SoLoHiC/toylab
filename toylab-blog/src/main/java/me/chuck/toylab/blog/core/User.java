package me.chuck.toylab.blog.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.MoreObjects;

import java.util.Date;

import javax.annotation.Nullable;

import lombok.Data;

/**
 * @author chuck
 * @since 4/12/16
 */
@Data
public class User {

  private int id;

  private String password;

  private String nickName;

  private String fullName;

  private Auth auth;

  private Date gmtCreated;

  private Date gmtUpdated;

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("nickName", nickName)
        .add("fullname", fullName)
        .add("auth", auth.toString())
        .add("gmtCreated", gmtCreated)
        .add("gmtUpdated", gmtUpdated)
        .toString();
  }

  public enum Auth {
    UNKNOWN(-1),
    GUEST(1),
    ADMIN(2),
    ;

    private final int value;

    Auth(int v) {
      this.value = v;
    }

    @JsonCreator
    public static @Nullable Auth of(int x) {
      for (Auth auth : Auth.values()) {
        if (auth.value == x) {
          return auth;
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
