package com.sqli.challenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.sqli.challenge.similarities.SimilarityMetric;

public final class Reviewers
{
	private final Map<String, Reviewer> reviewers;

	public Reviewers()
	{
		reviewers = new HashMap<>();
	}
	
	public void addReview(final String reviewerName, final Product product, final double score)
	{
		reviewers.computeIfAbsent(reviewerName, Reviewer::new).addReview(product, score);
	}
	
	public Double reviewScore(final String reviewerName, final Product product)
	{
		return Optional.ofNullable(reviewers.get(reviewerName)).orElseThrow(() -> new RecommendationFacadeException("User not found.")).reviewScore(product);
	}
	
	public double similarityOf(final String firstReviewer, final String secondReviewer, final SimilarityMetric similarityMetric)
	{
	  return similarityMetric.similarity(reviewers.get(firstReviewer), reviewers.get(secondReviewer));
	}
}
