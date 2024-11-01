package com.dashboard.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        JMenu fileMenu = new JMenu("File");
        
        JMenuItem loadData = new JMenuItem("Load Data");
        loadData.addActionListener(new MenuActionListener("Load Data clicked"));
        
        JMenuItem exportData = new JMenuItem("Export Data");
        exportData.addActionListener(new MenuActionListener("Export Data clicked"));
        
        JMenuItem exit = new JMenuItem("Exit");
        // Exit application when Exit menu item is clicked
        exit.addActionListener(e -> System.exit(0));
        
        fileMenu.add(loadData);
        fileMenu.add(exportData);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        add(fileMenu);
    }
    
    private static class MenuActionListener implements ActionListener {
        private final String message;

        public MenuActionListener(String message) {
            this.message = message;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, message);
        }
    }
}
