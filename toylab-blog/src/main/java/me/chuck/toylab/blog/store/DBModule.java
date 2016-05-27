package me.chuck.toylab.blog.store;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import org.hibernate.SessionFactory;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import me.chuck.toylab.blog.BlogConfiguration;
import me.chuck.toylab.blog.store.impl.mysql.post.PostDAOImpl;
import me.chuck.toylab.blog.store.impl.mysql.post.PostDO;
import me.chuck.toylab.blog.store.impl.mysql.tag.PostTagDAOImpl;
import me.chuck.toylab.blog.store.impl.mysql.tag.TagDAOImpl;
import me.chuck.toylab.blog.store.impl.mysql.tag.TagDO;
import me.chuck.toylab.blog.store.impl.mysql.user.UserDAOImpl;
import me.chuck.toylab.blog.store.impl.mysql.user.UserDO;

/**
 * @author chuck
 * @since 4/13/16
 */
public class DBModule extends AbstractModule {

  private final HibernateBundle<BlogConfiguration> hibernateBundle =
      new HibernateBundle<BlogConfiguration>(PostDO.class, UserDO.class, TagDO.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(BlogConfiguration blogConfiguration) {
          return blogConfiguration.getDatabase();
        }
      };

  @Override
  protected void configure() {
    bind(PostDAO.class)
        .annotatedWith(Names.named("postDAO"))
        .to(PostDAOImpl.class)
        .in(Singleton.class);
    bind(UserDAO.class)
        .annotatedWith(Names.named("userDAO"))
        .to(UserDAOImpl.class)
        .in(Singleton.class);
    bind(TagDAO.class)
        .annotatedWith(Names.named("tagDAO"))
        .to(TagDAOImpl.class)
        .in(Singleton.class);
    bind(PostTagDAO.class)
        .annotatedWith(Names.named("postTagDAO"))
        .to(PostTagDAOImpl.class)
        .in(Singleton.class);
  }

  @Provides
  SessionFactory provideSessionFactory() {
    return this.hibernateBundle.getSessionFactory();
  }

  @Provides
  HibernateBundle provideHibernateBundle() {
    return this.hibernateBundle;
  }
}
