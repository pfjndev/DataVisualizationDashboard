package com.dashboard.app;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import com.dashboard.ui.Toolbar;
import com.dashboard.ui.MenuBar;
import com.dashboard.ui.DashboardPanel;

public class DashboardApp extends JFrame {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Data Visualization Dashboard");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            frame.setJMenuBar(new MenuBar());

            frame.add(new Toolbar(), BorderLayout.NORTH);
            frame.add(new DashboardPanel(), BorderLayout.CENTER);

            frame.pack();
            frame.setVisible(true);
        });
    }

}