package com.sqli.challenge.similarity;

import com.sqli.challenge.model.Review;

import java.util.Collection;

public interface SimilarityMetric {
    double score(Collection<Review> reviewsFstPerson, Collection<Review> reviewsSndPerson);
}
