package com.dashboard.ui;

import javax.swing.*;

import com.dashboard.data.DailyMetric;
import com.dashboard.data.DashboardData;
import com.dashboard.data.DataLoader;
import com.dashboard.data.MonthlyData;
import com.dashboard.graphics.visualizations.AverageResponseTimeOverMonthPanel;
import com.dashboard.graphics.visualizations.AverageResponseTimePanel;
import com.dashboard.graphics.visualizations.BarChart;
import com.dashboard.graphics.visualizations.CSATBreakdownPanel;
import com.dashboard.graphics.visualizations.CustomerSatisfactionScorePanel;
import com.dashboard.graphics.visualizations.LineChart;
import com.dashboard.graphics.visualizations.PieChart;
import com.dashboard.graphics.visualizations.ProductNamePanel;
import com.dashboard.graphics.visualizations.PromotersPassivesDetractorsPanel;

import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class DashboardPanel extends JPanel {
    private DashboardData dashboardData;
    // List of panels and their respective charts
    private List<ChartPanel> chartPanels;
    
    private ProductNamePanel productNamePanel;
    private AverageResponseTimePanel averageResponseTimePanel;

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

        setLayout(new GridBagLayout());
        setBackground(new Color(30, 30, 30)); // Background for dashboard
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Add Product Name Panel
        productNamePanel = new ProductNamePanel("Product 01");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 0.2;
        add(productNamePanel, gbc);

        // Add Average Response Time Panel
        averageResponseTimePanel = new AverageResponseTimePanel(11.24, 8.11);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 0.2;
        add(averageResponseTimePanel, gbc);

        // Add Average Response Time Panel
        averageResponseTimePanel = new AverageResponseTimePanel(20.01, 3.90);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 0.2;
        add(averageResponseTimePanel, gbc);

        // Promoters, Passives, Detractors Panel
        PromotersPassivesDetractorsPanel promotersPanel = new PromotersPassivesDetractorsPanel(67.76, 20.43, 11.81);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 0.3;
        gbc.weighty = 0.2;
        add(promotersPanel, gbc);

        // Customer Satisfaction Score Panel
        CustomerSatisfactionScorePanel csatPanel = new CustomerSatisfactionScorePanel(65);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 0.2;
        add(csatPanel, gbc);


        // Updated CSAT Breakdown Panel
        double[][] csatData = {
            { 10, 30, 35, 15, 10}, // Sample data
            { 10, 25, 30, 20, 15},
            { 15, 20, 25, 20, 20},
            { 20, 15, 20, 25, 20},
        };
        CSATBreakdownPanel csatBreakdownPanel = new CSATBreakdownPanel(csatData);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.6;
        gbc.weighty = 0.3;
        add(csatBreakdownPanel, gbc);

        // Updated Average Response Time Over Month Panel
        double[] responseTimes = { 10.5, 12.0, 8.7, 9.8, 11.2, 10.1, 9.5, 8.9, 10.3, 11.0, 9.7, 10.2 };
        AverageResponseTimeOverMonthPanel responseTimePanel = new AverageResponseTimeOverMonthPanel(responseTimes);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.6;
        gbc.weighty = 0.3;
        add(responseTimePanel, gbc);
        //loadInitialData();
        //initCharts();
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

            /*BarChart barChart = new BarChart(monthDailyMetrics);
            ChartPanel barChartPanel = new ChartPanel(barChart, dimension);
            chartPanels.add(barChartPanel);

            LineChart lineChart = new LineChart(monthDailyMetrics);
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
