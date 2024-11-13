package com.rbalazs.storeapi.controller.swagger;

import com.rbalazs.storeapi.controller.ProductController;
import com.rbalazs.storeapi.enums.AppConstants;
import com.rbalazs.storeapi.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Swagger interface related to {@link ProductController}.
 * API Documentation/Swagger at => http://<project_url>/swagger-ui/index.html
 */
@Tag(name = "Product API", description = "API endpoints related to Products")
public interface ProductControllerSwagger {

    @Operation(summary = "Retrieves all Products ( this endpoints is non-cached, therefore results are fetched " +
                         "directly from Elasticsearch )")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns a JSON Array with all the Products",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Product.class)))})})
    public ResponseEntity<List<Product>> getProducts();


    @Operation(summary = "Retrieves a Product by ID ( first result will be fetched from Elasticsearch, " +
            "subsequent cached ones from Redis Cache )")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns a JSON Object with the Product information",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Product.class))})})
    public ResponseEntity<Product> getProductById(
            @Parameter(description = "product ID", example = "1", required = true)
            @PathVariable String id);


    @Operation(summary = "Retrieves a Product by Name ( first result will be fetched from Elasticsearch, " +
            "subsequent cached ones from Redis Cache )")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns a JSON Object with the Product information",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Product.class))})})
    public ResponseEntity<Product> getProductByName(
            @Parameter(description = "product name", example = AppConstants.PRODUCT_SOFA_NAME, required = true)
            @PathVariable String name);


    @Operation(summary = "Saves a new Product into Elasticsearch and into Redis Cache")
    public ResponseEntity<Product> save(@RequestBody Product product);


    @Operation(summary = "Deletes a Product by ID from Elasticsearch, also deletes the cache entry from Redis Cache")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns void / nothing",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = Void.class)))})})
    public ResponseEntity<Void> delete(
            @Parameter(description = "product ID to delete", example = "1", required = true) @PathVariable String id);
}
