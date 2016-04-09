package me.chuck.toylab.blog;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import me.chuck.toylab.blog.db.post.PostDAO;
import me.chuck.toylab.blog.db.post.PostDO;
import me.chuck.toylab.blog.resources.PostResource;

/**
 * @author chuck
 * @since 4/9/16
 */
public class BlogApplication extends Application<BlogConfiguration> {

  private final HibernateBundle<BlogConfiguration> hibernateBundle =
      new HibernateBundle<BlogConfiguration>(PostDO.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(BlogConfiguration blogConfiguration) {
          return blogConfiguration.getDatabase();
        }
      };

  @Override
  public void initialize(Bootstrap<BlogConfiguration> bootstrap) {
    bootstrap.addBundle(hibernateBundle);
  }

  @Override
  public void run(BlogConfiguration configuration, Environment environment) throws Exception {
    final PostDAO dao = new PostDAO(hibernateBundle.getSessionFactory());

    environment.jersey().register(new PostResource(dao));
  }

  @Override
  public String getName() {
    return "toylab-blog";
  }

  public static void main(String[] args) throws Exception {
    new BlogApplication().run(args);
  }

}
