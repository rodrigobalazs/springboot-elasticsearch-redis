package com.rbalazs.storeapi.controller;

import com.rbalazs.storeapi.controller.swagger.ProductControllerSwagger;
import com.rbalazs.storeapi.model.Product;
import com.rbalazs.storeapi.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Product REST Controller.
 * API Documentation/Swagger at => http://<project_url>/swagger-ui/index.html
 */
@RestController
@RequestMapping("/product")
public class ProductController implements ProductControllerSwagger {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getProducts() {
        LOGGER.info("starts to execute productController.getProducts()");
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        LOGGER.info("starts to execute productController.getProductById() with ID:{}" , id);
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        LOGGER.info("starts to execute productController.getProductByName() with name:{}" , name);
        Product product = productService.getProductByName(name);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        LOGGER.info("starts to execute productController.save()");
        Product savedProduct = productService.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        LOGGER.info("starts to execute productController.delete() with ID:{}" , id);
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}