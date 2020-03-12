package com.example.demo.rest.model.category;

import com.example.demo.persistance.model.CategoryType;

import java.util.Objects;

public class CategoryRequestModel {
    private CategoryType type;

    public CategoryRequestModel(CategoryType type) {
        this.type = type;
    }

    public CategoryRequestModel() {
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryRequestModel that = (CategoryRequestModel) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "CategoriesRequestModel{" +
                "type=" + type +
                '}';
    }
}
