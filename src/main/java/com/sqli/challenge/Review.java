package com.sqli.challenge;

public class Review {
    private Person person;
    private Product product;
    private double score;
    public Review(Person person, Product product, double score){
        this.person = person;
        this.product = product;
        this.score = score;
    }

    public boolean match(Person person, Product product) {
        return this.person.equals(person) && this.product.equals(product);
    }

    public Double getScore() {
        return score;
    }
}
