package com.example.demo.rest.controller;

import com.example.demo.persistance.model.CategoryType;
import com.example.demo.rest.model.product.ProductRequestModel;
import com.example.demo.rest.model.product.ProductResponseModel;
import com.example.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
public class ProductController implements Serializable {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    private static final long serialVersionUID = -8136528719240504082L;


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/product")
    public ResponseEntity create(@RequestBody ProductRequestModel productRequestModel){
        LOGGER.info("Creating product- {}", productRequestModel);
        ProductResponseModel product = productService.create(productRequestModel);
        LOGGER.info("Successfuly created product - {}", product);
        return ResponseEntity.ok(product);
    }

    @GetMapping(value = "/product")
    public ResponseEntity selectAllProducts(){
        LOGGER.info("Geting all products");
        List<ProductResponseModel> products = productService.selectAllProducts();
        LOGGER.info("Successfully selected all categories - {}",products);
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity findProductById(@PathVariable Long id){
        LOGGER.info("Geting category by id - {}",id);
        ProductResponseModel product = productService.findProductById(id);
        LOGGER.info("Successfully be find category by id - {}",product);
        return ResponseEntity.ok(product);
    }

    @GetMapping(value = "/product/count/{type}")
    public ResponseEntity countProductByCategoryType(@PathVariable CategoryType type){
        LOGGER.info("Counting products by category type - {}",type);
        Long count = productService.countProductByCategoryType(type);
        LOGGER.info("Successfully counted products by category type - {}",count);
        return ResponseEntity.ok(count);
    }

    @PutMapping(value = "product/{id}")
    public ResponseEntity update(@PathVariable Long id,@RequestBody ProductRequestModel productRequestModel){
        LOGGER.info("Updating category by id - {} - {}",id,productRequestModel);
        ProductResponseModel updateProduct = productService.update(id,productRequestModel);
        LOGGER.info("Successfully updated category by id - {}",updateProduct);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping(value = "/product/{id}")
    public void delete(@PathVariable Long id){
        LOGGER.info("Deleting category by id - {}",id);
        productService.delete(id);
        LOGGER.info("Successfully deleted");
    }
}
