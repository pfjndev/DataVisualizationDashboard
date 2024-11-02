package com.dashboard.ui;

import java.awt.Font;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.Graphics2D;

import java.util.List;

import com.dashboard.data.DataModel;

public interface Chart {
    
    // Drawing methods
    void draw(Graphics2D g2, int x, int y, int width, int height);

    // Data model methods
    void setDataModels(List<DataModel> dataModels);

    // Chart customization methods
    void setChartTitle(String title);

    void setXAxisTitle(String title);

    void setYAxisTitle(String title);

    void setXAxisLabels(String[] labels);

    void setYAxisLabels(String[] labels);

    void setXAxisRange(double min, double max);

    void setYAxisRange(double min, double max);

    void setXAxisTicks(int ticks);

    void setYAxisTicks(int ticks);

    void setXAxisGridlines(boolean showGridlines);

    void setYAxisGridlines(boolean showGridlines);

    void setLegend(boolean showLegend);

    void setLegendPosition(int position);

    void setLegendLabels(String[] labels);

    void setLegendColors(Color[] colors);

    void setLegendFont(Font font);

    void setLegendFontSize(int size);

    void setLegendFontColor(Color color);

    void setLegendBackgroundColor(Color color);

    void setLegendBorderColor(Color color);

    void setLegendBorderStroke(Stroke stroke);

    void setLegendBorderRadius(int radius);

    void setLegendPadding(int padding);

    void setLegendItemPadding(int padding);

    void setLegendItemShape(Shape shape);

    void setLegendItemShapeSize(int size);

    void setLegendItemShapePadding(int padding);

    void setLegendItemFont(Font font);

    void setLegendItemFontSize(int size);

    void setLegendItemFontColor(Color color);

    void setLegendItemBackgroundColor(Color color);

    void setLegendItemBorderColor(Color color);

    void setLegendItemBorderStroke(Stroke stroke);

    void setLegendItemBorderRadius(int radius);

    void setLegendItemBorderWidth(int width);

    void setLegendItemWidth(int width);

    void setLegendItemHeight(int height);

    void setLegendItemMargin(int margin);

    void setLegendItemTextMargin(int margin);

    void setLegendItemIconMargin(int margin);

    void setLegendItemIconSize(int size);

    void setLegendItemIconShape(Shape shape);

    void setLegendItemIconShapeSize(int size);

    void setLegendItemIconShapePadding(int padding);

    void setLegendItemIconColor(Color color);

    void setLegendItemIconBorderColor(Color color);

    void setLegendItemIconBorderStroke(Stroke stroke);

    void setLegendItemIconBorderRadius(int radius);

    void setLegendItemIconBorderWidth(int width);

    void setLegendItemIconBackground(Color color);

    void setLegendItemIconVisible(boolean visible);

    void setLegendItemIconTextVisible(boolean visible);

    void setLegendItemIconShapeVisible(boolean visible);

    void setLegendItemIconBorderVisible(boolean visible);

    void setLegendItemIconBackgroundVisible(boolean visible);
}
