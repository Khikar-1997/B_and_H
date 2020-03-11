package com.example.demo.rest.controller;

import com.example.demo.persistance.model.Categories;
import com.example.demo.rest.model.category.CategoriesRequestModel;
import com.example.demo.rest.model.category.CategoriesResponseModel;
import com.example.demo.service.CategoriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
public class CategoriesController implements Serializable {
    private final static Logger LOGGER = LoggerFactory.getLogger(CategoriesController.class);

    private static final long serialVersionUID = 7923271946960072682L;
    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping(value = "/category")
    public ResponseEntity create(@RequestBody CategoriesRequestModel category){
        LOGGER.info("Creating category - {}", category);
        CategoriesResponseModel savedCategory = categoriesService.create(category);
        LOGGER.info("Successfully created - {}",savedCategory);
        return ResponseEntity.ok(savedCategory);
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<CategoriesResponseModel>> selectAllCategories(){
        LOGGER.info("Select all categories");
        List<CategoriesResponseModel> categories = categoriesService.selectAllCategories();
        LOGGER.info("Successfully selected all categories- {}",categories);
        return ResponseEntity.ok(categories);
    }

    @GetMapping(value = "/category/{id}")
    public ResponseEntity<CategoriesResponseModel> findCategoryById(@PathVariable Long id){
        LOGGER.info("Find category by id - {}",id);
        CategoriesResponseModel category = categoriesService.findCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @PutMapping(value = "/category/{id}")
    public ResponseEntity update(@PathVariable Long id,@RequestBody CategoriesRequestModel category){
        CategoriesResponseModel updateCategory = categoriesService.update(id,category);
        return ResponseEntity.ok(updateCategory);
    }

    @DeleteMapping(value = "/category/{id}")
    public void delete(@PathVariable Long id){
        categoriesService.delete(id);
    }

}
