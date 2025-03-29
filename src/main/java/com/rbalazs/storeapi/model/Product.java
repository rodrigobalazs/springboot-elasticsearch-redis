package com.rbalazs.storeapi.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * Represents a given Product which is stored both into Elasticsearch ( for indexing and searching ) and
 * Redis ( for caching ).
 * The entity needs to be Serializable to work properly with Redis Cache.
 *
 * @author Rodrigo Balazs
 */
@RedisHash("products")
@Document(indexName = "products")
@Getter
@Setter
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /** The Product Identifier is used as the document ID for Elasticsearch and also as the primary key for Redis Cache,
        it´s String type for better Elasticsearch/Redis compatibility. */
    @Id
    private String id;

    private String name;
    private String description;
    private double price;

    /** Empty Constructor required by Elasticsearch / Redis. */
    public Product() {}

    /**
     * Creates a new Product.
     */
    public Product(final String theId, final String theName, final Double thePrice, final String theDescription) {
        Validate.notEmpty(theId, "The product identifier cannot be null nor empty");
        Validate.notEmpty(theName, "The product name cannot be null nor empty");
        Validate.notEmpty(theDescription, "The product description cannot be null nor empty");
        id = theId;
        name = theName;
        price = thePrice;
        description = theDescription;
    }
}