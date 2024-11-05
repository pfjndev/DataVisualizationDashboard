package com.dashboard.data;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class DashboardData {
    private final Map<String, MonthlyData> monthlyData;
    private final List<DailyMetric> allDailyMetrics;
    private final List<SatisfactionScore> allSatisfactionScores;

    public DashboardData() {
        this.monthlyData = new HashMap<>();
        this.allDailyMetrics = new ArrayList<>();
        this.allSatisfactionScores = new ArrayList<>();
    }

    public void addMonthlyData(String month, MonthlyData data) {
        monthlyData.put(month, data);
        allDailyMetrics.addAll(data.getDailyMetrics());
        allSatisfactionScores.addAll(data.getSatisfactionScores());
    }

    public MonthlyData getMonthlyData(String month) {
        return monthlyData.get(month);
    }

    // Access method to filter data based on period (month/year, etc.)
    public List<DailyMetric> getMetricsByPeriod(LocalDate start, LocalDate end) {
        return allDailyMetrics.stream()
                .filter(metric -> !metric.getDate().isBefore(start) && !metric.getDate().isAfter(end))
                .collect(Collectors.toList());
    }

    public List<SatisfactionScore> getSatisfactionScoresByPeriod(LocalDate start, LocalDate end) {
        // Filter logic if applicable
        return allSatisfactionScores;
    }
}
