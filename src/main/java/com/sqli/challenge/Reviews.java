package com.sqli.challenge;

import java.util.ArrayList;
import java.util.Collection;

public class Reviews {
    Collection<Review> reviews = new ArrayList<>();
    public void addReview(Review review){
        reviews.add(review);
    }
    public Double reviewScore(Person person, Product product){
        for(Review review: reviews){
            if(review.match(person, product)) return review.getScore();
        }
        throw new RuntimeException("No rating found");
    }
}
