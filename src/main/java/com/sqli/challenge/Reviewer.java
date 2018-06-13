package com.sqli.challenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class Reviewer
{
	private final String name;

	private final Map<Product, Double> reviews;

	public Reviewer(String name)
	{
		this.name = name;
		reviews = new HashMap<>();
	}
	
	public void addReview(final Product product, final double score)
	{
		reviews.put(product, score);
	}
	
	public Double reviewScore(final Product product)
	{
		return Optional.ofNullable(reviews.get(product)).orElseThrow(() -> new RecommendationFacadeException("Product not found."));
	}
}
