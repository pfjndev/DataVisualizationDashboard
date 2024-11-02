package com.dashboard.ui;

import javax.swing.*;
import java.awt.*;

public class ChartPanel extends JPanel {
    private final Chart chart;  // Assume Chart is an interface for BarChart, LineChart, etc.

    public ChartPanel(Chart chart) {
        this.chart = chart;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Set up rendering hints for quality
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the chart within the panel’s bounds
        chart.draw(g2, 0, 0, getWidth(), getHeight());
    }
}
