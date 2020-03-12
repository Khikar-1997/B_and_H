package com.example.demo.rest.controller;

import com.example.demo.rest.model.category.CategoryRequestModel;
import com.example.demo.rest.model.category.CategoryResponseModel;
import com.example.demo.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
public class CategoryController implements Serializable {
    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    private static final long serialVersionUID = 7923271946960072682L;
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/category")
    public ResponseEntity create(@RequestBody CategoryRequestModel category){
        LOGGER.info("Creating category - {}", category);
        CategoryResponseModel savedCategory = categoryService.create(category);
        LOGGER.info("Successfully created - {}",savedCategory);
        return ResponseEntity.ok(savedCategory);
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<CategoryResponseModel>> selectAllCategories(){
        LOGGER.info("Select all categories");
        List<CategoryResponseModel> categories = categoryService.selectAllCategories();
        LOGGER.info("Successfully selected all categories- {}",categories);
        return ResponseEntity.ok(categories);
    }

    @GetMapping(value = "/category/{id}")
    public ResponseEntity<CategoryResponseModel> findCategoryById(@PathVariable Long id){
        LOGGER.info("Find category by id - {}",id);
        CategoryResponseModel category = categoryService.findCategoryById(id);
        LOGGER.info("Successfully be find category by id - {}",category);
        return ResponseEntity.ok(category);
    }

    @PutMapping(value = "/category/{id}")
    public ResponseEntity update(@PathVariable Long id,@RequestBody CategoryRequestModel category){
        LOGGER.info("Updating category by id - {}",id);
        CategoryResponseModel updateCategory = categoryService.update(id,category);
        LOGGER.info("Successfully updated category by id - {}",updateCategory);
        return ResponseEntity.ok(updateCategory);
    }

    @DeleteMapping(value = "/category/{id}")
    public void delete(@PathVariable Long id){
        LOGGER.info("Deleting category by id - {}",id);
        categoryService.delete(id);
        LOGGER.info("Successfully deleted");
    }

}
