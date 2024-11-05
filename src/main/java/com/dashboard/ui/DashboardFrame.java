package com.dashboard.ui;

import javax.swing.*;

import java.awt.*;

public class DashboardFrame extends JFrame {

    private DashboardPanel dashboardPanel = new DashboardPanel();
    private MenuBar menuBar = new MenuBar();

    public DashboardFrame() {
        // Set title and basic properties
        super("Data Visualization Dashboard");
        setSize(1200, 800);
        // Exit the application when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Center the window
        setLocationRelativeTo(null);
        // Use the system look and feel
        setDefaultLookAndFeelDecorated(true);
        // Allow resizing
        setResizable(true);

        setBackground(Color.BLACK);

        // Initialize and add components
        setJMenuBar(menuBar);
        add(dashboardPanel, BorderLayout.CENTER);
    }
}

