// SatisfactionScore.java - represents satisfaction breakdown for a product
package com.dashboard.data;

import java.util.Arrays;

public class SatisfactionScore {
    private String product;
    private double[] scores;

    public SatisfactionScore(String product, double[] scores) {
        this.product = product;
        this.scores = scores;
    }

    public String getProduct() {
        return product;
    }

    public double[] getScores() {
        return scores;
    }

    public double getAverageScore() {
        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        return sum / scores.length;
    }

    public double getScoreVariance() {
        double average = getAverageScore();
        double sum = 0;
        for (double score : scores) {
            sum += Math.pow(score - average, 2);
        }
        return sum / scores.length;
    }

    public double getScoreStandardDeviation() {
        return Math.sqrt(getScoreVariance());
    }

    @Override
    public String toString() {
        return "SatisfactionScore{" +
                "product='" + product + '\'' +
                ", scores=" + Arrays.toString(scores) +
                '}';
    }

}
