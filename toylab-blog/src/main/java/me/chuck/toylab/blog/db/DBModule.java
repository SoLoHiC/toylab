package me.chuck.toylab.blog.db;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

import org.hibernate.SessionFactory;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import me.chuck.toylab.blog.BlogConfiguration;
import me.chuck.toylab.blog.db.post.PostDAO;
import me.chuck.toylab.blog.db.post.PostDO;
import me.chuck.toylab.blog.db.user.UserDAO;
import me.chuck.toylab.blog.db.user.UserDO;

/**
 * @author chuck
 * @since 4/13/16
 */
public class DBModule extends AbstractModule {

  private final HibernateBundle<BlogConfiguration> hibernateBundle =
      new HibernateBundle<BlogConfiguration>(PostDO.class, UserDO.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(BlogConfiguration blogConfiguration) {
          return blogConfiguration.getDatabase();
        }
      };

  @Override
  protected void configure() {
    bind(PostDAO.class).annotatedWith(Names.named("postDAO")).to(PostDAO.class);
    bind(UserDAO.class).annotatedWith(Names.named("userDAO")).to(UserDAO.class);
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
