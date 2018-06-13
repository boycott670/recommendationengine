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
        Collection<ReviewPair> sharedReviews =sharedReviews(reviewsFstPerson, reviewsSndPerson);
        return Math.sqrt(sharedReviews.stream().mapToDouble(reviewPair -> Math.pow(reviewPair.fstReview.getScore()-reviewPair.sndReview.getScore(),2)).sum());
    }

}
