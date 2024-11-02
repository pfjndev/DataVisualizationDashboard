package com.dashboard.graphics.shapes;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class ShapeFactory {
    
    public static Shape createRectangle(double x, double y, double width, double height) {
        return new Rectangle2D.Double(x, y, width, height);
    }

    public static Shape createCircle(double x, double y, double radius) {
        return new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    public static Shape createTriangle(double x, double y, double side) {
        double halfSide = side / 2;
        double height = Math.sqrt(3) * halfSide / 2;
        
        return new Polygon(
                new int[] {(int) x, (int) (x - halfSide), (int) (x + halfSide)},
                new int[] {(int) (y - height), (int) (y + height), (int) (y + height)},
                3
        );
    }

    public static Shape createPentagon(double x, double y, double side) {
        double halfSide = side / 2;
        double height = Math.sqrt(3) * halfSide / 2;
        
        return new Polygon(
                new int[] {(int) x, (int) (x - halfSide), (int) (x - (side / 2)), (int) (x + (side / 2)), (int) (x + halfSide)},
                new int[] {(int) (y - height), (int) (y - height), (int) (y + height), (int) (y + height), (int) (y - height)},
                5
        );
    }

    public static Shape createHexagon(double x, double y, double side) {
        double halfSide = side / 2;
        double height = Math.sqrt(3) * halfSide / 2;
        
        return new Polygon(
                new int[] {(int) x, (int) (x - halfSide), (int) (x - halfSide), (int) x, (int) (x + halfSide), (int) (x + halfSide)},
                new int[] {(int) (y - height), (int) (y - (2 * height)), (int) (y - height), (int) (y + height), (int) (y - height), (int) (y - (2 * height))},
                6
        );
    }

    public static Shape createOctagon(double x, double y, double side) {
        double halfSide = side / 2;
        double height = Math.sqrt(2) * halfSide / 2;
        
        return new Polygon(
                new int[] {(int) x, (int) (x - halfSide), (int) (x - halfSide), (int) (x - height), (int) (x + height), (int) (x + halfSide), (int) (x + halfSide), (int) (x + height)},
                new int[] {(int) (y - halfSide), (int) (y - halfSide), (int) (y - height), (int) (y - halfSide), (int) (y - halfSide), (int) (y - height), (int) (y + halfSide), (int) (y + halfSide)},
                8
        );
    }

    public static Shape createStar(double x, double y, double outerRadius, double innerRadius) {
        int numPoints = 5;
        int numVertices = numPoints * 2;
        double angleIncrement = Math.PI / numPoints;
     
        Polygon star = new Polygon();
        for (int i = 0; i < numVertices; i++) {
            double radius = (i % 2 == 0) ? outerRadius : innerRadius;
            double angle = i * angleIncrement;
            double xPos = x + radius * Math.cos(angle);
            double yPos = y + radius * Math.sin(angle);
            star.addPoint((int) xPos, (int) yPos);
        }

        return star;
    }

    public static Shape createCross(double x, double y, double size) {
        return new Polygon(
                new int[] {(int) (x - size), (int) (x + size), (int) (x + size), (int) (x + size), (int) (x - size), (int) (x - size), (int) (x - size), (int) (x - size)},
                new int[] {(int) (y - size), (int) (y - size), (int) (y - size), (int) (y + size), (int) (y + size), (int) (y + size), (int) (y + size), (int) (y - size)},
                8
        );
    }

    public static Shape createArrow(double x, double y, double size) {
        return new Polygon(
                new int[] {(int) x, (int) (x + size), (int) (x + size), (int) (x + (2 * size)), (int) (x + (2 * size)), (int) (x + size), (int) (x + size)},
                new int[] {(int) (y - size), (int) (y - size), (int) (y - (2 * size)), (int) y, (int) (y + (2 * size)), (int) (y + size), (int) (y + size)},
                7
        );
    }

    public static Shape createHeart(double x, double y, double size) {
        double halfSize = size / 2;
        double quarterSize = size / 4;
        
        Polygon heart = new Polygon();
        heart.addPoint((int) x, (int) (y - halfSize));
        heart.addPoint((int) (x + quarterSize), (int) y);
        heart.addPoint((int) (x + halfSize), (int) (y - quarterSize));
        heart.addPoint((int) (x + halfSize), (int) y);
        heart.addPoint((int) x, (int) (y + halfSize));
        heart.addPoint((int) (x - halfSize), (int) y);
        heart.addPoint((int) (x - halfSize), (int) (y - quarterSize));
        heart.addPoint((int) (x - quarterSize), (int) y);
        
        return heart;
    }

    public static Shape createDiamond(double x, double y, double size) {
        double halfSize = size / 2;
        
        return new Polygon(
                new int[] {(int) x, (int) (x + halfSize), (int) x, (int) (x - halfSize)},
                new int[] {(int) (y - halfSize), (int) y, (int) (y + halfSize), (int) y},
                4
        );
    }

    public static Shape createCrescent(double x, double y, double size) {
        double halfSize = size / 2;
        double quarterSize = size / 4;
        
        Polygon crescent = new Polygon();
        crescent.addPoint((int) x, (int) (y - halfSize));
        crescent.addPoint((int) (x + quarterSize), (int) (y - halfSize));
        crescent.addPoint((int) (x + halfSize), (int) y);
        crescent.addPoint((int) (x + quarterSize), (int) (y + halfSize));
        crescent.addPoint((int) x, (int) (y + halfSize));
        crescent.addPoint((int) (x - halfSize), (int) y);
        crescent.addPoint((int) (x - quarterSize), (int) (y - halfSize));
        
        return crescent;
    }

    public static Shape createMoon(double x, double y, double size) {
        double halfSize = size / 2;
        double quarterSize = size / 4;
        
        Polygon moon = new Polygon();
        moon.addPoint((int) x, (int) (y - halfSize));
        moon.addPoint((int) (x + quarterSize), (int) (y - halfSize));
        moon.addPoint((int) (x + halfSize), (int) y);
        moon.addPoint((int) (x + quarterSize), (int) (y + halfSize));
        moon.addPoint((int) x, (int) (y + halfSize));
        moon.addPoint((int) (x - halfSize), (int) y);
        moon.addPoint((int) (x - quarterSize), (int) (y - halfSize));
        
        return moon;
    }

    public static Shape createCloud(double x, double y, double size) {
        double halfSize = size / 2;
        double quarterSize = size / 4;
        
        Polygon cloud = new Polygon();
        cloud.addPoint((int) x, (int) (y - halfSize));
        cloud.addPoint((int) (x + quarterSize), (int) (y - halfSize));
        cloud.addPoint((int) (x + halfSize), (int) (y - quarterSize));
        cloud.addPoint((int) (x + halfSize), (int) (y + quarterSize));
        cloud.addPoint((int) (x + quarterSize), (int) (y + halfSize));
        cloud.addPoint((int) x, (int) (y + halfSize));
        cloud.addPoint((int) (x - quarterSize), (int) (y + halfSize));
        cloud.addPoint((int) (x - halfSize), (int) (y + quarterSize));
        cloud.addPoint((int) (x - halfSize), (int) (y - quarterSize));
        cloud.addPoint((int) (x - quarterSize), (int) (y - halfSize));
        
        return cloud;
    }
}
