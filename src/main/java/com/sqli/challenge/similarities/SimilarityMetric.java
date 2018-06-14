package com.sqli.challenge.similarities;

import com.sqli.challenge.Reviewer;

public interface SimilarityMetric
{
  double similarity(final Reviewer firstReviewer, final Reviewer secondReviewer);
}
