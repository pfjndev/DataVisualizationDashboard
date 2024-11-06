package com.dashboard.graphics.visualizations;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class PromotersPassivesDetractorsPanel extends JPanel {
    private JLabel promotersLabel;
    private JLabel passivesLabel;
    private JLabel detractorsLabel;

    public PromotersPassivesDetractorsPanel(double promoters, double passives, double detractors) {
        setLayout(new GridLayout(3, 1, 0, 10));
        setOpaque(false);

        // Promoters
        promotersLabel = createLabel("🙂 " + String.format("%.2f%%", promoters), Color.GREEN);
        add(promotersLabel);

        // Passives
        passivesLabel = createLabel("😐 " + String.format("%.2f%%", passives), Color.ORANGE);
        add(passivesLabel);

        // Detractors
        detractorsLabel = createLabel("🙁 " + String.format("%.2f%%", detractors), Color.RED);
        add(detractorsLabel);
    }

    private JLabel createLabel(String text, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(color);
        return label;
    }

    public void updateValues(double promoters, double passives, double detractors) {
        promotersLabel.setText("🙂 " + String.format("%.2f%%", promoters));
        passivesLabel.setText("😐 " + String.format("%.2f%%", passives));
        detractorsLabel.setText("🙁 " + String.format("%.2f%%", detractors));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background color and rounded corners
        g2d.setColor(new Color(40, 44, 52)); // Dark background for modern look
        // x and y
        RoundRectangle2D.Double roundedRectangle = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20);
        g2d.fill(roundedRectangle);
    }
}