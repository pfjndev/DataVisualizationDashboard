package com.dashboard.ui;

import javax.swing.*;

import com.dashboard.data.DailyMetric;
import com.dashboard.data.DashboardData;
import com.dashboard.data.DataLoader;
import com.dashboard.data.MonthlyData;

import com.dashboard.graphics.visualizations.BarChart;
import com.dashboard.graphics.visualizations.LineChart;
import com.dashboard.graphics.visualizations.PieChart;

import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class DashboardPanel extends JPanel {
    private DashboardData dashboardData;
    // List of panels and their respective charts
    private List<ChartPanel> chartPanels;

    public DashboardPanel(DashboardData dashboardData) {
        this.dashboardData = dashboardData;
        this.chartPanels = new java.util.ArrayList<>();

        // Set to full screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension minSize = new Dimension(screenSize.width / 4, screenSize.height / 4);

        this.setSize(screenSize.width, screenSize.height);
        this.setPreferredSize(screenSize);
        
        this.setMinimumSize(minSize);
        this.setMaximumSize(screenSize);

        this.setBackground(Color.BLACK);
        GridLayout layout = new GridLayout(2, 2);
        this.setLayout(layout);
        layout.setHgap(10);
        layout.setVgap(10);

        loadInitialData();
        initCharts();
    }

    private void loadInitialData() {
        if (dashboardData != null && dashboardData.getDirectoryPath() != null) {
            // Load data from default file
            File file = new File(dashboardData.getDirectoryPath());
            
            if (file.exists()) {
                try {
                    this.dashboardData = DataLoader.loadDashboardData(file);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error",
                                                JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Data files not found. Please load data manually.", 
                                            "File Not Found", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void initCharts() {
        List<DailyMetric> monthDailyMetrics = dashboardData.getMetricsByPeriod(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 4, 30));
        /* MonthlyData februaryData = dashboardData.getMonthlyData("February");
        MonthlyData marchData = dashboardData.getMonthlyData("March");
        MonthlyData aprilData = dashboardData.getMonthlyData("April"); */

        Dimension dimension;
        int currentSize = chartPanels.size();
        
        if (currentSize < 1) {
            // Create a new panel for each chart
            dimension = new Dimension(DashboardPanel.WIDTH / 2, DashboardPanel.HEIGHT / 2);
        } else {
            // Create a new panel for each chart
            dimension = new Dimension(DashboardPanel.WIDTH / currentSize, DashboardPanel.HEIGHT / currentSize);
            // Clear the existing panels
            chartPanels.clear();
        }

        if (monthDailyMetrics != null) {

            BarChart barChart = new BarChart(monthDailyMetrics);
            ChartPanel barChartPanel = new ChartPanel(barChart, dimension);
            chartPanels.add(barChartPanel);

            /* LineChart lineChart = new LineChart(monthDailyMetrics);
            ChartPanel lineChartPanel = new ChartPanel(lineChart, dimension);
            chartPanels.add(lineChartPanel);

            PieChart pieChart = new PieChart(monthDailyMetrics);
            ChartPanel pieChartPanel = new ChartPanel(pieChart, dimension);
            chartPanels.add(pieChartPanel); */
        }

        // Add all the panels in the array
        for (ChartPanel panel : chartPanels) {
            add(panel);
        }
    }

    public void refreshDataPeriod(LocalDate start, LocalDate end) {
        List<DailyMetric> filteredMetrics = dashboardData.getMetricsByPeriod(start, end);
        
        if (filteredMetrics != null) {
            for (ChartPanel panel : chartPanels) {
                if (panel.getChart() instanceof BarChart) {
                    ((BarChart) panel.getChart()).updateData(filteredMetrics);
                } else if (panel.getChart() instanceof LineChart) {
                    ((LineChart) panel.getChart()).updateData(filteredMetrics);
                } else if (panel.getChart() instanceof PieChart) {
                    ((PieChart) panel.getChart()).updateData(filteredMetrics);
                }
            }
        }
        revalidate();
        repaint();
    }
}
