package com.rbalazs.storeapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.redis.core.RedisHash;

/**
 * Represents a given Product which is stored both into Elasticsearch ( for indexing and searching ) and Redis ( for caching ).
 *
 * @author Rodrigo Balazs
 */
@RedisHash("products")
@Document(indexName = "products")
@Getter
@Setter
public class Product {

    /** The Product Identifier is used as the document ID for Elasticsearch and also as the primary key for Redis Cache,
        it´s String type for Redis / Elasticsearch convenience. */
    @Id
    private String id;

    private String name;
    private String description;
    private double price;
}