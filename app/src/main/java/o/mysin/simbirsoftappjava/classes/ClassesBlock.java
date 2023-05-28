package o.mysin.simbirsoftappjava.classes;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Набор заданий по работе с классами в java.
 * <p>
 * Задания подразумевают создание класса(ов), выполняющих задачу.
 * <p>
 * Проверка осуществляется ментором.
 */
public interface ClassesBlock {

    /*
      I

      Создать класс с двумя переменными. Добавить функцию вывода на экран
      и функцию изменения этих переменных. Добавить функцию, которая находит
      сумму значений этих переменных, и функцию, которая находит наибольшее
      значение из этих двух переменных.
     */
    public class TwoValues {
        private int value1;
        private int value2;

        public TwoValues(int value1, int value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        public void setValues(int value1, int value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        public void getValues() {
            System.out.println("Value 1: " + value1);
            System.out.println("Value 2: " + value2);
        }

        public int sumValue() {
            return value1 + value2;
        }

        public int maxValue() {
            return Math.max(value1, value2);
        }
    }

    /*
      II

      Создать класс, содержащий динамический массив и количество элементов в нем.
      Добавить конструктор, который выделяет память под заданное количество элементов.
      Добавить методы, позволяющие заполнять массив случайными числами,
      переставлять в данном массиве элементы в случайном порядке, находить количество
      различных элементов в массиве, выводить массив на экран.
     */

    public class DynamicArray {
        private int[] array;
        private int size;

        public DynamicArray(int size) {
            this.array = new int[size];
            this.size = size;
        }

        public void fillArrayRandom(int bound) {
            Random random = new Random();
            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(bound);
            }
        }

        public void shuffleArrayRandom() {
            Random random = new Random();
            for (int i = 0; i < size; i++) {
                int randCount = random.nextInt(size);
                int temp = array[i];
                array[i] = array[randCount];
                array[randCount] = temp;
            }
        }

        public int countSets() {
            Set<Integer> set = new HashSet<Integer>();
            for (int i = 0; i < size; i++) {
                set.add(array[i]);
            }
            return set.size();
        }

        public void printArray() {
            System.out.println(Arrays.toString(array));
        }
    }

    /*
      III

      Описать класс, представляющий треугольник. Предусмотреть методы для создания объектов,
      вычисления площади, периметра и точки пересечения медиан.
      Описать свойства для получения состояния объекта.
     */

    public class Triangle {
        private double sideA;
        private double sideB;
        private double sideC;

        public Triangle(double sideA, double sideB, double sideC) {
            this.sideA = sideA; // напротив угла в точке А
            this.sideB = sideB; // напротив угла в точке B
            this.sideC = sideC; // напротив угла в точке C
        }

        //S=sqrt(p * (p−a)(p−b)(p−c) )
        public double getArea() {
            double p = getPerimeter() / 2;
            return Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
        }

        public double getPerimeter() {
            return sideA + sideB + sideC;
        }

        public double heightOnSideA() {
            return (2 * getArea()) / sideA;
        }

        //x = (1/3) * (x1 + x2 + x3)
        //y = (1/3) * (y1 + y2 + y3)
        public Point getCentroid() {
            double x = (1.0 / 3.0) * (getXSideA() + getXSideB() + getXSideC());
            double y = (1.0 / 3.0) * (getYSideA() + getYSideB() + getYSideC());
            return new Point(x, y);
        }

        private double getXSideA() {
            return 0.0; //точка A находится в начале координат
        }

        private double getXSideB() {
            return sideC; //точка B находится на оси X
        }

        //теорема косинусов
        private double getXSideC() {
            return (Math.pow(sideB, 2) + Math.pow(sideC, 2) - Math.pow(sideA, 2)) / (2.0 * sideC);

        }

        private double getYSideA() {
            return 0.0;
        }

        private double getYSideB() {
            return 0.0; //точка B находится на оси X
        }

        //теореме Пифагора
        private double getYSideC() {
            return Math.sqrt(Math.pow(sideB, 2) - Math.pow(getXSideC(), 2));
        }

