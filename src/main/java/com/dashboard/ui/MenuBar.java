package com.dashboard.ui;

import javax.swing.*;

import com.dashboard.data.DataLoader;
import com.dashboard.data.DataModel;

import java.io.File;
import java.io.IOException;

public class MenuBar extends JMenuBar {
    
    public MenuBar(DataModel dataModel, DashboardPanel dashboardPanel) {

        JMenu fileMenu = new JMenu("File");

        JMenuItem loadData = new JMenuItem("Load Data");
        loadData.addActionListener(e -> loadData(dataModel, dashboardPanel));

        JMenuItem exportData = new JMenuItem("Export Data");
        JMenuItem exit = new JMenuItem("Exit");

        exit.addActionListener(e -> System.exit(0));
        fileMenu.add(loadData);
        fileMenu.add(exportData);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        add(fileMenu);
    }

    private void loadData(DataModel dataModel, DashboardPanel dashboardPanel) {
        String lastUsedDirectory = "";

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Excel Data");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        // Set the last used directory
        if (!lastUsedDirectory.isEmpty()) {
            fileChooser.setCurrentDirectory(new File(lastUsedDirectory));
        }

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // Save the last used directory
            lastUsedDirectory = fileChooser.getCurrentDirectory().getAbsolutePath();

            try {
                DataLoader dataLoader = new DataLoader();
                dataLoader.loadData(file, loadedDataModel -> {
                    // If the loading is successful, update the data model
                    dataModel.setData(loadedDataModel);
                    // Update the data model in the dashboard panel
                    dashboardPanel.setDataModel(dataModel);
                    
                    // Notify the user
                    JOptionPane.showMessageDialog(null, "Data loaded successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

                }, errorMessage -> {
                    JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                });
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
