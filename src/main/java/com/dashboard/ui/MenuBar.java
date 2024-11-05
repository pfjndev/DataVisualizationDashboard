package com.dashboard.ui;

import javax.swing.*;

import java.io.File;

import com.dashboard.data.DataLoader;
import com.dashboard.utils.FileUtils;

public class MenuBar extends JMenuBar {

    public MenuBar() {

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
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = fileChooser.getSelectedFile();
            try {
                FileUtils.showLoadingIndicator(true, this);
                DataLoader.loadDashboardData(selectedFolder.getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Data loaded successfully.");
                FileUtils.showLoadingIndicator(false, this);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
