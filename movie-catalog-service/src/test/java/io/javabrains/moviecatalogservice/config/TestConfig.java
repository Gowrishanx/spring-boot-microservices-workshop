package io.javabrains.moviecatalogservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Gowri Shankar on 28/04/19.
 */

@Profile("test")
@Configuration
public class TestConfig {

//  @Primary
//  @Bean
//  public RestTemplate getRestTemplate() {
//    return new RestTemplate();
//
//  }
}
