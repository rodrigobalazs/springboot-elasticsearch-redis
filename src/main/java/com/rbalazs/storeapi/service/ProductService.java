package com.rbalazs.storeapi.service;

import com.rbalazs.storeapi.model.Product;
import com.rbalazs.storeapi.repository.elasticsearch.ProductElasticSearchRepository;
import com.rbalazs.storeapi.repository.redis.ProductRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Product Service.
 *
 * @author Rodrigo Balazs
 */
@Service
public class ProductService {

    private ProductElasticSearchRepository productElasticSearchRepository;
    private ProductRedisRepository productRedisRepository;

    @Autowired
    public ProductService(final ProductElasticSearchRepository productElasticSearchRepository,
                          final ProductRedisRepository productRedisRepository) {
        this.productElasticSearchRepository = productElasticSearchRepository;
        this.productRedisRepository = productRedisRepository;
    }

    public List<Product> getProducts() {
        // TODO(rodrigo.balazs) ATM it´s returning a max of 10 Products
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = productElasticSearchRepository.findAll(pageable);
        return productPage.getContent();
    }

    /**
     * Retrieves a {@link Product} by its ID. The first retrieval will fetch the Product from Elasticsearch,
     * and subsequent retrievals will return cached results from Redis.
     */
    @Cacheable(value = "products", key = "#id")
    public Product getProductById(String id) {
        Optional<Product> product = productElasticSearchRepository.findById(id);
        return product.orElse(null);
    }

    /**
     * Retrieves a {@link Product} by its name. The first retrieval will fetch the Product from Elasticsearch,
     * and subsequent retrievals will return cached results from Redis.
     */
    @Cacheable(value = "products", key = "#name")
    public Product getProductByName(String name) {
        Optional<Product> product = productElasticSearchRepository.findByName(name);
        return product.orElse(null);
    }

    /**
     * Saves a new {@link Product} into Elasticsearch and into Redis Cache
     */
    public Product save(Product product) {
        productElasticSearchRepository.save(product);
        productRedisRepository.save(product);
        return product;
    }

    /**
     * Deletes a {@link Product} by ID from Elasticsearch and also deletes the corresponding cache entry
     * from Redis Cache (@CacheEvict)
     */
    @CacheEvict(value = "products", key = "#id")
    public void delete(String id) {
        productElasticSearchRepository.deleteById(id);
    }
}
