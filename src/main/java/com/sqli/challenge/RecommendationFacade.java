package com.sqli.challenge;

import com.sqli.challenge.model.Person;
import com.sqli.challenge.model.Product;
import com.sqli.challenge.model.Review;
import com.sqli.challenge.similarity.SimilarityMetric;
import com.sqli.challenge.similarity.SimilarityMetricFactory;

import java.util.Collection;

public class RecommendationFacade {
    private Reviews reviews = new Reviews();
    private String similarityMetric;
    public void addReview(String userName, String productName, double score) {

        reviews.addReview(new Review(new Person(userName), new Product(productName), score));
    }

    public Double reviewScore(String userName, String productName) {
        return reviews.reviewScore(new Person(userName), new Product(productName));
    }

    public double similarity(String person1, String person2) {
        SimilarityMetric similarityMetric = SimilarityMetricFactory.create(this.similarityMetric);
        java.util.Collection<Review> reviewsFstPerson = reviews.reviewsFor(new Person(person1));
        Collection<Review> reviewsSndPerson = reviews.reviewsFor(new Person(person2));
        return similarityMetric.score(reviewsFstPerson, reviewsSndPerson);
    }

    public void setSimilarityMetric(String similarityMetric) {
        this.similarityMetric = similarityMetric;
    }

    public double expectedRating(String user, String product) {
        throw  new RuntimeException("ToBiImplemented");
    }
}
