package com.sqli.challenge;

public class RecommendationFacade
{
	private final Reviewers reviewers;

	public RecommendationFacade()
	{
		reviewers = new Reviewers();
	}

	public void addReview(String user, String product, double score)
	{
		reviewers.addReview(user, new Product(product), score);
	}

	public Double reviewScore(String user, String product)
	{
		return reviewers.reviewScore(user, new Product(product));
	}

	public double similarity(String user1, String user2)
	{
		throw new RuntimeException("ToBiImplemented");
	}

	public void setSimilarityMetric(String similarityMetric)
	{
		throw new RuntimeException("ToBiImplemented");
	}

	public double expectedRating(String user, String product)
	{
		throw new RuntimeException("ToBiImplemented");
	}
}
