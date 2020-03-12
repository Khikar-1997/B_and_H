package com.example.demo.rest.model.category;

import com.example.demo.persistance.model.CategoryType;

import java.util.Objects;

public class CategoryResponseModel {
    private Long id;
    private CategoryType categoryType;

    public CategoryResponseModel(Long id, CategoryType categoryType) {
        this.id = id;
        this.categoryType = categoryType;
    }

    public CategoryResponseModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryResponseModel that = (CategoryResponseModel) o;
        return Objects.equals(id, that.id) &&
                categoryType == that.categoryType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryType);
    }

    @Override
    public String toString() {
        return "CategoriesResponseModel{" +
                "id=" + id +
                ", categoriesType=" + categoryType +
                '}';
    }
}
