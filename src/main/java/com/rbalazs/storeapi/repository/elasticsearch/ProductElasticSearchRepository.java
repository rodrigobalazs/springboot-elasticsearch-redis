package com.rbalazs.storeapi.repository.elasticsearch;

import com.rbalazs.storeapi.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.Optional;

public interface ProductElasticSearchRepository extends ElasticsearchRepository<Product, String> {
    Optional<Product> findByName(String name);
}