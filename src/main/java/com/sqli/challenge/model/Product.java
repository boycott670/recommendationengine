package com.sqli.challenge.model;

public class Product {
    private String name;
    public Product(String name){
        this.name = name;
    }
    @Override
    public boolean equals(Object product){
        if(!(product instanceof  Product)) return false;
        return name.equals(((Product)product).name);
    }
}
