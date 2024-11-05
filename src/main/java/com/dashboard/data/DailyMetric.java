// DailyMetric.java - represents daily metrics data
package com.dashboard.data;

import java.time.LocalDate;

public class DailyMetric {
    private LocalDate date;
    private String product;
    private int avgResponseTime;
    private int customerSatisfactionScore;
    private int customerEffortScore;
    private int netPromoterScore;

    public DailyMetric(LocalDate date, String product, int avgResponseTime, int customerSatisfactionScore,
                       int customerEffortScore, int netPromoterScore) {
        this.date = date;
        this.product = product;
        this.avgResponseTime = avgResponseTime;
        this.customerSatisfactionScore = customerSatisfactionScore;
        this.customerEffortScore = customerEffortScore;
        this.netPromoterScore = netPromoterScore;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getProduct() {
        return product;
    }

    public int getAvgResponseTime() {
        return avgResponseTime;
    }

    public int getCustomerSatisfactionScore() {
        return customerSatisfactionScore;
    }

    public int getCustomerEffortScore() {
        return customerEffortScore;
    }

    public int getNetPromoterScore() {
        return netPromoterScore;
    }

    @Override
    public String toString() {
        return "DailyMetric{" +
                "date=" + date +
                ", product='" + product + '\'' +
                ", avgResponseTime=" + avgResponseTime +
                ", customerSatisfactionScore=" + customerSatisfactionScore +
                ", customerEffortScore=" + customerEffortScore +
                ", netPromoterScore=" + netPromoterScore +
                '}';
    }
}
