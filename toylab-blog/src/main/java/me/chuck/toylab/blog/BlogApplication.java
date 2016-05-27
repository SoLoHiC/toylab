package me.chuck.toylab.blog;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.Guice;
import com.google.inject.Injector;

import org.hibernate.validator.constraints.NotEmpty;

import java.text.SimpleDateFormat;

import io.dropwizard.Application;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import me.chuck.toylab.blog.core.CoreModule;
import me.chuck.toylab.blog.resources.PostTagResource;
import me.chuck.toylab.blog.resources.TagResource;
import me.chuck.toylab.blog.store.DBModule;
import me.chuck.toylab.blog.resources.PostResource;
import me.chuck.toylab.blog.resources.UserResource;

/**
 * @author chuck
 * @since 4/9/16
 */
public class BlogApplication extends Application<BlogConfiguration> {

  private Injector injector;

  @Override
  public void initialize(Bootstrap<BlogConfiguration> bootstrap) {
    bootstrap.getObjectMapper()
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    injector = Guice.createInjector(
        new RootModule(),
        new CoreModule(),
        new DBModule());

    bootstrap.addBundle(injector.getInstance(HibernateBundle.class));

  }

  @Override
  public void run(BlogConfiguration configuration, Environment environment) throws Exception {
    environment.jersey().register(injector.getInstance(PostResource.class));
    environment.jersey().register(injector.getInstance(UserResource.class));
    environment.jersey().register(injector.getInstance(TagResource.class));
    environment.jersey().register(injector.getInstance(PostTagResource.class));
  }

  @Override
  @NotEmpty
  public String getName() {
    return "toylab-blog";
  }

  public static void main(String[] args) throws Exception {
    new BlogApplication().run(args);
  }

}
