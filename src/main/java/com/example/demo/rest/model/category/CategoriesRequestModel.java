package com.example.demo.rest.model.category;

import com.example.demo.persistance.model.CategoriesType;

import java.util.Objects;

public class CategoriesRequestModel {
    private CategoriesType type;

    public CategoriesRequestModel(CategoriesType type) {
        this.type = type;
    }

    public CategoriesRequestModel() {
    }

    public CategoriesType getType() {
        return type;
    }

    public void setType(CategoriesType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriesRequestModel that = (CategoriesRequestModel) o;
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
