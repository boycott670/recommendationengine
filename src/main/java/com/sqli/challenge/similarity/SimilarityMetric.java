package com.sqli.challenge.similarity;

import java.util.ArrayList;
import java.util.Collection;

import com.sqli.challenge.model.Review;

public interface SimilarityMetric {
    double score(Collection<Review> reviewsFstPerson, Collection<Review> reviewsSndPerson);

    default Collection<ReviewPair> sharedReviews(Collection<Review> reviewsFstPerson, Collection<Review> reviewsSndPerson){
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

    class ReviewPair{
        Review fstReview;
        Review sndReview;
        private ReviewPair(Review fstReview, Review sndReview){
            this.fstReview = fstReview;
            this.sndReview = sndReview;
        }
    }
}
