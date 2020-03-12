package com.example.demo.service;

import com.example.demo.persistance.model.Category;
import com.example.demo.persistance.model.CategoryType;
import com.example.demo.persistance.model.Product;
import com.example.demo.persistance.repository.CategoryRepository;
import com.example.demo.persistance.repository.ProductRepository;
import com.example.demo.rest.model.category.CategoryResponseModel;
import com.example.demo.rest.model.product.ProductRequestModel;
import com.example.demo.rest.model.product.ProductResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements Serializable {
    private static final long serialVersionUID = 929206620406869299L;
    private final static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponseModel create(ProductRequestModel productRequestModel) {
        LOGGER.info("Request to create product- {}", productRequestModel);
        Product product = buildProductFrom(productRequestModel);
        Category category = categoryRepository.findById(productRequestModel.getCategoryId()).get();
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        LOGGER.info("Successfuly created product - {}", savedProduct);
        return buildProductResponseModel(savedProduct);
    }

    public List<ProductResponseModel> selectAllProducts() {
        LOGGER.info("Geting all products");
        List<Product> products = productRepository.findAll();
        List<ProductResponseModel> collect = products.stream()
                .map(this::buildProductResponseModel).collect(Collectors.toList());
        LOGGER.info("Successfully selected all categories - {}",collect);
        return collect;
    }

    public ProductResponseModel findProductById(Long id) {
        LOGGER.info("Geting category by id - {}",id);
        Product product = productRepository.findById(id).get();
        LOGGER.info("Successfully be find category by id - {}",product);
        return buildProductResponseModel(product);
    }

    public ProductResponseModel update(Long id, ProductRequestModel productRequestModel) {
        LOGGER.info("Updating category by id - {} - {} ",id,productRequestModel);
        Product product = productRepository.findById(id).get();
        product.setName(productRequestModel.getName());
        product.setPrice(productRequestModel.getPrice());
        Category category = categoryRepository.findById(productRequestModel.getCategoryId()).get();
        product.setCategory(category);
        Product updateProduct = productRepository.save(product);
        ProductResponseModel productResponseModel = buildProductResponseModel(updateProduct);
        LOGGER.info("Successfully updated category by id - {}",productResponseModel);
        return productResponseModel;
    }

    public void delete(Long id) {
        LOGGER.info("Deleting category by id - {}",id);
        productRepository.deleteById(id);
        LOGGER.info("Successfully deleted");
    }

    public Long countProductByCategoryType(CategoryType type){
        LOGGER.info("Counting products by category type - {}",type);
        Long count = productRepository.countByCategory_Type(type);
        LOGGER.info("Successfully counted products by category type - {}",count);
        return count;
    }

    //region Private Build Methods
    private Product buildProductFrom(ProductRequestModel productRequestModel) {
        Product product = new Product();
        product.setName(productRequestModel.getName());
        product.setPrice(productRequestModel.getPrice());
        return product;
    }

    private ProductResponseModel buildProductResponseModel(Product product) {
        ProductResponseModel productResponseModel = new ProductResponseModel();
        CategoryResponseModel categoryResponseModel = new CategoryResponseModel();
        productResponseModel.setId(product.getId());
        productResponseModel.setName(product.getName());
        productResponseModel.setPrice(product.getPrice());
        categoryResponseModel.setCategoryType(product.getCategory().getType());
        categoryResponseModel.setId(product.getCategory().getId());
        productResponseModel.setCategory(categoryResponseModel);
        return productResponseModel;
    }
    //endregion
}
