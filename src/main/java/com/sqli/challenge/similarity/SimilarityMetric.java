package com.sqli.challenge.similarity;

import com.sqli.challenge.model.Review;

import java.util.ArrayList;
import java.util.Collection;

public interface SimilarityMetric {
    double score(Collection<Review> reviewsFstPerson, Collection<Review> reviewsSndPerson);

    default Collection<ReviewPair> sharedReviews(Collection<Review> reviewsFstPerson, Collection<Review> reviewsSndPerson){
        Collection<ReviewPair> reviewPairs = new ArrayList<>();
        for(Review fstReview: reviewsFstPerson){
            for(Review sndReview: reviewsSndPerson){
                if(fstReview.hasSameProduct(sndReview)){
                    reviewPairs.add(new ReviewPair(fstReview, sndReview));
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
