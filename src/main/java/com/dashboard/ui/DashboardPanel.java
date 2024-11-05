package com.dashboard.ui;

import javax.swing.*;

import com.dashboard.data.DailyMetric;
import com.dashboard.data.DashboardData;
import com.dashboard.data.DataLoader;
import com.dashboard.data.MonthlyData;
import com.dashboard.graphics.visualizations.BarChart;

import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class DashboardPanel extends JPanel {
    private DashboardData dashboardData;

    public DashboardPanel() {
        setLayout(new GridLayout(2, 2));
        loadInitialData();
        initCharts();
    }

    private void loadInitialData() {
        // Load data from default folder
        String folderPath = dashboardData.getDirectoryPath();
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            this.dashboardData = DataLoader.loadDashboardData(folderPath);
        } else {
            JOptionPane.showMessageDialog(this, "Data files not found. Please load data manually.", 
                                          "File Not Found", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void initCharts() {
        MonthlyData januaryData = dashboardData.getMonthlyData("January");
        
        if (januaryData != null) {
            BarChart barChart = new BarChart(januaryData.getDailyMetrics());
            ChartPanel barChartPanel = new ChartPanel(barChart);
            add(barChartPanel);

            // Add other charts here
        }
    }

    public void refreshDataPeriod(LocalDate start, LocalDate end) {
        List<DailyMetric> filteredMetrics = dashboardData.getMetricsByPeriod(start, end);
        
        // Update each chart with the filtered data
        for (Component component : getComponents()) {
            if (component instanceof ChartPanel) {
                ChartPanel chartPanel = (ChartPanel) component;
                BarChart barChart = (BarChart) chartPanel.getChart();
                barChart.updateData(filteredMetrics);
            }
        }
        revalidate();
        repaint();
    }
}
