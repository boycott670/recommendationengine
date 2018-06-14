package com.sqli.challenge;

import com.sqli.challenge.similarities.SimilarityMetric;
import com.sqli.challenge.similarities.SimilarityMetricFactory;

public class RecommendationFacade
{
	private final Reviewers reviewers;
	
	private SimilarityMetric similarityMetric;

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
		return reviewers.similarityOf(user1, user2, similarityMetric);
	}

	public void setSimilarityMetric(String similarityMetric)
	{
		this.similarityMetric = SimilarityMetricFactory.get(similarityMetric);
	}

	public double expectedRating(String user, String product)
	{
		throw new RuntimeException("ToBiImplemented");
	}
}
