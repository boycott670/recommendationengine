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
  
      default:
        throw new RecommendationFacadeException("Unknown similarity metric");
    }
  }
}
