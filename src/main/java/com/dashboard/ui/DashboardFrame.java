package com.dashboard.ui;

import javax.swing.*;

import com.dashboard.data.DashboardData;

import java.awt.*;

public class DashboardFrame extends JFrame {
    
    public DashboardFrame(DashboardData DashboardData) {
        super("Data Visualization Dashboard");
        // Exit the application when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Use the system look and feel
        try {
            UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
            for (UIManager.LookAndFeelInfo info : infos) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Allow resizing
        setResizable(true);
        // Center the window
        setLocationRelativeTo(null);

        DashboardPanel dashboardPanel = new DashboardPanel(DashboardData);
        MenuBar menuBar = new MenuBar();
        // Initialize and add components
        setJMenuBar(menuBar);
        add(dashboardPanel);
    }
}

