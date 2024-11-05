package com.dashboard.graphics.visualizations;

import com.dashboard.data.DataModel;
import com.dashboard.data.ProductData;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.List;

import javax.swing.JComponent;

public class PieChart extends JComponent {

    private DataModel dataModel = new DataModel();
    private ProductData productData = dataModel.getProductData();

    private int PADDING = 10;

    public PieChart(DataModel dataModel, int x, int y, int w, int h) {
        this.dataModel = dataModel;
        setBounds(x, y, w, h);
    }
    
    // PieChart.java
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (dataModel == null) return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double totalValue = dataModel.getProductData().getCustomerEffortScore();
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int diameter = Math.min(panelWidth, panelHeight) - PADDING * 2;

        Color[] segmentColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};
        int segmentIndex = 0;

        double currentAngle = 0.0;

    }

    public void setData(DataModel dataModels) {
        this.dataModel = dataModels;
        repaint();
    }

}
