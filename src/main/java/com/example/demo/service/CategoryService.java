package com.example.demo.service;

import com.example.demo.persistance.model.Category;
import com.example.demo.persistance.repository.CategoryRepository;
import com.example.demo.rest.model.category.CategoryRequestModel;
import com.example.demo.rest.model.category.CategoryResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService implements Serializable {
    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);
    private static final long serialVersionUID = -7020976505717817897L;
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponseModel create(CategoryRequestModel categoryRequestModel){
        LOGGER.info("Request to create category - {}", categoryRequestModel);
        Category category = buildCotegoryFrom(categoryRequestModel);
        Category saveCategory = categoryRepository.save(category);
        LOGGER.info("Successfully created category - {}",saveCategory);
        return buildCategoryResponseModelFrom(saveCategory);
    }

    public List<CategoryResponseModel> selectAllCategories(){
        LOGGER.info("Geting all categories");
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseModel> collect = categories.stream()
                .map(this::buildCategoryResponseModelFrom).collect(Collectors.toList());
        LOGGER.info("Successfully selected all categories - {}",collect);
        return collect;
    }

    public CategoryResponseModel findCategoryById(Long id){
        LOGGER.info("Geting category by id - {}",id);
        Category category = categoryRepository.findById(id).get();
        LOGGER.info("Successfully be find category by id - {}",category);
        return buildCategoryResponseModelFrom(category);
    }

    public CategoryResponseModel update(Long id, CategoryRequestModel categoryRequestModel){
        LOGGER.info("Updating category by id - {}",id);
        Category category = categoryRepository.findById(id).get();
        category.setType(categoryRequestModel.getType());
        Category updateCategory = categoryRepository.save(category);
        CategoryResponseModel categoryResponseModel = buildCategoryResponseModelFrom(updateCategory);
        LOGGER.info("Successfully updated category by id - {}", categoryResponseModel);
        return categoryResponseModel;
    }

    public void delete(Long id){
        LOGGER.info("Deleting category by id - {}",id);
        categoryRepository.deleteById(id);
        LOGGER.info("Successfully deleted");
    }

    //region Private Build Methods
    private Category buildCotegoryFrom(CategoryRequestModel categoryRequestModel){
        Category category = new Category();
        category.setType(categoryRequestModel.getType());
        return category;
    }

    private CategoryResponseModel buildCategoryResponseModelFrom(Category category){
        CategoryResponseModel categoryResponseModel = new CategoryResponseModel();
        categoryResponseModel.setId(category.getId());
        categoryResponseModel.setCategoryType(category.getType());
        return categoryResponseModel;
    }
    //endregion
}
