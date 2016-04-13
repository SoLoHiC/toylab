package me.chuck.toylab.blog.db.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author chuck
 * @since 4/12/16
 */
@Entity
@Table(name = "users")
@Data
public class UserDO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "pwd", nullable = false)
  private String password;

  @Column(name = "nick_name", nullable = false)
  private String nickName;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "auth", nullable = false)
  private int auth;

  @Column(name = "gmt_created", nullable = false)
  private Date gmtCreated;

  @Column(name = "gmt_updated", nullable = false)
  private Date gmtUpdated;

}
