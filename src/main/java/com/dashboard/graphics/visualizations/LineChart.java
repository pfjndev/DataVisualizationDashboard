package com.dashboard.graphics.visualizations;

import com.dashboard.data.DataModel;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;

public class LineChart {
    private List<DataModel> dataModels;

    public void draw(Graphics2D g2, int x, int y, int width, int height) {
        if (dataModels == null || dataModels.isEmpty()) return;

        double maxScore = dataModels.stream().mapToDouble(DataModel::getCustomerSatisfactionScore).max().orElse(1);

        // Calculate point spacing
        int pointSpacing = width / (dataModels.size() - 1);

        // Draw lines between data points
        g2.setColor(Color.MAGENTA);
        g2.setStroke(new BasicStroke(2));

        for (int i = 0; i < dataModels.size() - 1; i++) {
            double score1 = dataModels.get(i).getCustomerSatisfactionScore();
            double score2 = dataModels.get(i + 1).getCustomerSatisfactionScore();

            int y1 = y + height - (int) ((score1 / maxScore) * height);
            int y2 = y + height - (int) ((score2 / maxScore) * height);

            Line2D line = new Line2D.Double(x + i * pointSpacing, y1, x + (i + 1) * pointSpacing, y2);
            g2.draw(line);
        }
    }

    public void setDataModels(List<DataModel> dataModels) {
        this.dataModels = dataModels;
    }
}
