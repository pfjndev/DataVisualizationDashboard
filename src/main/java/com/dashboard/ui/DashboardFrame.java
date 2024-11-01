package com.dashboard.ui;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {
    
    public DashboardFrame() {
        // Set title and basic properties
        super("Data Visualization Dashboard");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Initialize and add components
        setJMenuBar(new MenuBar()); // Add the custom menu bar
        add(new Toolbar(), BorderLayout.NORTH); // Add toolbar at the top
        add(new DashboardPanel(), BorderLayout.CENTER); // Central dashboard panel
    }
}

