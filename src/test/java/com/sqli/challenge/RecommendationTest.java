package com.sqli.challenge;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RecommendationTest
{
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void getUserReviewForASpecificProduct()
	{
		RecommendationFacade recommendationFacade = new RecommendationFacade();
		recommendationFacade.addReview("user1", "product1", 3.5);
		recommendationFacade.addReview("user1", "product2", 2.5);
		recommendationFacade.addReview("user1", "product3", 5);
		Double score = recommendationFacade.reviewScore("user1", "product2");

		assertEquals(score.doubleValue(), 2.5, 0.0001);
	}

	@Test
	public void userNotFound()
	{
		thrown.expect(RecommendationFacadeException.class);
		thrown.expectMessage("User not found.");
		RecommendationFacade recommendationFacade = new RecommendationFacade();
		recommendationFacade.addReview("user1", "product1", 3.5);
		recommendationFacade.addReview("user1", "product2", 2.5);
		recommendationFacade.addReview("user1", "product3", 5);
		recommendationFacade.reviewScore("user2", "product2");
	}

	@Test
	public void productNotFound()
	{
		thrown.expect(RecommendationFacadeException.class);
		thrown.expectMessage("Product not found.");
		RecommendationFacade recommendationFacade = new RecommendationFacade();
		recommendationFacade.addReview("user1", "product1", 3.5);
		recommendationFacade.addReview("user1", "product2", 2.5);
		recommendationFacade.addReview("user1", "product3", 5);
		recommendationFacade.reviewScore("user1", "product5");
	}

	@Test
	public void similarityUsingEuclideanDistance()
	{
		RecommendationFacade recommendationFacade = new RecommendationFacade();
		recommendationFacade.addReview("user1", "product1", 3.5);
		recommendationFacade.addReview("user1", "product2", 2.5);
		recommendationFacade.addReview("user1", "product3", 5);
		recommendationFacade.addReview("user2", "product5", 4.5);
		recommendationFacade.addReview("user2", "product2", 3.5);
		recommendationFacade.addReview("user2", "product1", 2);
		recommendationFacade.setSimilarityMetric("euclidian");
		// Find the items rated by both users, for example (i1,i2, ...,in); the
		// similarity using euclidian metric is as follow:
		// sqrt(sum(item -> (user1Rating(item)-user2Rating(item))^2)
		double similarity = recommendationFacade.similarity("user1", "user2");
		assertEquals(similarity, 1.802, 0.001);
	}

	@Test
	public void similarityUsingJaccardIndex()
	{
		RecommendationFacade recommendationFacade = new RecommendationFacade();
		recommendationFacade.addReview("user1", "product1", 3.5);
		recommendationFacade.addReview("user1", "product2", 2.5);
		recommendationFacade.addReview("user1", "product3", 5);
		recommendationFacade.addReview("user1", "product4", 3.8);
		recommendationFacade.addReview("user1", "product5", 2.6);
		recommendationFacade.addReview("user1", "product6", 2.6);
		recommendationFacade.addReview("user1", "product7", 2.6);
		recommendationFacade.addReview("user2", "product5", 4.5);
		recommendationFacade.addReview("user2", "product2", 3.5);
		recommendationFacade.addReview("user2", "product1", 2);
		recommendationFacade.addReview("user2", "product3", 4);
		recommendationFacade.addReview("user2", "product4", 4);
		recommendationFacade.addReview("user2", "product6", 4);
		recommendationFacade.addReview("user2", "product11", 4.2);
		recommendationFacade.addReview("user2", "product12", 2.2);
		recommendationFacade.setSimilarityMetric("jaccardIndex");
		// The similarity using JaccardIndex metric is as follow:
		// numberOfItemsSharedBetweenTheTwoUsers/numberOfUnionOfItemsBetweenTheTwoUsers
		double similarity = recommendationFacade.similarity("user1", "user2");
		assertEquals(similarity, 0.075, 0.001);
	}

	@Test
	public void expectedRatingUsingJaccardIndex()
	{
		RecommendationFacade recommendationFacade = new RecommendationFacade();
		recommendationFacade.addReview("user1", "product1", 3.5);
		recommendationFacade.addReview("user1", "product2", 2.5);
		recommendationFacade.addReview("user1", "product3", 5);
		recommendationFacade.addReview("user1", "product4", 3.8);
		recommendationFacade.addReview("user1", "product5", 2.6);
		recommendationFacade.addReview("user1", "product6", 2.6);
		recommendationFacade.addReview("user1", "product7", 2.6);
		recommendationFacade.addReview("user2", "product5", 4.5);
		recommendationFacade.addReview("user2", "product2", 3.5);
		recommendationFacade.addReview("user2", "product1", 2);
		recommendationFacade.addReview("user2", "product3", 4);
		recommendationFacade.addReview("user2", "product4", 4);
		recommendationFacade.addReview("user2", "product6", 4);
		recommendationFacade.addReview("user2", "product11", 4.2);
		recommendationFacade.addReview("user2", "product12", 2.2);
		recommendationFacade.addReview("user3", "product5", 3.7);
		recommendationFacade.addReview("user3", "product2", 2.2);
		recommendationFacade.addReview("user3", "product1", 5);
		recommendationFacade.addReview("user3", "product3", 3.8);
		recommendationFacade.addReview("user3", "product4", 4.2);
		recommendationFacade.addReview("user3", "product6", 4.9);
		recommendationFacade.addReview("user3", "product11", 4.7);
		recommendationFacade.addReview("user3", "product12", 3.2);
		recommendationFacade.setSimilarityMetric("jaccardIndex");
		// To calculate the expected rating for a product not yet rated by a user A, you
		// have to find all users different from A who have rated this product
		// for each one you have to calculate its similarity with the user A, then the
		// expected rating is equal to:
		// sumForAllUserOtherThanAOf(similarityToA*ratingOfThisUser(product))/sumOf(similarities
		// of those users to A)
		double expectedRating = recommendationFacade.expectedRating("user1", "product11");
		assertEquals(expectedRating, 2.35, 0.01);
	}
}
