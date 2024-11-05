package com.dashboard.data;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static DashboardData loadDashboardData(File file) {
        DashboardData dashboardData = new DashboardData();
        dashboardData.setDirectoryPath(file.getAbsolutePath());
        
        // Load the monthly data from the file
        MonthlyData monthlyData = loadMonthlyData(file);
        dashboardData.addMonthlyData(getMonthFromFileName(file.getName()), monthlyData);

        return dashboardData;
    }

    private static MonthlyData loadMonthlyData(File file) {
        List<DailyMetric> dailyMetrics = new ArrayList<>();
        List<SatisfactionScore> satisfactionScores = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // Load Daily Metrics sheet
            Sheet dailyMetricsSheet = workbook.getSheet("Daily Metrics");
            for (Row row : dailyMetricsSheet) {
                if (row.getRowNum() == 0) continue; // Skip header

                LocalDate date = row.getCell(0).getLocalDateTimeCellValue().toLocalDate();
                date.format(DATE_FORMAT);
                String product = row.getCell(1).getStringCellValue();
                int avgResponseTime = (int) row.getCell(2).getNumericCellValue();
                int customerSatisfactionScore = (int) row.getCell(3).getNumericCellValue();
                int customerEffortScore = (int) row.getCell(4).getNumericCellValue();
                int netPromoterScore = (int) row.getCell(5).getNumericCellValue();

                dailyMetrics.add(new DailyMetric(date, product, avgResponseTime, customerSatisfactionScore, customerEffortScore, netPromoterScore));
            }

            // Load Satisfaction Breakdown sheet
            Sheet satisfactionSheet = workbook.getSheet("Satisfaction Breakdown");
            for (Row row : satisfactionSheet) {
                if (row.getRowNum() == 0) continue;

                String product = row.getCell(0).getStringCellValue();
                double[] scores = new double[5];
                for (int i = 0; i < 5; i++) {
                    scores[i] = row.getCell(i + 1).getNumericCellValue();
                }
                satisfactionScores.add(new SatisfactionScore(product, scores));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MonthlyData(dailyMetrics, satisfactionScores);
    }

    private static String getMonthFromFileName(String fileName) {
        return fileName.split("_")[0]; // Assumes file naming like "January_Dashboard_Data.xlsx"
    }
}
