package com.dashboard.ui;

import javax.swing.*;

import java.awt.*;

public class ChartPanel extends JPanel {
    private final JComponent chart;

    public ChartPanel(JComponent chart) {
        this.chart = chart;
        setPreferredSize(new Dimension(400, 300));
        setLayout(new BorderLayout());
        // Add a border around the panel
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        add(chart, BorderLayout.CENTER);
    }

    public JComponent getChart() {
        return chart;
    }
}
