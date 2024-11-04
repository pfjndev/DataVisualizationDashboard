package com.dashboard.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * ProductData class represents the data for a product in the dashboard application.
 * 
 * This class is used to store the data for a product, including:
 * product name, average response time, customer satisfaction score,
 * customer effort score, and net promoter score.
 * 
 * @version 2.0
 * @see ExcelParser
 */
public class ProductData {
    private String productName = "";
    private double averageResponseTime = 0.0;
    private double averageResponseTimeOverMonth = 0.0;
    private double customerSatisfactionScore = 0.0;
    private double customerSatisfactionScoreOverMonth = 0.0;
    private List<Double> customerSatisfactionBreakDownPercentage = new ArrayList<>();
    private double customerEffortScore = 0.0;
    private double netPromoterScore = 0.0;

    public ProductData() {
    }

    public ProductData(
        String productName,
        double averageResponseTime,
        double customerSatisfactionScore,
        double customerEffortScore,
        double netPromoterScore,
        List<Double> customerSatisfactionBreakDownPercentage
    ) {
        this.productName = productName;
        this.averageResponseTime = averageResponseTime;
        this.customerSatisfactionScore = customerSatisfactionScore;
        this.customerEffortScore = customerEffortScore;
        this.netPromoterScore = netPromoterScore;
        this.customerSatisfactionBreakDownPercentage = customerSatisfactionBreakDownPercentage;
        // Initialize the average response time over the month
        calculateARTOverMonth();
        // Initialize the customer satisfaction score over the month
        calculateCSATOverMonth();
        // Initialize the customer satisfaction breakdown percentage
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

    public double getAverageResponseTimeOverMonth() {
        return averageResponseTimeOverMonth;
    }

    public void setAverageResponseTimeOverMonth(double averageResponseTimeOverMonth) {
        this.averageResponseTimeOverMonth = averageResponseTimeOverMonth;
    }

    public double getCustomerSatisfactionScore() {
        return customerSatisfactionScore;
    }

    public void setCustomerSatisfactionScore(double customerSatisfactionScore) {
        this.customerSatisfactionScore = customerSatisfactionScore;
    }

    public double getCustomerSatisfactionScoreOverMonth() {
        return customerSatisfactionScoreOverMonth;
    }

    public void setCustomerSatisfactionScoreOverMonth(double customerSatisfactionScoreOverMonth) {
        this.customerSatisfactionScoreOverMonth = customerSatisfactionScoreOverMonth;
    }

    public List<Double> getCustomerSatisfactionBreakDownPercentage() {
        return customerSatisfactionBreakDownPercentage;
    }

    public void setCustomerSatisfactionBreakDownPercentage(double customerSatisfactionBreakDownPercentage) {
        this.customerSatisfactionBreakDownPercentage.add(customerSatisfactionBreakDownPercentage);
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
    
    @Override
    public String toString() {
        return "ProductData{" +
                "productName='" + productName + '\'' +
                ", averageResponseTime=" + averageResponseTime +
                ", averageResponseTimeOverMonth=" + averageResponseTimeOverMonth +
                ", customerSatisfactionScore=" + customerSatisfactionScore +
                ", customerSatisfactionScoreOverMonth=" + customerSatisfactionScoreOverMonth +
                ", customerSatisfactionBreakDownPercentage=" + customerSatisfactionBreakDownPercentage +
                ", customerEffortScore=" + customerEffortScore +
                ", netPromoterScore=" + netPromoterScore +
                '}';
    }

    /**
     * getMin
     * 
     * This method calculates the minimum value from whatever metric is passed in
     * 
     * @return The minimum value of the metric
     * @reference https://www.baeldung.com/java-max-min-array
     */
    public Double getMin(Double metric) {
        // Find what metric is passed in, ex: customerSatisfactionScore, netPromoterScore, etc.
        // Iterate through the data model and get the minimum value of the metric
        // If the metric is null, return 0.0
        // If the metric is not null, return the minimum value calculated
        // from all the values in the data model

        if (metric == null) return 0.0;

        String metricName = metric.describeConstable().map(Object::toString).orElse("");

        DataModel dataModel = DataModel.getDataModel();
        return dataModel.getData().values().stream()
                .flatMap(map -> map.values().stream())
                .map(productData -> {
                    switch (metricName) {
                        case "customerSatisfactionScore":
                            return productData.getCustomerSatisfactionScore();
                        case "netPromoterScore":
                            return productData.getNetPromoterScore();
                        case "customerEffortScore":
                            return productData.getCustomerEffortScore();
                        case "averageResponseTime":
                            return productData.getAverageResponseTime();
                        case "averageResponseTimeOverMonth":
                            return productData.getAverageResponseTimeOverMonth();
                        case "customerSatisfactionScoreOverMonth":
                            return productData.getCustomerSatisfactionScoreOverMonth();
                        default:
                            return 0.0;
                    }
                })
                .min(Double::compare)
                .orElse(0.0);
        
    }
    
    public Double getMin(List<Double> metric) {
        if (metric == null || metric.isEmpty()) return 0.0;
        return metric.stream().min(Double::compare).orElse(0.0);
    }

    /**
     * getMax
     * 
     * This method calculates the maximum value from whatever metric is passed in
     * 
     * @return The maximum value of the metric
     * @reference https://www.baeldung.com/java-max-min-array
     */
    public Double getMax(Double metric) {
        // Iterate through the data model and get the maximum value of the metric
        // If the metric is null, return 0.0
        // If the metric is not null, return the maximum value calculated
        // from all the values in the data model

        if (metric == null) return 0.0;
        
        String metricName = metric.describeConstable().map(Object::toString).orElse("");

        DataModel dataModel = DataModel.getDataModel();
        return dataModel.getData().values().stream()
                .flatMap(map -> map.values().stream())
                .map(productData -> {
                    switch (metricName) {
                        case "customerSatisfactionScore":
                            return productData.getCustomerSatisfactionScore();
                        case "netPromoterScore":
                            return productData.getNetPromoterScore();
                        case "customerEffortScore":
                            return productData.getCustomerEffortScore();
                        case "averageResponseTime":
                            return productData.getAverageResponseTime();
                        case "averageResponseTimeOverMonth":
                            return productData.getAverageResponseTimeOverMonth();
                        case "customerSatisfactionScoreOverMonth":
                            return productData.getCustomerSatisfactionScoreOverMonth();
                        default:
                            return 0.0;
                    }
                })
                .max(Double::compare)
                .orElse(0.0);
    }

        

    /**
     * getAverage
     * 
     * This method calculates the average value from whatever metric is passed in
     * 
     * @return The average value of the metric
     * @reference https://www.baeldung.com/java-average
     */
    public Double getAverage(Double metric) {
        // Iterate through the data model and get the average value of the metric
        // If the metric is null, return 0.0
        // If the metric is not null, return the average value calculated
        // from all the values in the data model

        if (metric == null) return 0.0;

        String metricName = metric.describeConstable().map(Object::toString).orElse("");

        DataModel dataModel = DataModel.getDataModel();
        return dataModel.getData().values().stream()
                .flatMap(map -> map.values().stream())
                .map(productData -> {
                    switch (metricName) {
                        case "customerSatisfactionScore":
                            return productData.getCustomerSatisfactionScore();
                        case "netPromoterScore":
                            return productData.getNetPromoterScore();
                        case "customerEffortScore":
                            return productData.getCustomerEffortScore();
                        case "averageResponseTime":
                            return productData.getAverageResponseTime();
                        case "averageResponseTimeOverMonth":
                            return productData.getAverageResponseTimeOverMonth();
                        case "customerSatisfactionScoreOverMonth":
                            return productData.getCustomerSatisfactionScoreOverMonth();
                        default:
                            return 0.0;
                    }
                })
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    public Double getAverage(List<Double> metric) {
        if (metric == null || metric.isEmpty()) return 0.0;
        return metric.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    /**
     * calculateARTOverMonth
     * 
     * This method calculates the average response time over the month
     * 
     * @return The average response time over the month
     */
    public void calculateARTOverMonth() {
        // Iterate through the data model and get the average response time over the month
        // If the average response time is null, return 0.0
        // If the average response time is not null, return the average response time calculated
        // from all the values in the data model

        if (Double.isNaN(this.averageResponseTimeOverMonth)) return;
        DataModel dataModel = DataModel.getDataModel();
        
        setAverageResponseTimeOverMonth(
            dataModel.getData().values().stream() // Stream of values, which are maps of product data
                .flatMap(map -> map.values().stream()) // Stream of product data objects from the map
                .mapToDouble(ProductData::getAverageResponseTime) // Stream of average response times from product data objects
                .average()  // Calculate the average of the stream of average response times
                .orElse(0.0) // If the average is not present, return 0.0
        );
    }

    /**
     * calculateCSATOverMonth
     * 
     * This method calculates the customer satisfaction score over the month
     * 
     * The customer satisfaction score over the month is set after the calculation.
     */
    public void calculateCSATOverMonth() {
        // Iterate through the data model and get the customer satisfaction score over the month
        // If the customer satisfaction score is null, return 0.0
        // If the customer satisfaction score is not null, return the customer satisfaction score calculated
        // from all the values in the data model

        if (Double.isNaN(this.customerSatisfactionScoreOverMonth)) return;
        DataModel dataModel = DataModel.getDataModel();
        
        setCustomerSatisfactionScoreOverMonth(
            dataModel.getData().values().stream() // Stream of values, which are maps of product data
                .flatMap(map -> map.values().stream()) // Stream of product data objects from the map
                .mapToDouble(ProductData::getCustomerSatisfactionScore) // Stream of customer satisfaction scores from product data objects
                .average() // Calculate the average of the stream of customer satisfaction scores
                .orElse(0.0) // If the average is not present, return 0.0
        );
    }
    /**
     * getMedian
     * 
     * This method calculates the median value from whatever metric is passed in
     * 
     * @return The median value of the metric
     * @reference https://www.baeldung.com/java-median
     */
    public Double getMedian(Double metric) {
        // Iterate through the data model and get the median value of the metric
        // If the metric is null, return 0.0
        // If the metric is not null, return the median value calculated
        // from all the values in the data model

        if (metric == null) return 0.0;

        String metricName = metric.describeConstable().map(Object::toString).orElse("");

        DataModel dataModel = DataModel.getDataModel();
        List<Double> metricList = dataModel.getData().values().stream()
                .flatMap(map -> map.values().stream())
                .map(productData -> {
                    switch (metricName) {
                        case "customerSatisfactionScore":
                            return productData.getCustomerSatisfactionScore();
                        case "netPromoterScore":
                            return productData.getNetPromoterScore();
                        case "customerEffortScore":
                            return productData.getCustomerEffortScore();
                        case "averageResponseTime":
                            return productData.getAverageResponseTime();
                        case "averageResponseTimeOverMonth":
                            return productData.getAverageResponseTimeOverMonth();
                        case "customerSatisfactionScoreOverMonth":
                            return productData.getCustomerSatisfactionScoreOverMonth();
                        default:
                            return 0.0;
                    }
                })
                .collect(Collectors.toList());
        
        int size = metricList.size();
        if (size == 0) return 0.0;
        
        Collections.sort(metricList);
        int middle = size / 2;
        if (size % 2 == 1) {
            return metricList.get(middle);
        } else {
            return (metricList.get(middle - 1) + metricList.get(middle)) / 2.0;
        }
    }

    /**
     * getStandardDeviation
     * 
     * This method calculates the standard deviation value from whatever metric is passed in
     * 
     * @return The standard deviation value of the metric
     * @reference https://www.baeldung.com/java-standard-deviation
     */
    public Double getStandardDeviation(Double metric) {
        // Iterate through the data model and get the standard deviation value of the metric
        // If the metric is null, return 0.0
        // If the metric is not null, return the standard deviation value calculated
        // from all the values in the data model

        if (metric == null) return 0.0;

        String metricName = metric.describeConstable().map(Object::toString).orElse("");

        DataModel dataModel = DataModel.getDataModel();
        List<Double> metricList = dataModel.getData().values().stream()
                .flatMap(map -> map.values().stream())
                .map(productData -> {
                    switch (metricName) {
                        case "customerSatisfactionScore":
                            return productData.getCustomerSatisfactionScore();
                        case "netPromoterScore":
                            return productData.getNetPromoterScore();
                        case "customerEffortScore":
                            return productData.getCustomerEffortScore();
                        case "averageResponseTime":
                            return productData.getAverageResponseTime();
                        case "averageResponseTimeOverMonth":
                            return productData.getAverageResponseTimeOverMonth();
                        case "customerSatisfactionScoreOverMonth":
                            return productData.getCustomerSatisfactionScoreOverMonth();
                        default:
                            return 0.0;
                    }
                })
                .collect(Collectors.toList());
        
        int size = metricList.size();
        if (size == 0) return 0.0;
        
        double mean = metricList.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        double sum = metricList.stream().mapToDouble(value -> Math.pow(value - mean, 2)).sum();
        return Math.sqrt(sum / size);
    }

    /**
     * getVariance
     * 
     * This method calculates the variance value from whatever metric is passed in
     * 
     * @return The variance value of the metric
     * @reference https://www.baeldung.com/java-variance
     */
    public Double getVariance(Double metric) {
        // Iterate through the data model and get the variance value of the metric
        // If the metric is null, return 0.0
        // If the metric is not null, return the variance value calculated
        // from all the values in the data model

        if (metric == null) return 0.0;

        String metricName = metric.describeConstable().map(Object::toString).orElse("");

        DataModel dataModel = DataModel.getDataModel();
        List<Double> metricList = dataModel.getData().values().stream()
                .flatMap(map -> map.values().stream())
                .map(productData -> {
                    switch (metricName) {
                        case "customerSatisfactionScore":
                            return productData.getCustomerSatisfactionScore();
                        case "netPromoterScore":
                            return productData.getNetPromoterScore();
                        case "customerEffortScore":
                            return productData.getCustomerEffortScore();
                        case "averageResponseTime":
                            return productData.getAverageResponseTime();
                        case "averageResponseTimeOverMonth":
                            return productData.getAverageResponseTimeOverMonth();
                        case "customerSatisfactionScoreOverMonth":
                            return productData.getCustomerSatisfactionScoreOverMonth();
                        default:
                            return 0.0;
                    }
                })
                .collect(Collectors.toList());
        
        int size = metricList.size();
        if (size == 0) return 0.0;
        
        double mean = metricList.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        return metricList.stream().mapToDouble(value -> Math.pow(value - mean, 2)).sum() / size;
    }

}
