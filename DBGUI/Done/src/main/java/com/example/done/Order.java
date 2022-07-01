package com.example.done;

public class Order
{


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order(String name, int amount, int id) {
        this.name = name;
        this.amount = amount;
        this.id = id;
    }

    public String name;
    public int amount;
    public int id;
}
