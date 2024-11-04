package com.dashboard.ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.dashboard.data.DataModel;

import com.dashboard.graphics.visualizations.BarChart;
import com.dashboard.graphics.visualizations.PieChart;
import com.dashboard.graphics.visualizations.LineChart;

public class DashboardPanel extends JPanel {
    private BarChart barChart;
    private ChartPanel BarChartPanel;
    
    private PieChart pieChart;
    private ChartPanel PieChartPanel;

    private LineChart lineChart;
    private ChartPanel LineChartPanel;

    public DashboardPanel(DataModel dataModel) {
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        this.barChart = new BarChart(dataModel, 0, 0, getWidth(), getHeight());
        this.BarChartPanel = new ChartPanel(barChart);
        
        this.pieChart = new PieChart(dataModel, 0, 0, getWidth(), getHeight());
        this.PieChartPanel = new ChartPanel(pieChart);
        
        this.lineChart = new LineChart(dataModel);
        this.LineChartPanel = new ChartPanel(lineChart);

        
        /* // Placeholder label to indicate where visualizations will go
        JLabel placeholder = new JLabel("Data Visualization Area", SwingConstants.CENTER);
        placeholder.setFont(new Font("Arial", Font.PLAIN, 18));
        placeholder.setForeground(Color.DARK_GRAY);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(placeholder, gbc); */

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // BarChart
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(BarChartPanel, gbc);

        // PieChart
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(PieChartPanel, gbc);

        // LineChart
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(LineChartPanel, gbc);
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Apply smooth rendering for better visual quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Apply anti-aliasing for text rendering
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }
    
    public void setDataModel(DataModel dataModel) {
        barChart.setData(dataModel);
        pieChart.setData(dataModel);
        lineChart.setData(dataModel);
        revalidate(); // Update layout
        repaint(); // Refresh display
    }

    private void handleKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_TAB:
            if (e.isControlDown()) { // CTRL + T to cycle through panels
                switch (getComponentZOrder(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner())) {
                    case 0:
                        BarChartPanel.requestFocusInWindow();
                        break;
                    case 1:
                        PieChartPanel.requestFocusInWindow();
                        break;
                    case 2:
                        LineChartPanel.requestFocusInWindow();
                        break;
                    default:
                        break;
                }

                
            }
            break;
            case KeyEvent.VK_R:
                if (e.isMetaDown()) { // CMD + R to refresh data
                    // Refresh data logic
                    JOptionPane.showMessageDialog(this, "Data refreshed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case KeyEvent.VK_E:
                if (e.isMetaDown()) { // CMD + E to export data
                    exportData();
                    JOptionPane.showMessageDialog(this, "Data exported successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            case KeyEvent.VK_Q:
                if (e.isMetaDown()) { // CMD + Q to quit the application
                    System.exit(0);
                }
                break;
            default:
                break;
        }
    }

    private void exportData() {
        // Export data logic
    }

}
