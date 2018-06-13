package com.sqli.challenge.similarity;

public class SimilarityMetricFactory {
    public static SimilarityMetric create(String similarityMetric){
        switch (similarityMetric.toLowerCase()){
            case "euclidian":
                return EuclidianSimilarityMetric.getInstance();
            default:
                throw new RuntimeException("Unknown metric!");
        }
    }
}
