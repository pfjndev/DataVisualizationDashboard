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

        int width = getWidth();
        int height = getHeight();

        // Background gradient
        GradientPaint background = new GradientPaint(0, 0, new Color(30, 30, 30), width, height, new Color(60, 60, 60));
        g2d.setPaint(background);
        RoundRectangle2D.Double bg = new RoundRectangle2D.Double(0, 0, width, height, 20, 20);
        g2d.fill(bg);
    }
}
