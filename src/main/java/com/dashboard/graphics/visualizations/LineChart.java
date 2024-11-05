package com.dashboard.graphics.visualizations;

import com.dashboard.data.DataModel;
import com.dashboard.data.ProductData;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Date;
import java.util.Map;

public class LineChart extends JComponent {
    private int PADDING = 40; // Panel padding for chart
    private int LABEL_PADDING = 20; // Padding for labels

    private DataModel dataModel = DataModel.getDataModel();
    private ProductData productData = dataModel.getProductData();

    public LineChart(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set black theme background
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        if (dataModel == null || dataModel.getData().isEmpty())
            return;

        // Title Styling
        Font titleFont = new Font("Arial", Font.BOLD, 14);
        g2d.setFont(titleFont);
        g2d.setColor(Color.WHITE);
        String title = "Average Response Time Over Month";
        FontMetrics titleMetrics = g2d.getFontMetrics(titleFont);
        int titleX = (getWidth() - titleMetrics.stringWidth(title)) / 2;
        g2d.drawString(title, titleX, PADDING / 2);

        // Label Styling with line indicator
        g2d.setColor(Color.WHITE);
        int labelX = (getWidth() - titleMetrics.stringWidth(title)) / 2;
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(labelX - LABEL_PADDING, PADDING, labelX - LABEL_PADDING + 15, PADDING);
        g2d.drawString("Average Response Rate (ms)", labelX, PADDING);

        // Get average response time (ms) range for scaling
        // data already has getAverageResponseTime(), getMaxAvgResponseTime(), getMinAvgResponseTime() methods
        /*
            public double getMaxAvgResponseTime() {
                if (responseTimesOverMonth == null) return 0;
                return responseTimesOverMonth.stream().max(Double::compare).orElse(0.0);
            }

            public double getMinAvgResponseTime() {
                if (responseTimesOverMonth == null) return 0;
                return responseTimesOverMonth.stream().min(Double::compare).orElse(0.0);
            }
        */
        // dataList is a DataModel object, which has a List<ProductData> data field
        // ProductData has a Double averageResponseTime field
        // ProductData has a List<Double> responseTimesOverMonth field

        double maxAvgResponseTime = dataModel.getProductData().getMax(productData.getAverageResponseTime());
        double minAvgResponseTime = dataModel.getProductData().getMin(productData.getAverageResponseTime());

        // Axis and grid layout
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        
        int yLabelWidth = 50;
        
        int xLabelPadding = 10;
        int yLabelPadding = 10;
        
        // Draw X-Axis, No gridlines
        g2d.drawLine(PADDING, panelHeight - PADDING, panelWidth - PADDING, panelHeight - PADDING);
        g2d.drawString("Month", panelWidth - PADDING, panelHeight - PADDING + xLabelPadding);
        
        // Draw X-Axis Labels (Months)
        // Months names are already in the dataModel
        // dataModel has a private Map<Integer, String> monthsNames;

        int xLabel = PADDING;
        java.util.List<String> monthsNames = dataModel.getMonthsNames();
        for (int i = 0; i < monthsNames.size(); i++) {
            g2d.drawString(monthsNames.get(i), xLabel, panelHeight - PADDING + xLabelPadding);
            xLabel += (panelWidth - 2 * PADDING) / dataModel.getMonthsNames().size();
        }

        // Draw Y-Axis, No gridlines
        g2d.drawLine(PADDING, PADDING, PADDING, panelHeight - PADDING);
        g2d.drawString("Average Response Time Over Month (ms)", PADDING - yLabelWidth, PADDING - yLabelPadding);
        
        // Draw Y-Axis Labels
        // Response time lives in the ProductData object
        // We iterate through the dataModel to get the response time for each product
        // private double averageResponseTimeOverMonth;

        int yLabel = panelHeight - PADDING;
        for (int i = 0; i < dataModel.getData().size(); i++) {
            for (Map.Entry<Date, ProductData> outerEntry : dataModel.getData().entrySet()) {
                ProductData productData = outerEntry.getValue();
                double avgResponseTime = productData.getAverageResponseTime();
                g2d.drawString(String.valueOf((int) avgResponseTime), PADDING - yLabelWidth, yLabel);
                yLabel -= (panelHeight - 2 * PADDING) / dataModel.getData().size();
            }
        }

        // Get data and draw the line with gradient
        int x2 = panelWidth - PADDING;
        int y2 = PADDING;


        Path2D path = new Path2D.Double();
        for (Map.Entry<Date, ProductData> outerEntry : dataModel.getData().entrySet()) {
            ProductData productData = outerEntry.getValue();
            double avgResponseTime = productData.getAverageResponseTime();

            int x1 = PADDING + (panelWidth - 2 * PADDING) / dataModel.getData().size();
            int y1 = (int) ((avgResponseTime - minAvgResponseTime) / (maxAvgResponseTime - minAvgResponseTime) * (panelHeight - 2 * PADDING));

            path.moveTo(x1, y1);
            path.lineTo(x2, y2);

            GradientPaint gradient = new GradientPaint(x1, y1, new Color(y1), x2, y2, new Color(y2));
            g2d.setPaint(gradient);
            g2d.draw(path);

            /* g2d.setColor(Color.WHITE);
            g2d.drawString(productData.getProductName(), x1, panelHeight - 5); */

        }
    }

    // Method to update data and repaint chart
    public void setData(DataModel dataModel) {
        this.dataModel = dataModel;
        repaint();
    }
}
