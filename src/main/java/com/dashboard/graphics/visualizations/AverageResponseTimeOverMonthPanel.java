package com.dashboard.graphics.visualizations;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;

public class AverageResponseTimeOverMonthPanel extends JPanel {
    private double[] responseTimes; // Response times for each month

    public AverageResponseTimeOverMonthPanel(double[] responseTimes) {
        this.responseTimes = responseTimes;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int padding = 50;

        // Background gradient
        GradientPaint background = new GradientPaint(0, 0, new Color(30, 30, 30), width, height, new Color(60, 60, 60));
        g2d.setPaint(background);
        RoundRectangle2D.Double bg = new RoundRectangle2D.Double(0, 0, width, height, 20, 20);
        g2d.fill(bg);
        
        // Title
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        FontMetrics fmTitle = g2d.getFontMetrics();
        String title = "Average Response Time Over Month";
        int titleX = (width - fmTitle.stringWidth(title)) / 2;
        g2d.drawString(title, titleX, 30);

        // Calculate scaling factors
        double maxTime = getMax(responseTimes);
        int chartHeight = height - padding * 3;
        int chartWidth = width - padding * 2;
        
        // Draw Y axis labels (Response time)
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        FontMetrics fm = g2d.getFontMetrics();
        for (int i = 0; i <= responseTimes.length; i+=5) {
            int y = height - padding - i * chartHeight / responseTimes.length;
            g2d.drawString(String.format("%.0f", maxTime * i / responseTimes.length), 20, y - padding + fm.getAscent());
        }

        GradientPaint gradient = new GradientPaint(0, padding, Color.CYAN, 0, height - padding, Color.BLUE);
        g2d.setPaint(gradient);
        
        // Draw Smooth Curved line
        int prevX = padding;
        int prevY = height - (int) ((responseTimes[0] / maxTime) * chartHeight);

        for (int i = 0; i < responseTimes.length; i++) {
            int x = padding + i * chartWidth / (responseTimes.length - 1);
            int y = height - (int) ((responseTimes[i] / maxTime) * chartHeight);

            g2d.draw(new Line2D.Double(prevX, prevY, x, y));
            prevX = x;
            prevY = y;
        }

        // X-axis labels (Months)
        g2d.setColor(Color.WHITE);
        String[] months = { "Jan", "Feb", "Mar", "Apr" };
        for (int i = 0; i < months.length; i++) {
            int x = padding * 2 + i * width / months.length; 
            //int x = padding + i * (width - 2 * padding) / (responseTimes.length);
            g2d.drawString(months[i], x - 10, height - 20);
        }
    }

    private double getMax(double[] values) {
        double max = values[0];
        for (double v : values) {
            if (v > max) max = v;
        }
        return max;
    }

    public void setResponseTimes(double[] responseTimes) {
        this.responseTimes = responseTimes;
        repaint();
    }
}