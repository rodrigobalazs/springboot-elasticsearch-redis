package com.rbalazs.storeapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.rbalazs.storeapi.repository.elasticsearch")
public class ElasticSearchConfig {}