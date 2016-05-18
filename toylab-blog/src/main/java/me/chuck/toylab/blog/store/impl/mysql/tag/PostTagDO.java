package me.chuck.toylab.blog.store.impl.mysql.tag;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author chuck
 * @since 5/18/16
 */
@Data
@Entity
@Table(name = "post_tag")
@NamedQueries({
    @NamedQuery(
        name = "me.chuck.toylab.blog.store.impl.mysql.tag.findAll",
        query = "SELECT postTag FROM PostTagDO postTag " +
            "ORDER BY postTag.id DESC"
    ),
    @NamedQuery(
        name = "me.chuck.toylab.blog.store.impl.mysql.tag.findByPostId",
        query = "SELECT postTag FROM PostTagDO postTag " +
            "WHERE postTag.post_id = :postId " +
            "ORDER BY postTag.id DESC"
    ),
    @NamedQuery(
        name = "me.chuck.toylab.blog.store.impl.mysql.tag.findByTagId",
        query = "SELECT postTag FROM PostTagDO postTag " +
            "WHERE postTag.tag_id = :tagId " +
            "ORDER BY postTag.id DESC"
    )
})
public class PostTagDO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int postId;

  private int tagId;

  private Date gmtCreated;

  private Date gmtUpdated;
}
