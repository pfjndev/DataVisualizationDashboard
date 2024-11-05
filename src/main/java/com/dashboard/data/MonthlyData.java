// MonthlyData.java - represents one month’s data
package com.dashboard.data;

import java.util.List;

public class MonthlyData {
    private List<DailyMetric> dailyMetrics;
    private List<SatisfactionScore> satisfactionScores;

    public MonthlyData(List<DailyMetric> dailyMetrics, List<SatisfactionScore> satisfactionScores) {
        this.dailyMetrics = dailyMetrics;
        this.satisfactionScores = satisfactionScores;
    }

    public List<DailyMetric> getDailyMetrics() {
        return dailyMetrics;
    }

    public List<SatisfactionScore> getSatisfactionScores() {
        return satisfactionScores;
    }
}
