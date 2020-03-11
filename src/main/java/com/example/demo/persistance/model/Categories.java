package com.example.demo.persistance.model;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Categories extends AbstractBaseEntity {
    private CategoriesType type;

    public Categories(CategoriesType type) {
        this.type = type;
    }

    public Categories() {
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
        if (!super.equals(o)) return false;
        Categories that = (Categories) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public String toString() {
        return "Categories{" +
                "type=" + type +
                '}';
    }
}
