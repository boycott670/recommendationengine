package com.sqli.challenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
	
	public double expectedRating(final String reviewer, final Product product, final SimilarityMetric similarityMetric)
	{
    final Set<String> otherReviewers = reviewers.keySet()
        .stream()
        .filter(otherReviewer -> !Objects.equals(otherReviewer, reviewer))
        .filter(otherReviewer -> reviewers.get(otherReviewer).getReviews().containsKey(product))
        .collect(Collectors.toSet());
    
    final double sumOfSimilaritiesMultipliedByScores = otherReviewers.stream()
        .mapToDouble(otherReviewer -> similarityOf(reviewer, otherReviewer, similarityMetric) * reviewScore(otherReviewer, product))
        .sum();
    
    final double sumOfSimilarities = otherReviewers.stream()
        .mapToDouble(otherReviewer -> similarityOf(reviewer, otherReviewer, similarityMetric))
        .sum();
    
    return sumOfSimilaritiesMultipliedByScores / sumOfSimilarities;
	}
}
