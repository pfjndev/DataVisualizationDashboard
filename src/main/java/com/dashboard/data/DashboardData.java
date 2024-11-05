package com.dashboard.data;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class DashboardData {
    private final Map<String, MonthlyData> monthlyData;
    private final List<DailyMetric> allDailyMetrics;
    private final List<SatisfactionScore> allSatisfactionScores;

    private final String defaultDirectory = "src/resources/dataFiles/January_Dashboard_Data.xlsx";
    private String userDirectory = "";

    public DashboardData() {
        this.monthlyData = new HashMap<>();
        this.allDailyMetrics = new ArrayList<>();
        this.allSatisfactionScores = new ArrayList<>();
    }
    
    public String getDirectoryPath() {
        return userDirectory.isEmpty() ? defaultDirectory : userDirectory;
    }

    public void setDirectoryPath(String path) {
        userDirectory = path;
    }
    
    public String getUserDirectoryPath() {
        return userDirectory;
    }
    
    public void setUserDirectoryPath(String path) {
        userDirectory = path;
    }
    
    public String getDefaultDirectoryPath() {
        return defaultDirectory;
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
        // Filter logic not applicable - no date field in SatisfactionScore
        return allSatisfactionScores;
    }
}
