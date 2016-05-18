package me.chuck.toylab.blog.core;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import me.chuck.toylab.blog.core.repos.PostRepo;

/**
 * @author chuck
 * @since 5/19/16
 */
public class CoreModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(PostRepo.class).in(Singleton.class);
  }
}
