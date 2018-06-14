package com.sqli.challenge.similarities;

import com.sqli.challenge.RecommendationFacadeException;

public final class SimilarityMetricFactory
{
  public static SimilarityMetric get(final String similarityMetric)
  {
    switch (similarityMetric)
    {
      case "euclidian":
        return new EuclideanSimilarityMetric();
  
      case "jaccardIndex":
        return new JaccardIndexSimilarityMetric();
  
      default:
        throw new RecommendationFacadeException("Unknown similarity metric");
    }
  }
}
