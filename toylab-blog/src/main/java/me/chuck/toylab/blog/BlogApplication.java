package me.chuck.toylab.blog;

import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import me.chuck.toylab.blog.db.post.PostDAO;
import me.chuck.toylab.blog.db.post.PostDO;
import me.chuck.toylab.blog.db.user.UserDAO;
import me.chuck.toylab.blog.db.user.UserDO;
import me.chuck.toylab.blog.resources.PostResource;
import me.chuck.toylab.blog.resources.UserResource;

/**
 * @author chuck
 * @since 4/9/16
 */
public class BlogApplication extends Application<BlogConfiguration> {

  private final HibernateBundle<BlogConfiguration> hibernateBundle =
      new HibernateBundle<BlogConfiguration>(PostDO.class, UserDO.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(BlogConfiguration blogConfiguration) {
          return blogConfiguration.getDatabase();
        }
      };

  @Override
  public void initialize(Bootstrap<BlogConfiguration> bootstrap) {
    bootstrap.getObjectMapper()
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    bootstrap.addBundle(hibernateBundle);
  }

  @Override
  public void run(BlogConfiguration configuration, Environment environment) throws Exception {
    final PostDAO postDAO = new PostDAO(hibernateBundle.getSessionFactory());
    final UserDAO userDAO = new UserDAO(hibernateBundle.getSessionFactory());

    environment.jersey().register(new PostResource(postDAO));
    environment.jersey().register(new UserResource(userDAO));
  }

  @Override
  public String getName() {
    return "toylab-blog";
  }

  public static void main(String[] args) throws Exception {
    new BlogApplication().run(args);
  }

}
