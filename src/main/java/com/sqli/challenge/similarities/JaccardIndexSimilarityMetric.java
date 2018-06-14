package com.sqli.challenge.similarities;

import java.util.Map;

import com.sqli.challenge.Product;
import com.sqli.challenge.Reviewer;

final class JaccardIndexSimilarityMetric extends SimilarityMetric
{

  @Override
  public double similarity(Reviewer firstReviewer, Reviewer secondReviewer)
  {
    final Map<Product, Double> firstReviewerReviews = firstReviewer.getReviews();
    
    final Map<Product, Double> secondReviewerReviews = secondReviewer.getReviews();
    
    final double sharedProductsSize = sharedProducts(firstReviewerReviews, secondReviewerReviews).size();
    
    return sharedProductsSize / (firstReviewerReviews.size() + secondReviewerReviews.size() - sharedProductsSize);
  }

}
