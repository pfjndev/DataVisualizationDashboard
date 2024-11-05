package com.dashboard.ui;

import javax.swing.*;

import com.dashboard.data.DashboardData;

import java.awt.*;

public class DashboardFrame extends JFrame {
    
    public DashboardFrame(DashboardData DashboardData) {
        super("Data Visualization Dashboard");

        DashboardPanel dashboardPanel = new DashboardPanel(DashboardData);
        MenuBar menuBar = new MenuBar();
        // Initialize and add components
        setJMenuBar(menuBar);
        add(dashboardPanel, BorderLayout.CENTER);
        
        // Exit the application when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Use the system look and feel
        setDefaultLookAndFeelDecorated(true);
        // Allow resizing
        setResizable(true);
        // Center the window
        setLocationRelativeTo(null);

        setBackground(Color.BLACK);

    }
}

