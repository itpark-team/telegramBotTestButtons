package com.myapp.Db;

public class Phone {
    private String model;
    private int price;
    private int quantity;

    public Phone(String model, int price, int quantity) {
        this.model = model;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "model='" + model + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public String getModel() {
        return model;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
