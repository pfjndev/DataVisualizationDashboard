package com.dashboard.graphics.visualizations;

import com.dashboard.data.DataModel;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.List;

public class PieChart{
    private List<DataModel> dataModels;

    public void draw(Graphics2D g2, int x, int y, int width, int height) {
        if (dataModels == null || dataModels.isEmpty()) return;

        // Summing satisfaction scores for normalization
        double total = dataModels.stream().mapToDouble(DataModel::getCustomerSatisfactionScore).sum();
        
        int startAngle = 0;
        int segmentIndex = 0;
        Color[] segmentColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};

        for (DataModel model : dataModels) {
            double percentage = model.getCustomerSatisfactionScore() / total;
            int arcAngle = (int) Math.round(percentage * 360);

            g2.setColor(segmentColors[segmentIndex % segmentColors.length]);
            Arc2D segment = new Arc2D.Double(x, y, width, height, startAngle, arcAngle, Arc2D.PIE);
            g2.fill(segment);

            startAngle += arcAngle;
            segmentIndex++;
        }
    }

    public void setDataModels(List<DataModel> dataModels) {
        this.dataModels = dataModels;
    }

}
