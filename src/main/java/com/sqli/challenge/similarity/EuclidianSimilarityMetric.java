package com.sqli.challenge.similarity;

import com.sqli.challenge.model.Review;

import java.util.ArrayList;
import java.util.Collection;

class EuclidianSimilarityMetric implements SimilarityMetric{
    private static final EuclidianSimilarityMetric instance = new EuclidianSimilarityMetric();

    private EuclidianSimilarityMetric(){

    }
    public static SimilarityMetric getInstance() {
        return instance;
    }

    @Override
    public double score(Collection<Review> reviewsFstPerson, Collection<Review> reviewsSndPerson) {
        Collection<ReviewPair> reviewPairs = new ArrayList<>();
        for(Review fstReview: reviewsFstPerson){
            for(Review sndReview: reviewsSndPerson){
                if(fstReview.hasSameProduct(sndReview)){
                    reviewPairs.add(new ReviewPair(fstReview, sndReview));
                }
            }
        }
        return Math.sqrt(reviewPairs.stream().mapToDouble(reviewPair -> Math.pow(reviewPair.fstReview.getScore()-reviewPair.sndReview.getScore(),2)).sum());
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
