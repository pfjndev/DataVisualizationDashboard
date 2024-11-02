package com.dashboard.ui;

import javax.swing.*;

import com.dashboard.data.DataLoader;
import com.dashboard.data.DataModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Toolbar extends JToolBar {

    public Toolbar(List<DataModel> dataModels) {
        JButton loadDataButton = new JButton("Load Data");
        loadDataButton.addActionListener(e -> loadData(dataModels));

        JButton exportButton = new JButton("Export Data");
        exportButton.addActionListener(new ToolbarActionListener("Export Data clicked"));

        add(loadDataButton);
        add(exportButton);
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
                    JOptionPane.showMessageDialog(null, "Data loaded successfully!");
                }, errorMessage -> {
                    JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                });
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private static class ToolbarActionListener implements ActionListener {
        private final String message;

        public ToolbarActionListener(String message) {
            this.message = message;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, message);
        }
    }
}