package com.dashboard.graphics.visualizations;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ProductNamePanel extends JPanel {
    private JLabel productNameLabel;

    public ProductNamePanel(String productName) {
        setLayout(new BorderLayout());
        setOpaque(false);

        productNameLabel = new JLabel(productName, SwingConstants.CENTER);
        productNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        productNameLabel.setForeground(Color.WHITE);

        add(productNameLabel, BorderLayout.CENTER);
    }

    public void setProductName(String productName) {
        productNameLabel.setText(productName);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Custom background for a "card" effect
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background color and rounded corners 
        g2d.setColor(new Color(40, 44, 52)); // Dark background for modern look
        RoundRectangle2D.Double roundedRectangle = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20);
        g2d.fill(roundedRectangle);
    }
}
