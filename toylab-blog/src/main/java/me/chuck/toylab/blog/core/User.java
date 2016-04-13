package me.chuck.toylab.blog.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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


  public enum Auth {
    GUEST(1),
    ADMIN(2),
    ;

    private final int v;

    Auth(int v) {
      this.v = v;
    }

    @JsonCreator
    public static @Nullable Auth of(int x) {
      for (Auth auth : Auth.values()) {
        if (auth.v == x) {
          return auth;
        }
      }
      return null;
    }

    @JsonValue
    public int value() {
      return this.v;
    }
  }
}
