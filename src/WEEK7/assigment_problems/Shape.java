package WEEK7.assigment_problems;

public abstract class Shape {

    private static int shapeCounter = 0;
    private final String shapeId;

    public Shape() {
        shapeCounter++;
        shapeId = "SH-" + shapeCounter;
    }

    public abstract double calculateArea();

    public void scale(double factor) {
        scale(factor, factor);
    }

    public void scale(double xFactor, double yFactor) {
    }

    public String getShapeId() {
        return shapeId;
    }

    static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }
}

class CircleShape extends Shape {

    private double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        radius = radius * (xFactor + yFactor) / 2;
    }
}

class SquareShape extends Shape {

    private double side;

    public SquareShape(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        side = side * (xFactor + yFactor) / 2;
    }
}