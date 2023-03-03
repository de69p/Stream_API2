package org.example;

import java.time.LocalDate;

public class Product {
    public Type type;
    public double price;
    public boolean hasDiscount;
    public LocalDate dateAdded;

    public Product(Type type, double price, boolean hasDiscount, LocalDate dateAdded) {
        this.type = type;
        this.price = price;
        this.hasDiscount = hasDiscount;
        this.dateAdded = dateAdded;
    }

    public Type getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean hasDiscount() {
        return hasDiscount;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public double getDiscountedPrice() {
        return hasDiscount ? price * 0.9 : price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "type=" + type +
                ", price=" + price +
                ", hasDiscount=" + hasDiscount +
                ", dateAdded=" + dateAdded +
                '}';
    }
}

