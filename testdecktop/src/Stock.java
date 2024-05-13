//package com.example.loginsystem;

public class Stock {
    private String name;
    private double price;
    private String acronym;
    private boolean stockAvailability;
    private int quantity;
    public Stock(String name, double price, String acronym, boolean stockAvailability, int quantity) {
        this.name = name;
        this.price = price;
        this.acronym = acronym;
        this.stockAvailability = stockAvailability;
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStockAvailability() {
        return stockAvailability;
    }

    public void setStockAvailability(boolean stockAvailability) {
        this.stockAvailability = stockAvailability;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

