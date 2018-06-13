package com.sqli.challenge.similarity;

import com.sqli.challenge.model.Review;

import java.util.Collection;

class JaccardIndexSimilarityMetric implements SimilarityMetric{
    private static final JaccardIndexSimilarityMetric instance = new JaccardIndexSimilarityMetric();
    private JaccardIndexSimilarityMetric(){

    }
    public static SimilarityMetric getInstance() {
        return instance;
    }

    @Override
    public double score(Collection<Review> reviewsFstPerson, Collection<Review> reviewsSndPerson) {
        Collection<ReviewPair> sharedReviews =sharedReviews(reviewsFstPerson, reviewsSndPerson);
        return (sharedReviews.size()*1.0)/(reviewsFstPerson.size()+reviewsSndPerson.size()-sharedReviews.size());
    }
}
