package com.dashboard.ui;

import javax.swing.*;

import java.awt.*;

public class ChartPanel extends JPanel {
    private final JComponent chart;

    public ChartPanel(JComponent chart, Dimension dimension) {
        this.chart = chart;
        setPreferredSize(dimension);
        setLayout(new BorderLayout());
        // Add a border around the panel
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        setBackground(Color.GRAY);
        add(chart, BorderLayout.CENTER);
    }

    public JComponent getChart() {
        return chart;
    }
}
