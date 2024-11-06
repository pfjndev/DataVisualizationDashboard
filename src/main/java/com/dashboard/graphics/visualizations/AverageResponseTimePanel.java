package com.dashboard.graphics.visualizations;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class AverageResponseTimePanel extends JPanel {
    private JLabel currentTimeLabel;
    private JLabel lastMonthTimeLabel;
    private JLabel changeLabel;

    public AverageResponseTimePanel(double currentTime, double lastMonthTime) {
        setLayout(new GridBagLayout());
        setOpaque(false); // Use custom painting for the background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;

        // Display Current Month Time
        currentTimeLabel = new JLabel(String.format("Current Month: %.2f hr", currentTime), SwingConstants.LEFT);
        currentTimeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        currentTimeLabel.setForeground(Color.WHITE);
        gbc.gridy = 0;
        add(currentTimeLabel, gbc);

        // Display Last Month Time
        lastMonthTimeLabel = new JLabel(String.format("Last Month: %.2f hr", lastMonthTime), SwingConstants.LEFT);
        lastMonthTimeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        lastMonthTimeLabel.setForeground(Color.LIGHT_GRAY);
        gbc.gridy = 1;
        add(lastMonthTimeLabel, gbc);

        // Calculate and Display Change Indicator
        double change = currentTime - lastMonthTime;
        changeLabel = new JLabel(getChangeText(change), SwingConstants.CENTER);
        changeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        changeLabel.setOpaque(true);
        changeLabel.setBackground(change < 0 ? Color.GREEN : Color.RED);
        changeLabel.setForeground(Color.WHITE);
        changeLabel.setPreferredSize(new Dimension(100, 30));
        gbc.gridy = 2;
        add(changeLabel, gbc);
    }

    private String getChangeText(double change) {
        if (change < 0) {
            return String.format("- %.2f hr", Math.abs(change));
        } else if (change > 0) {
            return String.format("+ %.2f hr", change);
        } else {
            return "No Change";
        }
    }

    public void setAverageResponseTime(double currentTime, double lastMonthTime) {
        currentTimeLabel.setText(String.format("Current Month: %.2f hr", currentTime));
        lastMonthTimeLabel.setText(String.format("Last Month: %.2f hr", lastMonthTime));

        double change = currentTime - lastMonthTime;
        changeLabel.setText(getChangeText(change));
        changeLabel.setBackground(change < 0 ? Color.GREEN : Color.RED);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Custom background for a "card" effect
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Background gradient
        GradientPaint background = new GradientPaint(0, 0, new Color(30, 30, 30), width, height, new Color(60, 60, 60));
        g2d.setPaint(background);
        RoundRectangle2D.Double bg = new RoundRectangle2D.Double(0, 0, width, height, 20, 20);
        g2d.fill(bg);
    }
}
