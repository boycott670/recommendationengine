package com.sqli.challenge.similarity;

import java.util.ArrayList;
import java.util.Collection;

import com.sqli.challenge.model.Review;

class EuclidianSimilarityMetric implements SimilarityMetric{
    private static final EuclidianSimilarityMetric instance = new EuclidianSimilarityMetric();

    private EuclidianSimilarityMetric(){

    }
    public static SimilarityMetric getInstance() {
        return instance;
    }
    
    private Collection<ReviewPair> sharedReviews(Collection<Review> reviewsFstPerson, Collection<Review> reviewsSndPerson)
    {
      Collection<ReviewPair> reviewPairs = new ArrayList<>();
      
      firstPersonReviews:
      for(Review fstReview: reviewsFstPerson){
        secondPersonReviews:
          for(Review sndReview: reviewsSndPerson){
              if(fstReview.hasSameProduct(sndReview)){
                  reviewPairs.add(new ReviewPair(fstReview, sndReview));
                  continue firstPersonReviews;
              }
          }
      }
      
      return reviewPairs;
    }

    @Override
    public double score(Collection<Review> reviewsFstPerson, Collection<Review> reviewsSndPerson) {
      
        final Collection<ReviewPair> sharedReviews = sharedReviews(reviewsFstPerson, reviewsSndPerson);
        
        final double sumOfSimilarities = sharedReviews.stream().mapToDouble(reviewPair -> Math.pow(reviewPair.fstReview.getScore()-reviewPair.sndReview.getScore(),2)).sum();
        
        return Math.sqrt(sumOfSimilarities);
        
    }
    private class ReviewPair{
        Review fstReview;
        Review sndReview;
        private ReviewPair(Review fstReview, Review sndReview){
            this.fstReview = fstReview;
            this.sndReview = sndReview;
        }
    }
}
