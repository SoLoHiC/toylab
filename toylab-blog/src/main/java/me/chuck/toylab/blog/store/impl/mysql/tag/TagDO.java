package me.chuck.toylab.blog.store.impl.mysql.tag;

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
 * @since 5/18/16
 */
@Data
@Entity
@Table(name = "tag")
@NamedQueries({
    @NamedQuery(
        name = "me.chuck.toylab.blog.store.impl.mysql.tag.TagDO.findAll",
        query = "SELECT tag FROM TagDO tag " +
            "ORDER BY tag.id DESC "
    )
})
public class TagDO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "color", nullable = false)
  private int color;

  @Column(name = "gmt_created", nullable = false)
  private Date gmtCreated;

  @Column(name = "gmt_updated", nullable = false)
  private Date gmtUpdated;

}