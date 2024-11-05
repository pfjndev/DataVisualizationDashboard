package com.dashboard.ui;

import javax.swing.*;

import java.io.File;

import com.dashboard.data.DataLoader;
import com.dashboard.utils.FileUtils;

public class MenuBar extends JMenuBar {

    private JFileChooser fc;

    public MenuBar() {
        fc = new JFileChooser();
        JMenu fileMenu = new JMenu("Data");

        JMenuItem loadData = new JMenuItem("Load Data Folder");
        loadData.addActionListener(e -> selectDataFolder());

        JMenuItem exportData = new JMenuItem("Export Data");
        JMenuItem exit = new JMenuItem("Exit");

        exit.addActionListener(e -> System.exit(0));
        fileMenu.add(loadData);
        fileMenu.add(exportData);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        add(fileMenu);
    }

    private void selectDataFolder() {

        int returnVal = fc.showOpenDialog(this.getParent());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                FileUtils.showLoadingIndicator(true, this);
                DataLoader.loadDashboardData(file);
                JOptionPane.showMessageDialog(this.getParent(), "Data loaded successfully.");
                FileUtils.showLoadingIndicator(false, this);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
