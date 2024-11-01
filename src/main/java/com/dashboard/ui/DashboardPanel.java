package com.dashboard.ui;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    public DashboardPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Background for better visual clarity
        
        // Placeholder label to indicate where visualizations will go
        JLabel placeholder = new JLabel("Data Visualization Area", SwingConstants.CENTER);
        placeholder.setFont(new Font("Arial", Font.PLAIN, 18));
        placeholder.setForeground(Color.DARK_GRAY);
        add(placeholder, BorderLayout.CENTER);
    }
}
