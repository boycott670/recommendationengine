package com.sqli.challenge.similarities;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sqli.challenge.Product;
import com.sqli.challenge.Reviewer;

public abstract class SimilarityMetric
{
  final Set<Product> sharedProducts(Map<Product, Double> firstReviewerReviews, Map<Product, Double> secondReviewerReviews)
  {
    final Set<Product> sharedProducts = new HashSet<>(firstReviewerReviews.keySet());
    
    sharedProducts.retainAll(secondReviewerReviews.keySet());
    
    return sharedProducts;
  }
  
  public abstract double similarity(final Reviewer firstReviewer, final Reviewer secondReviewer);
}