        static class Point {
            double x;
            double y;

            public Point(double x, double y) {
                this.x = x;
                this.y = y;
            }
        }

        // Описать свойства для получения состояния объекта.
        public double getSideA() {
            return sideA;
        }

        public double getSideB() {
            return sideB;
        }

        public double getSideC() {
            return sideC;
        }
    }
    /*
      IV

      Составить описание класса для представления времени.
      Предусмотреть возможности установки времени и изменения его отдельных полей
      (час, минута, секунда) с проверкой допустимости вводимых значений.
      В случае недопустимых значений полей выбрасываются исключения.
      Создать методы изменения времени на заданное количество часов, минут и секунд.
     */

    public class Watch {
        private int hours;
        private int minutes;
        private int seconds;

        public Watch(int hours, int minutes, int seconds) {
            if (checkCurrentHours(hours) && checkCurrentMinutesAndSeconds(minutes) && checkCurrentMinutesAndSeconds(seconds)) {
                this.hours = hours;
                this.minutes = minutes;
                this.seconds = seconds;
            } else {
                throw new IllegalArgumentException("Недопустимое значение времени");
            }
        }

        private Boolean checkCurrentHours(int hours) {
            return hours >= 0 && hours <= 24;
        }

        private Boolean checkCurrentMinutesAndSeconds(int value) {
            return value >= 0 && value < 60;
        }

        public void setHours(int hours) {
            if (checkCurrentHours(hours)) {
                this.hours = hours;
            } else {
                throw new IllegalArgumentException("Недопустимое значение времени");
            }
        }

        public void setMinutes(int minutes) {
            if (checkCurrentMinutesAndSeconds(minutes)) {
                this.minutes = minutes;
            } else {
                throw new IllegalArgumentException("Недопустимое значение времени");
            }
        }

        public void setSeconds(int seconds) {
            if (checkCurrentMinutesAndSeconds(seconds)) {
                this.seconds = seconds;
            } else {
                throw new IllegalArgumentException("Недопустимое значение времени");
            }
        }

    }

    /*
      V

      Класс Абонент: Идентификационный номер, Фамилия, Имя, Отчество, Адрес,
      Номер кредитной карточки, Дебет, Кредит, Время междугородных и городских переговоров;
      Конструктор; Методы: установка значений атрибутов, получение значений атрибутов,
      вывод информации. Создать массив объектов данного класса.
      Вывести сведения относительно абонентов, у которых время городских переговоров
      превышает заданное.  Сведения относительно абонентов, которые пользовались
      междугородной связью. Список абонентов в алфавитном порядке.
     */

    public class Subscriber implements Comparable<Subscriber> {
        private int id;
        private String surname;
        private String name;
        private String patronymic;
        private String address;
        private String numberCreditCart;
        private Double Debit;
        private Double Credit;
        private int timeIntercityNegotiations;
        private int timeUrbanNegotiations;

