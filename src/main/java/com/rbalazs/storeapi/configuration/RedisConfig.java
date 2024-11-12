package com.rbalazs.storeapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = "com.rbalazs.storeapi.repository.redis")
public class RedisConfig {}