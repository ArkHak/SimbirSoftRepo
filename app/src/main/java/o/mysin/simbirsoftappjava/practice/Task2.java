package o.mysin.simbirsoftappjava.practice;

/*
Task2
    Условия: есть начальная позиция на двумерной плоскости,
    можно осуществлять последовательность шагов по четырем направлениям up,
    down, left, right. Размерность каждого шага равна 1. Задание:
    - Создать enum Directions с возможными направлениями движения;
    - Создать метод, принимающий координаты и одно из направлений
        и возвращающий координаты после перехода по направлению;
    - Создать метод, осуществляющий несколько переходов от
        первоначальных координат и выводящий координаты после каждого перехода.
        Для этого внутри метода следует определить переменную location с начальными
        координатами (0,0) и массив направлений, содержащий элементы
        [up, up, left, down, left, down, down, right, right, down, right], и
        программно вычислить какие будут координаты у переменной location после
        выполнения этой последовательности шагов. Для вычисленеия результата каждого
        перемещения следует использовать созданный ранее метод перемещения на один шаг;
 */
public class Task2 {

    public static void main(String[] args) {

        Location location = new Location(0, 0);

        Directions[] steps = new Directions[]{Directions.UP, Directions.UP, Directions.LEFT,
                Directions.DOWN, Directions.LEFT, Directions.DOWN, Directions.DOWN,
                Directions.RIGHT, Directions.RIGHT, Directions.DOWN, Directions.RIGHT};

        task2(location, steps);
    }

    private static void task2(Location location, Directions[] steps) {
        for (Directions dir : steps) {
            move(location, dir);
            System.out.println(location);
        }
        System.out.println(location.getY());
    }

    public static void move(Location location, Directions direction) {
        switch (direction) {
            case UP:
                location.moveUp();
                break;
            case DOWN:
                location.moveDown();
                break;
            case LEFT:
                location.moveLeft();
                break;
            case RIGHT:
                location.moveRight();
                break;
        }
    }

    public enum Directions {
        UP, DOWN, LEFT, RIGHT
    }

    public static class Location {
        int x;
        int y;

        public Location() {
            this.x = 0;
            this.y = 0;
        }

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void moveUp() {
            this.y += 1;
        }

        public void moveDown() {
            this.y -= 1;
        }

        public void moveLeft() {
            this.x -= 1;
        }

        public void moveRight() {
            this.x += 1;
        }

        @Override
        public String toString() {
            return "Point(" + this.getX() + ";" + this.getY() + ")";
        }
    }
}
