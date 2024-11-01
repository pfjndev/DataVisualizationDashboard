package com.dashboard.app;

import javax.swing.SwingUtilities;
import com.dashboard.ui.DashboardFrame;

public class DashboardApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardFrame frame = new DashboardFrame();
            frame.setVisible(true);
        });
    }
}
