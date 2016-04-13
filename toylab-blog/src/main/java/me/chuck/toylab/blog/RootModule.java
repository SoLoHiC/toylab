package me.chuck.toylab.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.text.SimpleDateFormat;

/**
 * @author chuck
 * @since 4/14/16
 */
class RootModule extends AbstractModule {

  @Override
  protected void configure() {

  }

  @Provides
  @Singleton
  ObjectMapper provideObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
    .setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
    return objectMapper;
  }
}
