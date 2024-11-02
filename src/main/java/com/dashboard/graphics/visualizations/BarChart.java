package com.dashboard.graphics.visualizations;

import com.dashboard.data.DataModel;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class BarChart {
    private List<DataModel> dataModels;

    public void draw(Graphics2D g2d, int x, int y, int width, int height) {
        if (dataModels == null || dataModels.isEmpty()) return;

        int barWidth = width / dataModels.size();
        int maxHeight = 150; // Maximum height of the bars for normalization
        
        // Find max response time for scaling
        double maxResponseTime = dataModels.stream().mapToDouble(DataModel::getAverageResponseTime).max().orElse(1);

        for (int i = 0; i < dataModels.size(); i++) {
            double responseTime = dataModels.get(i).getAverageResponseTime();
            int barHeight = (int) ((responseTime / maxResponseTime) * maxHeight);

            // Gradient for each bar
            GradientPaint gradient = new GradientPaint(x + i * barWidth, y, Color.CYAN, x + i * barWidth, y + barHeight, Color.BLUE);
            g2d.setPaint(gradient);

            Rectangle2D bar = new Rectangle2D.Double(x + i * barWidth, y + height - barHeight, barWidth - 5, barHeight);
            g2d.fill(bar);

            setDataModels(dataModels);
        }
    }

    public void setDataModels(List<DataModel> dataModels) {
        this.dataModels = dataModels;
    }
}
