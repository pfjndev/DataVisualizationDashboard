package com.dashboard.data;

import java.util.Date;
import java.util.List;

/**
 * Data model for the dashboard application.
 * This class is used to store the data that will be displayed in the dashboard.
 * 
 * @version 1.0
 * @author 1705125 - PFJN
 * 
 * @param productName The name of the product
 * @param averageResponseTime The average response time for the product
 * @param customerSatisfactionScore The customer satisfaction score for the product
 * @param customerEffortScore The customer effort score for the product
 * @param netPromoterScore The net promoter score for the product
 * @param responseTimesOverMonth The response times over the month for the product
 * @param satisfactionScoresOverMonth The satisfaction scores over the month for the product
 * @param satisfactionBreakdown The satisfaction breakdown for the product
 * 
 * @return A data model object containing the product data
 * 
 * @see DataModel
 */
public class DataModel {
    
    private Date date;    
    private String productName;

    private double averageResponseTime;
    private double customerSatisfactionScore;
    private double customerEffortScore;
    private double netPromoterScore;
    
    private List<Double> responseTimesOverMonth;
    private List<Double> satisfactionScoresOverMonth;
    private List<Double> satisfactionBreakdown; // For percentage breakdowns of satisfaction scores

    // Date in format ("dd/mm/yy")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getAverageResponseTime() {
        return averageResponseTime;
    }

    public void setAverageResponseTime(double averageResponseTime) {
        this.averageResponseTime = averageResponseTime;
    }

    public double getCustomerSatisfactionScore() {
        return customerSatisfactionScore;
    }

    public void setCustomerSatisfactionScore(double customerSatisfactionScore) {
        this.customerSatisfactionScore = customerSatisfactionScore;
    }

    public double getCustomerEffortScore() {
        return customerEffortScore;
    }

    public void setCustomerEffortScore(double customerEffortScore) {
        this.customerEffortScore = customerEffortScore;
    }

    public double getNetPromoterScore() {
        return netPromoterScore;
    }

    public void setNetPromoterScore(double netPromoterScore) {
        this.netPromoterScore = netPromoterScore;
    }

    public List<Double> getResponseTimesOverMonth() {
        return responseTimesOverMonth;
    }

    public void setResponseTimesOverMonth(List<Double> responseTimesOverMonth) {
        this.responseTimesOverMonth = responseTimesOverMonth;
    }

    public List<Double> getSatisfactionScoresOverMonth() {
        return satisfactionScoresOverMonth;
    }

    public void setSatisfactionScoresOverMonth(List<Double> satisfactionScoresOverMonth) {
        this.satisfactionScoresOverMonth = satisfactionScoresOverMonth;
    }

    public List<Double> getSatisfactionBreakdown() {
        return satisfactionBreakdown;
    }

    public void setSatisfactionBreakdown(List<Double> satisfactionBreakdown) {
        this.satisfactionBreakdown = satisfactionBreakdown;
    }
    
    // toString method for debugging
    @Override
    public String toString() {
        return "DataModel{" +
                "productName='" + productName + '\'' +
                ", averageResponseTime=" + averageResponseTime +
                ", customerSatisfactionScore=" + customerSatisfactionScore +
                ", customerEffortScore=" + customerEffortScore +
                ", netPromoterScore=" + netPromoterScore +
                ", responseTimesOverMonth=" + responseTimesOverMonth +
                ", satisfactionScoresOverMonth=" + satisfactionScoresOverMonth +
                ", satisfactionBreakdown=" + satisfactionBreakdown +
                '}';
    }
}
