package com.example.demo.persistance.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Entity
public class Category extends AbstractBaseEntity {
    @Enumerated(EnumType.STRING)
    private CategoryType type;

    public Category(CategoryType type) {
        this.type = type;
    }

    public Category() {
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
        if (!super.equals(o)) return false;
        Category that = (Category) o;
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
