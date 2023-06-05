package o.mysin.simbirsoftappjava.practice;

public class Task3 {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(6, 10);
        System.out.println("Периметр прямоугольника: " + rectangle.perimeter());
        System.out.println("Площадь прямоугольника: " + rectangle.area());

        Square square = new Square(4);
        System.out.println("Периметр квадрата: " + square.perimeter());
        System.out.println("Площадь квадрата: " + square.area());

        Circle circle = new Circle(5);
        System.out.println("Периметр окружности круга: " + circle.perimeter());
        System.out.println("Площадь круга: " + circle.area());
    }

    interface Shape {
        double perimeter();

        double area();
    }

    static class Rectangle implements Shape {
        private double width;
        private double length;

        public Rectangle(double width, double length) {
            this.width = width;
            this.length = length;
        }

        @Override
        public double perimeter() {
            return 2 * (width + length);
        }

        @Override
        public double area() {
            return width * length;
        }
    }

    static class Square implements Shape {
        private double sideLength;

        public Square(double sideLength) {
            this.sideLength = sideLength;
        }

        @Override
        public double perimeter() {
            return 4 * sideLength;
        }

        @Override
        public double area() {
            return sideLength * sideLength;
        }
    }

    static class Circle implements Shape {
        private double diameter;

        public Circle(double diameter) {
            this.diameter = diameter;
        }

        @Override
        public double perimeter() {
            return Math.PI * diameter;
        }

        @Override
        public double area() {
            return Math.PI * Math.pow(diameter / 2, 2);
        }
    }
}
