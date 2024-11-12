package com.rbalazs.storeapi.repository.redis;

import com.rbalazs.storeapi.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRedisRepository extends CrudRepository<Product, String> {}