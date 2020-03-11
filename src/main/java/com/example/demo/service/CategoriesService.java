package com.example.demo.service;

import com.example.demo.persistance.model.Categories;
import com.example.demo.persistance.repository.CategoriesRepository;
import com.example.demo.rest.model.category.CategoriesRequestModel;
import com.example.demo.rest.model.category.CategoriesResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesService implements Serializable {
    private final static Logger LOGGER = LoggerFactory.getLogger(CategoriesService.class);
    private static final long serialVersionUID = -7020976505717817897L;
    private final CategoriesRepository categoriesRepository;

    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public CategoriesResponseModel create(CategoriesRequestModel categoriesRequestModel){
        LOGGER.info("Request to create category - {}",categoriesRequestModel);
        Categories categories = buildCotegoryFrom(categoriesRequestModel);
        LOGGER.info("Successfully created category - {}",categoriesRequestModel);
        return buildCategoryResponseModelFrom(categoriesRepository.save(categories));
    }

    public List<CategoriesResponseModel> selectAllCategories(){
        LOGGER.info("Geting all categories");
        List<Categories> categories = categoriesRepository.findAll();
        LOGGER.info("Successfully selected all categories");
        return categories.stream()
                .map(this::buildCategoryResponseModelFrom).collect(Collectors.toList());
    }

    public CategoriesResponseModel findCategoryById(Long id){
        LOGGER.info("Geting category by id - {}",id);
        Categories category = categoriesRepository.findById(id).get();
        return buildCategoryResponseModelFrom(category);
    }

    public CategoriesResponseModel update(Long id,CategoriesRequestModel categoriesRequestModel){
        Categories categories = categoriesRepository.findById(id).get();
        categories.setType(categoriesRequestModel.getType());
        return buildCategoryResponseModelFrom(categoriesRepository.save(buildCotegoryFrom(categoriesRequestModel)));
    }

    public void delete(Long id){
        categoriesRepository.deleteById(id);
    }

    //region Private Build Methods
    private Categories buildCotegoryFrom(CategoriesRequestModel categoriesRequestModel){
        Categories categories = new Categories();
        categories.setType(categoriesRequestModel.getType());
        return categories;
    }

    private CategoriesResponseModel buildCategoryResponseModelFrom(Categories categories){
        CategoriesResponseModel categoriesResponseModel = new CategoriesResponseModel();
        categoriesResponseModel.setId(categories.getId());
        categoriesResponseModel.setCategoriesType(categories.getType());
        return categoriesResponseModel;
    }
    //endregion
}
