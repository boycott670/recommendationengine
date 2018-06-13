package com.sqli.challenge;

import com.sqli.challenge.model.Person;
import com.sqli.challenge.model.Product;
import com.sqli.challenge.model.Review;
import com.sqli.challenge.similarity.SimilarityMetric;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    public Collection<Person> whoReviewed(Product product){
        return reviews.stream().filter(review -> review.hasProduct(product)).map(Review::getPerson).collect(Collectors.toList());
    }
    public double expectedRating(SimilarityMetric similarityMetric, Person person, Product product) {
        Collection<Person>  otherPersons = whoReviewed(product);
        otherPersons.removeIf(otherPerson -> person.equals(otherPerson));
        Collection<Review>  reviewsForCurrentPerson = reviewsFor(person);
        List<Double> similarities = new ArrayList<>();
        List<Double> ratings = new ArrayList<>();
        for(Person other: otherPersons){
            Collection<Review>  reviewsForOther = reviewsFor(other);
            similarities.add(similarityMetric.score(reviewsForCurrentPerson, reviewsForOther));
            ratings.add(reviewsForOther.stream().filter(review -> review.hasProduct(product)).findFirst().map(Review::getScore).get());
        }
        return expectedRating(similarities, ratings);
    }

    private double expectedRating(List<Double> similarities, List<Double> ratings) {
        double sumOfWeightedSimilarities = 0;
        double totalSumOfSimilarities = 0;
        for(int i=0; i<similarities.size(); i++){
            sumOfWeightedSimilarities+=similarities.get(i)*ratings.get(i);
            totalSumOfSimilarities+=similarities.get(i);
        }
        return sumOfWeightedSimilarities/totalSumOfSimilarities;
    }

    private boolean hasReviewer(Person person) {
       return reviews.stream().anyMatch(review -> review.hasReviewer(person));
    }
}
