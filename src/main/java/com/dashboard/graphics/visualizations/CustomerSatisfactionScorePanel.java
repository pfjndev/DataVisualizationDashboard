package com.dashboard.graphics.visualizations;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CustomerSatisfactionScorePanel extends JPanel {
    private JLabel csatLabel;
    private JLabel percentageLabel;
    private int csatScore;

    public CustomerSatisfactionScorePanel(int csatScore) {
        this.csatScore = csatScore;
        setLayout(new BorderLayout());
        setOpaque(false);

        // Add padding around the panel
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // CSAT text label
        csatLabel = new JLabel("CSAT", SwingConstants.CENTER);
        csatLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        csatLabel.setForeground(Color.WHITE);
        add(csatLabel, BorderLayout.NORTH);

        // Percentage label for the score
        percentageLabel = new JLabel(csatScore + "%", SwingConstants.CENTER);
        FontMetrics metrics = percentageLabel.getFontMetrics(percentageLabel.getFont());
        int fontSize =  metrics.getHeight();
        percentageLabel.setFont(new Font("Arial", Font.BOLD, fontSize * 3/2));
        percentageLabel.setForeground(Color.WHITE);
        add(percentageLabel, BorderLayout.CENTER);
    }

    public void setCsatScore(int csatScore) {
        this.csatScore = csatScore;
        percentageLabel.setText(csatScore + "%");
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Background color and rounded corners 
        g2d.setColor(new Color(40, 44, 52)); // Dark background for modern look
        RoundRectangle2D.Double roundedRectangle = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20);
        g2d.fill(roundedRectangle);
        
        // Draw the gauge arc
        int arcDiameter = Math.min(getWidth() / 2, getHeight() / 2) - 20;
        int arcX = (getWidth() - arcDiameter) / 2;
        int arcY = (getHeight() - arcDiameter) / 2;

        // Background arc (light gray)
        g2d.setColor(new Color(70, 70, 70));
        g2d.setStroke(new BasicStroke(10));
        g2d.drawArc(arcX, arcY, arcDiameter, arcDiameter, 0, 360);

        // Score arc (colored based on score)
        g2d.setColor(csatScore >= 70 ? Color.GREEN : (csatScore >= 50 ? Color.ORANGE : Color.RED));
        int angle = (int) (csatScore * 3.6); // Convert score percentage to degrees
        g2d.drawArc(arcX, arcY, arcDiameter, arcDiameter, 90, -angle);
    }
}