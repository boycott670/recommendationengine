package com.sqli.challenge.similarities;

import java.util.Map;

import com.sqli.challenge.Product;
import com.sqli.challenge.Reviewer;

final class EuclideanSimilarityMetric extends SimilarityMetric
{
  
  @Override
  public double similarity(final Reviewer firstReviewer, final Reviewer secondReviewer)
  {
    final Map<Product, Double> firstReviewerReviews = firstReviewer.getReviews();
    
    final Map<Product, Double> secondReviewerReviews = secondReviewer.getReviews();
    
    final double sumOfSimilarities = sharedProducts(firstReviewerReviews, secondReviewerReviews)
      .stream()
      .mapToDouble(sharedProduct -> firstReviewerReviews.get(sharedProduct) - secondReviewerReviews.get(sharedProduct))
      .map(similarity -> Math.pow(similarity, 2))
      .sum();
    
    return Math.sqrt(sumOfSimilarities);
  }

}
