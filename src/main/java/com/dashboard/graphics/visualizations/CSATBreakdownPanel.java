package com.dashboard.graphics.visualizations;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CSATBreakdownPanel extends JPanel {
    private double[][] percentages;
    private String[] categories = { "Category 1", "Category 2", "Category 3", "Category 4"};
    private String[] satisfactionLevels = { "Very Satisfied", "Satisfied", "Neutral", "Dissatisfied", "Very Dissatisfied"};
    private Color[] colors = { Color.GREEN, new Color(144, 238, 144), Color.YELLOW, Color.ORANGE, Color.RED };

    public CSATBreakdownPanel(double[][] percentages) {
        this.percentages = percentages;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background with rounded corners
        g2d.setColor(new Color(40, 44, 52));  // Dark background for a modern look
        RoundRectangle2D.Double background = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20);
        g2d.fill(background);

        int width = getWidth();
        int height = getHeight();
        int barWidth = width / categories.length / 2;

        // Title
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 18));
        FontMetrics fmTitle = g2d.getFontMetrics();
        String title = "Customer Satisfaction Breakdown";
        int titleX = (width - fmTitle.stringWidth(title)) / 2;
        g2d.drawString(title, titleX, 30);

        // Draw bars with rounded corners
        for (int i = 0; i < categories.length; i++) {
            int x = width * i / categories.length + barWidth / 1/2;
            //int x = i * barWidth + 10;
            int y = height - 100;

            for (int j = 0; j < satisfactionLevels.length; j++) {
                int segmentHeight = (int) (percentages[i][j] * (height - 150) / 100);
                y -= segmentHeight;

                g2d.setColor(colors[j]);
                g2d.fillRoundRect(x, y, barWidth - 20, segmentHeight, 0, 0);

                // Percentage text inside segment
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.BOLD, 10));
                String percentageText = String.format("%.1f%%", percentages[i][j]);
                FontMetrics fm = g2d.getFontMetrics();
                int textX = x + (barWidth - 20 - fm.stringWidth(percentageText)) / 2;
                int textY = y + segmentHeight / 2 + fm.getAscent() / 2;
                g2d.drawString(percentageText, textX, textY);
            }

            // Category label beneath each bar
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.PLAIN, 12));
            String category = categories[i];
            FontMetrics fmCategory = g2d.getFontMetrics();
            int labelX = x + (barWidth - 20 - fmCategory.stringWidth(category)) / 2;
            g2d.drawString(category, labelX, height - 70);
        }

        // Draw legend at the bottom
        // Legend will be centered horizontally at the bottom of the panel
        int legendX = width / satisfactionLevels.length; 
        int legendY = height - 30;
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        for (int j = 0; j < satisfactionLevels.length; j++) {
            // Legend color box
            g2d.setColor(colors[j]);
            g2d.fillRect(legendX, legendY, 15, 15);

            // Legend text
            g2d.setColor(Color.WHITE);
            g2d.drawString(satisfactionLevels[j], legendX + 20, legendY + 12);
            FontMetrics fm = g2d.getFontMetrics();
            legendX = legendX + 50 + fm.stringWidth(satisfactionLevels[j]);  // Adjust spacing between legend items
        }
    }

    public void setPercentages(double[][] newPercentages) {
        this.percentages = newPercentages;
        repaint();
    }
}
