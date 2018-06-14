package com.sqli.challenge.similarities;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sqli.challenge.Product;
import com.sqli.challenge.Reviewer;

final class EuclideanSimilarityMetric implements SimilarityMetric
{
  
  private Set<Product> sharedProducts(Map<Product, Double> firstReviewerReviews, Map<Product, Double> secondReviewerReviews)
  {
    final Set<Product> sharedProducts = new HashSet<>(firstReviewerReviews.keySet());
    
    sharedProducts.retainAll(secondReviewerReviews.keySet());
    
    return sharedProducts;
  }

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
