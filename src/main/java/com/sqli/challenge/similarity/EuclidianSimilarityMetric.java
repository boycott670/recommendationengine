package com.sqli.challenge.similarity;

import java.util.Collection;

import com.sqli.challenge.model.Review;

class EuclidianSimilarityMetric implements SimilarityMetric{
    private static final EuclidianSimilarityMetric instance = new EuclidianSimilarityMetric();
    private EuclidianSimilarityMetric(){

    }
    public static SimilarityMetric getInstance() {
        return instance;
    }

    @Override
    public double score(Collection<Review> reviewsFstPerson, Collection<Review> reviewsSndPerson) {

      
      final Collection<ReviewPair> sharedReviews = sharedReviews(reviewsFstPerson, reviewsSndPerson);
      
      final double sumOfSimilarities = sharedReviews.stream().mapToDouble(reviewPair -> Math.pow(reviewPair.fstReview.getScore()-reviewPair.sndReview.getScore(),2)).sum();
      
      return Math.sqrt(sumOfSimilarities);
      
  }

}
