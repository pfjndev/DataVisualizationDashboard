package com.dashboard.graphics.visualizations;

import com.dashboard.data.DataModel;
import com.dashboard.data.ProductData;

import javax.swing.JComponent;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Date;
import java.util.Map;

public class BarChart extends JComponent {
    private DataModel dataModel = new DataModel();
    private ProductData productData = dataModel.getProductData();

    private int barWidth = 50;
    private int PADDING = barWidth;

    public BarChart(DataModel dataModel, int x, int y, int w, int h) {
        this.dataModel = dataModel;
        setBounds(x + PADDING, y + PADDING, w - 2 * PADDING, h - 2 * PADDING);
    }

    // BarChart.java
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (dataModel == null || dataModel.getData().isEmpty()) return;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        barWidth = panelWidth / dataModel.getData().size();
        PADDING = barWidth;

        double maxResponseTime = dataModel.getProductData().getMax(productData.getAverageResponseTime());
        double minResponseTime = dataModel.getProductData().getMin(productData.getAverageResponseTime());

        // Iterate through each product and draw bars
        for (int i = 0; i < dataModel.getData().size(); i++) {
            for (Map.Entry<Date, Map<String, ProductData>> outerEntry : dataModel.getData().entrySet()) {
                for (Map.Entry<String, ProductData> entry : outerEntry.getValue().entrySet()) {
                ProductData productData = entry.getValue();
                double avgResponseTime = productData.getAverageResponseTime();
                int barHeight = (int) ((avgResponseTime - minResponseTime) / (maxResponseTime - minResponseTime) * panelHeight);
                int x = i * barWidth;
                int y = panelHeight - barHeight;

                GradientPaint gradient = new GradientPaint(x, y, Color.CYAN, x, y + barHeight, Color.BLUE);
                g2d.setPaint(gradient);
                g2d.fill(new Rectangle2D.Double(x, y, barWidth - 5, barHeight));

                g2d.setColor(Color.WHITE);
                g2d.draw(new Rectangle2D.Double(x, y, barWidth - 5, barHeight));

                g2d.drawString(productData.getProductName(), x, panelHeight - 5);

            }

        }
        
        }

    }
    
    public void setData(DataModel dataModel) {
        this.dataModel = dataModel;
        repaint();
    }
}