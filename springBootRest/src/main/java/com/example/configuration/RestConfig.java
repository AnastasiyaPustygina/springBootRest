package com.example.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    public RestOperations restOperations(){
        return new RestTemplate();
    }

    @Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager("genre");
    }

}
