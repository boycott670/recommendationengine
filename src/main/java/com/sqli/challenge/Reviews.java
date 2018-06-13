package com.sqli.challenge;

import com.sqli.challenge.model.Person;
import com.sqli.challenge.model.Product;
import com.sqli.challenge.model.Review;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class Reviews {
    Collection<Review> reviews = new ArrayList<>();

    public Collection<Review> reviewsFor(Person person) {
        return reviews.stream().filter(review -> review.hasReviewer(person)).collect(Collectors.toList());
    }

    public void addReview(Review review){
        reviews.add(review);
    }
    public Double reviewScore(Person person, Product product){
        if(!hasReviewer(person)) throw new RecommendationFacadeException("User not found.");
        for(Review review: reviews){
            if(review.match(person, product)) return review.getScore();
        }
        throw new RecommendationFacadeException("Product not found.");
    }


    private boolean hasReviewer(Person person) {
       return reviews.stream().anyMatch(review -> review.hasReviewer(person));
    }
}
