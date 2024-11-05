package com.dashboard.graphics.visualizations;

import javax.swing.JComponent;

import com.dashboard.data.DailyMetric;

import java.awt.*;

import java.util.List;
import java.awt.FontMetrics;

public class BarChart extends JComponent {
    private List<DailyMetric> data;
    
    public BarChart(List<DailyMetric> dailyMetric) {
        // Set preferred size based on parent panel dimentions
        setPreferredSize(new Dimension(getWidth() / 2, getHeight() / 2));

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

        // Draw title
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));

        String title = "Customer Satisfaction Breakdown With %";
        FontMetrics FontMetrics = g2d.getFontMetrics();

        g2d.drawString(title, (getWidth() / 2) - FontMetrics.stringWidth(title), 20);

        // Draw Y-axis
        g2d.drawLine(50, 50, 50, getHeight() - 50);
        g2d.drawString("%", 50 - FontMetrics.stringWidth("%"), 50);

        // Draw X-axis
        g2d.drawLine(50, getHeight() - 50, getWidth() - 50, getHeight() - 50);

        // Draw bars
        int barWidth = (getWidth() / 2) / data.size();
        int x = getWidth() / getHeight() + barWidth;
        for (DailyMetric metric : data) {
            int barHeight = (int) (metric.getCustomerSatisfactionScore() * (getHeight() / 2) / data.size());
            g2d.setColor(Color.BLUE);
            g2d.fillRect(x, getHeight() - 50 - barHeight, barWidth, barHeight);
            // Draw x-axis label (The days names)
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.PLAIN, 12));
            g2d.drawString(metric.getDate().getDayOfMonth() + "", x + (barWidth / 2) - 5, getHeight() - 30);

            x += barWidth + FontMetrics.stringWidth(metric.getDate().getDayOfMonth() + "");
        }

    }
}