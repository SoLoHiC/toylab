package me.chuck.toylab.blog.db.impl.post;

import java.util.Date;

import javax.persistence.Column;
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
 * @since 4/3/16
 */
@Entity
@Table(name = "post")
@NamedQueries({
    @NamedQuery(
        name = "me.chuck.toylab.blog.db.impl.post.PostDO.findAll",
        query = "SELECT post FROM PostDO post " +
            "ORDER BY post.id DESC"
    )
})
@Data
public class PostDO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "body", nullable = false)
  private String body;

  @Column(name = "author_id", nullable = false)
  private int authorId;

  @Column(name = "author_name", nullable = false)
  private String authorName;

  @Column(name = "post_status", nullable = false)
  private int status;

  @Column(name = "gmt_created", nullable = false)
  private Date gmtCreated;

  @Column(name = "gmt_updated", nullable = false)
  private Date gmtUpdated;
}
