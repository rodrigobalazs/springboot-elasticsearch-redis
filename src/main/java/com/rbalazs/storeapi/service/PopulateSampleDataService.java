package com.rbalazs.storeapi.service;

import com.rbalazs.storeapi.enums.AppConstants;
import com.rbalazs.storeapi.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Populates some sample Products into Elasticsearch and Redis.
 */
@Service
public class PopulateSampleDataService implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(PopulateSampleDataService.class);

    ProductService productService;

    @Autowired
    public PopulateSampleDataService(final ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {

        if ( CollectionUtils.isNotEmpty(productService.getProducts())) {
            return;
        }

        LOGGER.info("populates some sample Products into Elasticsearch and Redis ..");
        createProduct(AppConstants.PRODUCT_OFFICE_CHAIR_ID, AppConstants.PRODUCT_OFFICE_CHAIR_NAME,
                AppConstants.PRODUCT_OFFICE_CHAIR_PRICE, AppConstants.PRODUCT_OFFICE_DESCRIPTION);
        createProduct(AppConstants.PRODUCT_SOFA_ID, AppConstants.PRODUCT_SOFA_NAME,
                AppConstants.PRODUCT_SOFA_PRICE, AppConstants.PRODUCT_SOFA_DESCRIPTION);
    }

    private void createProduct(final String productId, final String productName, final double productPrice,
                                  final String productDescription){
        Product product = new Product(productId, productName, productPrice, productDescription);
        productService.save(product);
    }
}