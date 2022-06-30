package com.example.done;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Products {
    public SimpleStringProperty product_name;
    public SimpleIntegerProperty price;
    public SimpleIntegerProperty remaining;
    public SimpleIntegerProperty sold;
    public SimpleIntegerProperty income;


    public Products(String product_name, int price, int remaining, int sold, int income) {
        this.product_name = new SimpleStringProperty(product_name);
        this.price = new SimpleIntegerProperty(price);
        this.remaining = new SimpleIntegerProperty(remaining);
        this.sold = new SimpleIntegerProperty(sold);
        this.income = new SimpleIntegerProperty(income);
    }
    public Products() {

    }


    public String getProduct_name() {
        return product_name.get();
    }

    public Integer getPrice() {
        return price.get();
    }

    public Integer getRemaining() {
        return remaining.get();
    }

    public Integer getSold() {
        return sold.get();
    }

    public Integer getIncome() {
        return income.get();
    }

    public void setProduct_name(String product_name) {
        this.product_name = new SimpleStringProperty(product_name);
    }

    public void setPrice(Integer price) {
        this.price = new SimpleIntegerProperty(price);
    }

    public void setRemaining(Integer remaining) {
        this.remaining = new SimpleIntegerProperty(remaining);
    }

    public void setSold(Integer sold) {
        this.sold = new SimpleIntegerProperty(sold);
    }

    public void setIncome(Integer income) {
        this.income = new SimpleIntegerProperty(income);
    }
}
