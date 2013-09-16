package tse.lr1;

import java.awt.geom.Point2D;
import java.util.Scanner;
import tse.Util;

/**
 * Площадь и периметр треугольника.
 * @author aNNiMON
 */
public class Triangle {
    
    private final Scanner scanner;
    private Point2D pointA, pointB, pointC;
    
    public static void main() {
        new Triangle().work();
    }
    
    public Triangle() {
        scanner = new Scanner(System.in);
    }

    private void work() {
        pointA = readPoint('A');
        pointB = readPoint('B');
        pointC = readPoint('C');
        double sideAB = calcSideLength(pointA, pointB);
        double sideBC = calcSideLength(pointB, pointC);
        double sideAC = calcSideLength(pointA, pointC);
        
        double perimeter = sideAB + sideBC + sideAC;
        System.out.println("Периметр треугольника: " + perimeter);
        
        double p = perimeter / 2;
        double square = Math.sqrt(p * (p - sideAB) * (p - sideBC) * (p - sideAC));
        System.out.println("Площадь треугольника: " + square);
    }
    
    private double calcSideLength(Point2D pt1, Point2D pt2) {
        //double tempX = (pt2.getX() - pt1.getX());
        //double tempY = (pt2.getY() - pt1.getY());
        //return Math.sqrt(tempX * tempX + tempY * tempY);
        return pt1.distance(pt2);
    }
    
    private Point2D readPoint(char symbol) {
        System.out.println("Введите координаты точки "+ symbol +":");
        return readCoodinates();
    }

    private Point2D readCoodinates() {
        double x = Util.readDouble(scanner);
        double y = Util.readDouble(scanner);
        return new Point2D.Double(x, y);
    }
    
}