        public Subscriber(
                int id,
                String surname,
                String name,
                String patronymic,
                String address,
                String numberCreditCart,
                Double debit,
                Double credit,
                int timeIntercityNegotiations,
                int timeUrbanNegotiations
        ) {
            this.id = id;
            this.surname = surname;
            this.name = name;
            this.patronymic = patronymic;
            this.address = address;
            this.numberCreditCart = numberCreditCart;
            Debit = debit;
            Credit = credit;
            this.timeIntercityNegotiations = timeIntercityNegotiations;
            this.timeUrbanNegotiations = timeUrbanNegotiations;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPatronymic() {
            return patronymic;
        }

        public void setPatronymic(String patronymic) {
            this.patronymic = patronymic;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNumberCreditCart() {
            return numberCreditCart;
        }

        public void setNumberCreditCart(String numberCreditCart) {
            this.numberCreditCart = numberCreditCart;
        }

        public Double getDebit() {
            return Debit;
        }

        public void setDebit(Double debit) {
            Debit = debit;
        }

        public Double getCredit() {
            return Credit;
        }

        public void setCredit(Double credit) {
            Credit = credit;
        }

        public int getTimeIntercityNegotiations() {
            return timeIntercityNegotiations;
        }

        public void setTimeIntercityNegotiations(int timeIntercityNegotiations) {
            this.timeIntercityNegotiations = timeIntercityNegotiations;
        }

        public int getTimeUrbanNegotiations() {
            return timeUrbanNegotiations;
        }

        public void setTimeUrbanNegotiations(int timeUrbanNegotiations) {
            this.timeUrbanNegotiations = timeUrbanNegotiations;
        }

        public void getInfoSubscriber() {
            System.out.println("ID: " + this.getId());
            System.out.println("ФИО: " + this.getSurname() + " " + this.getName() + " " + this.getPatronymic());
            System.out.println("Адрес: " + this.getAddress());
            System.out.println("Номер карты: " + this.getNumberCreditCart());
            System.out.println("Дебет: " + this.getDebit());
            System.out.println("Кредит: " + this.getCredit());
            System.out.println("Время междугородных переоговоров: " + this.getTimeIntercityNegotiations());
            System.out.println("Время городских переоговоров: " + this.getTimeUrbanNegotiations());
        }

        public void getSubscribesForWitchUrbanNegotiationsExceedsSpecified(double time) {
            if (this.getTimeUrbanNegotiations() > time) this.getInfoSubscriber();
        }

        public void getSubscribesUsedIntercityNegotiations() {
            if (this.getTimeIntercityNegotiations() > 0) this.getInfoSubscriber();
        }

        @Override
        public int compareTo(Subscriber subscriber) {
            return surname.compareTo(subscriber.getSurname());
        }
    }

    public static void main(String[] args) {
        Subscriber[] subscribers = new Subscriber[3];
        subscribers[0] = new Subscriber(
                1,
                "Ivanov",
                "Ivan",
                "Ivanovich",
                "Moscow, Russia",
                "1111 2222 3333 4444",
                1000.0,
                500.0,
                50,
                20
        );
        subscribers[1] = new Subscriber(
                2,
                "Petrov",
                "Petr",
                "Petrovich",
                "St. Petersburg, Russia",
                "5555 6666 7777 8888",
                2000.0,
                1000.0,
                100,
                40
        );
        subscribers[2] = new Subscriber(
                3,
                "Sidorov",
                "Sidor",
                "Sidorovich",
                "Novosibirsk, Russia",
                "9999 8888 7777 6666",
                0.0,
                0.0,
                150,
                50
        );

        for (Subscriber subscriber : subscribers) {
            subscriber.getSubscribesForWitchUrbanNegotiationsExceedsSpecified(10.0);
        }

        for (Subscriber subscriber : subscribers) {
            subscriber.getSubscribesUsedIntercityNegotiations();
        }

        Arrays.sort(subscribers);
        for (Subscriber subscriber : subscribers) {
            subscriber.getInfoSubscriber();
        }

    }
    /*
      VI

      Задача на взаимодействие между классами. Разработать систему «Вступительные экзамены».
      Абитуриент регистрируется на Факультет, сдает Экзамены. Преподаватель выставляет Оценку.
      Система подсчитывает средний бал и определяет Абитуриента, зачисленного в учебное заведение.
     */

    public class Student {
        private String name;
        private String surname;
        private List<Exam> exams = new ArrayList<>();
        private double averageScoreExam;

        public Student(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }

        public void addExam(Exam exam) {
            exams.add(exam);
            calculateAverageScore();
        }

        private void calculateAverageScore() {
            double sum = 0;
            for (Exam exam : exams) {
                sum += exam.getScore();
            }
            averageScoreExam = sum / exams.size();
        }

        public double getAverageScore() {
            return averageScoreExam;
        }

        public String getFullName() {
            return name + " " + surname;
        }

    }


    public class Exam {
        private String name;
        private int score;

        public Exam(String name) {
            this.name = name;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getScore() {
            return score;
        }
    }

    public class Faculty {
        private String name;
        private List<Student> students = new ArrayList<>();
        private List<Student> acceptedStudents = new ArrayList<>();

        public Faculty(String name) {
            this.name = name;
        }

        public void registerStudent(Student student) {
            students.add(student);
        }


        public void determineAcceptedStudent() {
            for (Student student : students) {
                if (student.getAverageScore() >= 3.5) {
                    acceptedStudents.add(student);
                }
            }
        }

        public List<Student> getAcceptedStudents() {
            return acceptedStudents;
        }
    }

    public class Teacher {
        private String name;
        private String surname;

        public Teacher(String name, String surname) {
            this.name = surname;
            this.name = surname;
        }

        public void setScore(Exam exam, Student student, int score) {
            exam.setScore(score);
            student.addExam(exam);
        }
    }

    public static void task6(String[] args) {
        Student student1 = new Student("Олег", "Олегович");
        Student student2 = new Student("Юлия", "Станиславовна");

        Faculty faculty = new Faculty("ИБ");
        faculty.registerStudent(student1);
        faculty.registerStudent(student2);

        Exam exam1 = new Exam("Математика");
        Exam exam2 = new Exam("Информатика");


        Teacher teacherMath = new Teacher("Василий", "Петрович");
        teacherMath.setScore(exam1, student1, 4);
        teacherMath.setScore(exam1, student2, 5);


        Teacher teacherComputerScience = new Teacher("Иван", "Сидоров");
        teacherComputerScience.setScore(exam1, student1, 2);
        teacherComputerScience.setScore(exam2, student2, 4);

        faculty.determineAcceptedStudent();
        List<Student> acceptedStudents = faculty.getAcceptedStudents();

        System.out.println("Зачисленны: ");
        for (Student student : acceptedStudents) {
            System.out.println(student.getFullName());
        }
    }

    /*
      VII

      Задача на взаимодействие между классами. Разработать систему «Интернет-магазин».
      Товаровед добавляет информацию о Товаре. Клиент делает и оплачивает Заказ на Товары.
      Товаровед регистрирует Продажу и может занести неплательщика в «черный список».
     */

    public class Client {
        private int id;
        private String name;
        private static int idCount = 1;

        public Client(String name) {
            this.id = idCount++;
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


    public class Product {
        private String name;
        private double price;
        private int count;

        public Product(String name, double price, int count) {
            this.name = name;
            this.price = price;
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }


    public class Order {
        private List<Product> products = new ArrayList<>();
        private boolean isPaid;
        private Client client;

        public Order(Client client) {
            this.client = client;
        }

        public void addProduct(Product product) {
            products.add(product);
        }

        public double getTotalPrice() {
            double totalPrice = 0;
            for (Product product : products) {
                totalPrice += product.getPrice();
            }
            return totalPrice;
        }

        public void pay() {
            isPaid = true;
        }

        public boolean isPaid() {
            return isPaid;
        }

        public Client getClient() {
            return client;
        }

        public List<Product> getProducts() {
            return products;
        }
    }

    public class Merchandiser {
        private List<Order> orders = new ArrayList<>();
        private List<Client> blackList = new ArrayList<>();

        public void registerOrder(Order order) {
            orders.add(order);
        }

        public void registerSale(Order order) {
            if (order.isPaid()) {
                for (Product product : order.getProducts()) {
                    product.setCount(product.getCount() - 1);
                }
            }
        }

        public void addToBlackList(Client client) {
            blackList.add(client);
        }

        public boolean isInBlackList(Client client) {
            return blackList.contains(client);
        }
    }


    public static void task7(String[] args) {

        Client client = new Client("Олег");

        Product product1 = new Product("PS5", 50_000, 5);
        Product product2 = new Product("MacBook Pro ", 150_000, 3);

        Order order = new Order(client);
        order.addProduct(product1);
        order.addProduct(product2);
        order.pay();

        Merchandiser merchandiser = new Merchandiser();
        merchandiser.registerOrder(order);
        merchandiser.registerSale(order);

        System.out.println("Продано");

        merchandiser.addToBlackList(client);
        merchandiser.isInBlackList(client);
    }
}
