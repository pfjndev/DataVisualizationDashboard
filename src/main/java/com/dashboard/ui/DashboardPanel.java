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
            List<DailyMetric> metrics = januaryData.getDailyMetrics();
            
            BarChart barChart = new BarChart(metrics);
            ChartPanel barChartPanel = new ChartPanel(barChart);
            chartPanels.add(barChartPanel);

            LineChart lineChart = new LineChart(metrics);
            ChartPanel lineChartPanel = new ChartPanel(lineChart);
            chartPanels.add(lineChartPanel);

            PieChart pieChart = new PieChart(metrics);
            ChartPanel pieChartPanel = new ChartPanel(pieChart);
            chartPanels.add(pieChartPanel);
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
