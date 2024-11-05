package com.dashboard.ui;

import javax.swing.*;

import com.dashboard.data.DataModel;

import java.awt.*;

public class DashboardFrame extends JFrame {
    // List of data models loaded from the file
    private DataModel dataModel = DataModel.getDataModel();
    private DashboardPanel dashboardPanel = new DashboardPanel(dataModel);
    private MenuBar menuBar = new MenuBar(dataModel, dashboardPanel);

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

        // Initialize and add components
        setJMenuBar(menuBar);
        add(dashboardPanel, BorderLayout.CENTER);
    }
}

