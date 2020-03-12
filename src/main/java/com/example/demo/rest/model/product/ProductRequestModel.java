package com.example.demo.rest.model.product;

import java.util.Objects;

public class ProductRequestModel {
    private String name;
    private int price;
    private Long categoryId;

    public ProductRequestModel(String name, int price, Long categoryId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }

    public ProductRequestModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRequestModel that = (ProductRequestModel) o;
        return price == that.price &&
                Objects.equals(name, that.name) &&
                Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, categoryId);
    }

    @Override
    public String toString() {
        return "ProductRequestModel{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                '}';
    }

}
