package com.sqli.challenge;

public class RecommendationFacade {
    private Reviews reviews = new Reviews();
    public void addReview(String userName, String productName, double score) {

        reviews.addReview(new Review(new Person(userName), new Product(productName), score));
    }

    public Double reviewScore(String userName, String productName) {
        return reviews.reviewScore(new Person(userName), new Product(productName));
    }

    public double similarity(String user1, String user2) {
        throw  new RuntimeException("ToBiImplemented");
    }

    public void setSimilarityMetric(String similarityMetric) {
        throw  new RuntimeException("ToBiImplemented");
    }

    public double expectedRating(String user, String product) {
        throw  new RuntimeException("ToBiImplemented");
    }
}
