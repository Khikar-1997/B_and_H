package com.example.demo.rest.model.category;

import com.example.demo.persistance.model.CategoriesType;

import java.util.Objects;

public class CategoriesResponseModel {
    private Long id;
    private CategoriesType categoriesType;

    public CategoriesResponseModel(Long id, CategoriesType categoriesType) {
        this.id = id;
        this.categoriesType = categoriesType;
    }

    public CategoriesResponseModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoriesType getCategoriesType() {
        return categoriesType;
    }

    public void setCategoriesType(CategoriesType categoriesType) {
        this.categoriesType = categoriesType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriesResponseModel that = (CategoriesResponseModel) o;
        return Objects.equals(id, that.id) &&
                categoriesType == that.categoriesType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoriesType);
    }

    @Override
    public String toString() {
        return "CategoriesResponseModel{" +
                "id=" + id +
                ", categoriesType=" + categoriesType +
                '}';
    }
}
