package com.dashboard.ui;

import javax.swing.*;

import com.dashboard.data.DataLoader;
import com.dashboard.data.DataModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MenuBar extends JMenuBar {
    public MenuBar(List<DataModel> dataModels) {
        JMenu fileMenu = new JMenu("File");
        
        JMenuItem loadData = new JMenuItem("Load Data");
        loadData.addActionListener(e -> loadData(dataModels));
        
        JMenuItem exportData = new JMenuItem("Export Data");
        
        JMenuItem exit = new JMenuItem("Exit");
        // Exit application when Exit menu item is clicked
        exit.addActionListener(e -> System.exit(0));
        
        fileMenu.add(loadData);
        fileMenu.add(exportData);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        add(fileMenu);
    }
    
    private void loadData(List<DataModel> dataModels) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                new DataLoader().loadData(file, data -> {
                    // Clear the existing data and add the new data
                    dataModels.clear();
                    // Add all the new data
                    dataModels.addAll(data);

                    // Notify the user and trigger a UI update
                    JOptionPane.showMessageDialog(null, "Data loaded successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                }, errorMessage -> {
                    JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                });
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
