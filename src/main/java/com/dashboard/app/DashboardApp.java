package com.dashboard.app;

import javax.swing.SwingUtilities;

import com.dashboard.data.DashboardData;
import com.dashboard.ui.DashboardFrame;

public class DashboardApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardData dashboardData = new DashboardData();
            DashboardFrame frame = new DashboardFrame(dashboardData);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
