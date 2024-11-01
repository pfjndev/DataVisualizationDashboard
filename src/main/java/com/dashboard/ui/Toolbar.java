package com.dashboard.ui;

import javax.swing.*;

import com.dashboard.data.DataLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Toolbar extends JToolBar {
    public Toolbar() {
        JButton loadDataButton = new JButton("Load Data");
        loadDataButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
    
            if (result == JFileChooser.APPROVE_OPTION) {
    
                File file = fileChooser.getSelectedFile();
                try {
                    new DataLoader().loadData(file, dataModels -> {
                        // Handle loaded data, e.g., update UI or store dataModels
                        JOptionPane.showMessageDialog(null, "Data loaded successfully!");
                    }, errorMessage -> {
                        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                    });
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });


        JButton exportButton = new JButton("Export Data");
        exportButton.addActionListener(new ToolbarActionListener("Export Data clicked"));

        add(loadDataButton);
        add(exportButton);
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
