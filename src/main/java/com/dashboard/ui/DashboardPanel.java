package com.dashboard.ui;

import javax.swing.*;

import java.util.List;

import java.awt.*;

import com.dashboard.data.DataModel;
import com.dashboard.graphics.visualizations.BarChart;
import com.dashboard.graphics.visualizations.PieChart;
import com.dashboard.graphics.visualizations.LineChart;

public class DashboardPanel extends JPanel {

    // List of data models loaded from the file
    private List<DataModel> dataModels;

    private BarChart barChart = new BarChart();
    private PieChart pieChart = new PieChart();
    private LineChart lineChart = new LineChart();

    public DashboardPanel(List<DataModel> dataModels) {
        this.dataModels = dataModels;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Background for better visual clarity
        
        // Placeholder label to indicate where visualizations will go
        JLabel placeholder = new JLabel("Data Visualization Area", SwingConstants.CENTER);
        placeholder.setFont(new Font("Arial", Font.PLAIN, 18));
        placeholder.setForeground(Color.DARK_GRAY);
        add(placeholder, BorderLayout.CENTER);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Apply smooth rendering for better visual quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Apply anti-aliasing for text rendering
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        
        // Draw visualizations
        barChart.draw(g2d, 50, 50, 300, 300);
        pieChart.draw(g2d, 400, 50, 300, 300);
        lineChart.draw(g2d, 750, 50, 300, 300);
        pieChart.draw(g2d, 400, 450, 300, 300);
        
        // Set the data for each visualization
        barChart.setDataModels(dataModels);
        pieChart.setDataModels(dataModels);
        lineChart.setDataModels(dataModels);
        pieChart.setDataModels(dataModels);
        repaint();
    }
}
