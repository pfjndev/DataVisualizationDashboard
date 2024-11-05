package com.dashboard.graphics.visualizations;

import javax.swing.JComponent;

import com.dashboard.data.DailyMetric;

import java.awt.*;

import java.util.List;

public class BarChart extends JComponent {
    private List<DailyMetric> data;
    
    public BarChart(List<DailyMetric> dailyMetric) {
        // Set preferred size based on parent panel dimentions
        setPreferredSize(new Dimension(getParent().getWidth() / 2, getParent().getHeight() / 2));

        this.data = dailyMetric;
    }

    public void updateData(List<DailyMetric> newData) {
        this.data = newData;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
    }
}