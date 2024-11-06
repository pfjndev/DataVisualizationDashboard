package com.dashboard.graphics.visualizations;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

public class CSATOverMonthPanel extends JPanel {
    private double[] csatScores;  // CSAT score per month
    private String[] months = { "January", "February", "March", "April" };

    public CSATOverMonthPanel(double[] csatScores) {
        this.csatScores = csatScores;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Background gradient
        GradientPaint background = new GradientPaint(0, 0, new Color(30, 30, 30), width, height, new Color(60, 60, 60));
        g2d.setPaint(background);
        RoundRectangle2D.Double bg = new RoundRectangle2D.Double(0, 0, width, height, 20, 20);
        g2d.fill(bg);

        // Title
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        String title = "Customer Satisfaction Score (CSAT) Over Month";
        FontMetrics fmTitle = g2d.getFontMetrics();
        int titleX = (width - fmTitle.stringWidth(title)) / 2;
        g2d.drawString(title, titleX, 30);

        // Padding and chart area dimensions
        int padding = 50;
        int chartWidth = width - 2 * padding;
        int chartHeight = height - 2 * padding - 50;

        // Y-axis labels (0 to 100 for CSAT scores, assuming 100 as maximum score)
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        for (int i = 0; i <= 5; i++) {
            int y = padding + i * chartHeight / 5;
            String label = String.valueOf(100 - i * 20) + "%";  // CSAT score labels from 100 down to 0
            g2d.setColor(Color.WHITE);
            g2d.drawString(label, padding - 30, y + 5);
        }

        // X-axis labels (month labels)
        for (int i = 0; i < months.length; i++) {
            int x = padding + i * chartWidth / (months.length - 1);
            String month = months[i];
            g2d.drawString(month, x - 20, height - padding + 20);
        }

        // Area path for CSAT scores
        Path2D.Double path = new Path2D.Double();
        path.moveTo(padding, height - padding);
        for (int i = 0; i < csatScores.length; i++) {
            int x = padding + i * chartWidth / (csatScores.length - 1);
            int y = padding + (int) ((100 - csatScores[i]) * chartHeight / 100);
            path.lineTo(x, y);
        }
        path.lineTo(padding + chartWidth, height - padding);
        path.closePath();

        // Fill the area under the curve
        g2d.setColor(new Color(255, 204, 102, 50));
        g2d.fill(path);

        // Draw the curve
        g2d.setColor(new Color(255, 204, 102));
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(path);

    }

    public void setCsatScores(double[] newCsatScores) {
        this.csatScores = newCsatScores;
        repaint();
    }
}
