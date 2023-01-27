package com.bezkoder.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")

public class Books {

    @Id
    private String id;

    private String price;
    private String author;
    private String editorial;
    private String name;
    private String year_publishied;
    private String brand;
    private String model;

    public Books(String price, String author, String editorial, String name, String year_publishied, String brand,
            String model) {
        this.price = price;
        this.author = author;
        this.editorial = editorial;
        this.name = name;
        this.year_publishied = year_publishied;
        this.brand = brand;
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getName() {
        return name;
    }

    public String getYearPublishied() {
        return year_publishied;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year_publishied = year;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "|| INFO : => [ price = " + price + ", author = " + author + ", editorial = " + editorial + ", name = "
                + name + ", year_publishied = " + year_publishied + ", brand = " + brand + ", model = " + model
                + "] <= ||";
    }
}
