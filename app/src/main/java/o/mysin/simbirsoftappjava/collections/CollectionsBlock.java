package o.mysin.simbirsoftappjava.collections;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see CollectionsBlockTest.
 */
public class CollectionsBlock<T extends Comparable> {

    /**
     * Даны два упорядоченных по убыванию списка.
     * Объедините их в новый упорядоченный по убыванию список.
     * Исходные данные не проверяются на упорядоченность в рамках данного задания
     *
     * @param firstList  первый упорядоченный по убыванию список
     * @param secondList второй упорядоченный по убыванию список
     * @return объединенный упорядоченный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask0( List<T> firstList, List<T> secondList) {
        if (firstList == null || secondList == null) {
            throw new NullPointerException();
        } else {
            List<T> sortedList = new ArrayList<>(firstList);
            sortedList.addAll(secondList);
            sortedList.sort(Collections.reverseOrder());
            return sortedList;
        }
    }

    /**
     * Дан список. После каждого элемента добавьте предшествующую ему часть списка.
     *
     * @param inputList с исходными данными
     * @return измененный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask1(@NonNull List<T> inputList) throws NullPointerException {
        List<T> tempList = new ArrayList<>();
        List<T> resultList = new ArrayList<>();
        for (T item : inputList) {
            resultList.add(item);
            if (!tempList.isEmpty()) resultList.addAll(tempList);
            tempList.add(item);
        }
        return resultList;
    }

    /**
     * Даны два списка. Определите, совпадают ли множества их элементов.
     *
     * @param firstList  первый список элементов
     * @param secondList второй список элементов
     * @return <tt>true</tt> если множества списков совпадают
     * @throws NullPointerException если один из параметров null
     */
    public boolean collectionTask2(@NonNull List<T> firstList, @NonNull List<T> secondList) throws NullPointerException {
        Set<T> firstSet = new HashSet<>(firstList);
        Set<T> secondSet = new HashSet<>(secondList);
        return firstSet.equals(secondSet);
    }

    /**
     * Создать список из заданного количества элементов.
     * Выполнить циклический сдвиг этого списка на N элементов вправо или влево.
     * Если N > 0 циклический сдвиг вправо.
     * Если N < 0 циклический сдвиг влево.
     *
     * @param inputList список, для которого выполняется циклический сдвиг влево
     * @param n         количество шагов циклического сдвига N
     * @return список inputList после циклического сдвига
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask3(@NonNull List<T> inputList, int n) throws NullPointerException {
        Collections.rotate(inputList, n);
        return inputList;
    }

    /**
     * Элементы списка хранят слова предложения.
     * Замените каждое вхождение слова A на B.
     *
     * @param inputList список со словами предложения и пробелами для разделения слов
     * @param a         слово, которое нужно заменить
     * @param b         слово, на которое нужно заменить
     * @return список после замены каждого вхождения слова A на слово В
     * @throws NullPointerException если один из параметров null
     */
    public List<String> collectionTask4(@NonNull List<String> inputList, String a,
                                        String b) {
        if (b == null || a == null) throw new NullPointerException();
        Collections.replaceAll(inputList, a, b);
        return inputList;
    }

    /*
      Задание подразумевает создание класса(ов) для выполнения задачи.

      Дан список студентов. Элемент списка содержит фамилию, имя, отчество, год рождения,
      курс, номер группы, оценки по пяти предметам. Заполните список и выполните задание.
      Упорядочите студентов по курсу, причем студенты одного курса располагались
      в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
      Определите самого старшего студента и самого младшего студентов.
      Для каждой группы найдите лучшего с точки зрения успеваемости студента.
     */
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        students.add(new Student(
                "Иванов",
                "Иван",
                "Иванович",
                1997,
                2,
                101,
                Arrays.asList(
                        new Subject(Subjects.MATH, 4),
                        new Subject(Subjects.HISTORY, 4),
                        new Subject(Subjects.LANGUAGE, 5),
                        new Subject(Subjects.PHYSICS, 3),
                        new Subject(Subjects.COMPUTER_SCIENCE, 4))
        ));

        students.add(new Student(
                "Петров",
                "Петр",
                "Петрович",
                1998,
                2,
                101,
                Arrays.asList(
                        new Subject(Subjects.MATH, 3),
                        new Subject(Subjects.HISTORY, 4),
                        new Subject(Subjects.LANGUAGE, 4),
                        new Subject(Subjects.PHYSICS, 5),
                        new Subject(Subjects.COMPUTER_SCIENCE, 4))
        ));

        students.add(new Student(
                "Сидоров",
                "Сидор",
                "Сидорович",
                1997,
                2,
                102,
                Arrays.asList(
                        new Subject(Subjects.MATH, 4),
                        new Subject(Subjects.HISTORY, 5),
                        new Subject(Subjects.LANGUAGE, 5),
                        new Subject(Subjects.PHYSICS, 4),
                        new Subject(Subjects.COMPUTER_SCIENCE, 5))));
        students.add(new Student(
                "Кузнецов",
                "Кузьма",
                "Кузьмич",
                1999,
                1,
                103,
                Arrays.asList(
                        new Subject(Subjects.MATH, 5),
                        new Subject(Subjects.HISTORY, 3),
                        new Subject(Subjects.LANGUAGE, 4),
                        new Subject(Subjects.PHYSICS, 5),
                        new Subject(Subjects.COMPUTER_SCIENCE, 4))));
        students.add(new Student(
                "Васильев",
                "Василий",
                "Васильевич",
                1998,
                1,
                103,
                Arrays.asList(
                        new Subject(Subjects.MATH, 3),
                        new Subject(Subjects.HISTORY, 5),
                        new Subject(Subjects.LANGUAGE, 4),
                        new Subject(Subjects.PHYSICS, 4),
                        new Subject(Subjects.COMPUTER_SCIENCE, 3))));
        students.add(new Student(
                "Новиков",
                "Новик",
                "Новикович",
                1999,
                1,
                104,
                Arrays.asList(
                        new Subject(Subjects.MATH, 4),
                        new Subject(Subjects.HISTORY, 4),
                        new Subject(Subjects.LANGUAGE, 3),
                        new Subject(Subjects.PHYSICS, 5),
                        new Subject(Subjects.COMPUTER_SCIENCE, 4))));
        students.add(new Student(
                "Морозов",
                "Мороз",
                "Морозович",
                1997,
                3,
                201,
                Arrays.asList(
                        new Subject(Subjects.MATH, 5),
                        new Subject(Subjects.HISTORY, 5),
                        new Subject(Subjects.LANGUAGE, 5),
                        new Subject(Subjects.PHYSICS, 4),
                        new Subject(Subjects.COMPUTER_SCIENCE, 4))));
        students.add(new Student(
                "Зайцев",
                "Захар",
                "Захарович",
                1998,
                3,
                201,
                Arrays.asList(
                        new Subject(Subjects.MATH, 4),
                        new Subject(Subjects.HISTORY, 4),
                        new Subject(Subjects.LANGUAGE, 4),
                        new Subject(Subjects.PHYSICS, 5),
                        new Subject(Subjects.COMPUTER_SCIENCE, 5))));
        students.add(new Student(
                "Смирнов",
                "Смирна",
                "Смирновна",
                1997,
                3,
                202,
                Arrays.asList(
                        new Subject(Subjects.MATH, 3),
                        new Subject(Subjects.HISTORY, 5),
                        new Subject(Subjects.LANGUAGE, 4),
                        new Subject(Subjects.PHYSICS, 4),
                        new Subject(Subjects.COMPUTER_SCIENCE, 3))));
        students.add(new Student(
                "Козлов",
                "Козел",
                "Козлович",
                1999,
                4,
                301,
                Arrays.asList(
                        new Subject(Subjects.MATH, 4),
                        new Subject(Subjects.HISTORY, 4),
                        new Subject(Subjects.LANGUAGE, 5),
                        new Subject(Subjects.PHYSICS, 4),
                        new Subject(Subjects.COMPUTER_SCIENCE, 5))));

        // Упорядочите студентов по курсу, причем студенты одного курса располагались в алфавитном порядке.
        Comparator<Student> scomp = new StudentCourseComparator().thenComparing(new StudentSurnameComparator().thenComparing(new StudentNameComparator()));
        TreeSet<Student> sortStudent = new TreeSet<>(scomp);
        sortStudent.addAll(students);

        //Найдите средний балл каждой группы по каждому предмету.
        Map<Integer, List<Subject>> averageGroupSub = new HashMap<>();
        for (Student student : students) {
            Integer group = student.getNumberGroup();
            if (!averageGroupSub.containsKey(group)) {
                averageGroupSub.put(group, student.grades);
            } else {
                List<Subject> currentList = averageGroupSub.get(group);
                List<Subject> tempList = new ArrayList<>();
                double tempMark;
                for (int i = 0; i < student.grades.size(); i++) {
                    assert currentList != null;
                    tempMark = (student.grades.get(i).grade + currentList.get(i).grade) / 2;
                    tempList.add(new Subject(Subjects.values()[i], tempMark));
                }
                averageGroupSub.put(group, tempList);
            }
        }

        //Определите самого старшего студента и самого младшего студентов.
        Comparator<Student> compYears = new StudentYearsComparator();
        TreeSet<Student> sortYearsStudent = new TreeSet<>(compYears);
        sortYearsStudent.addAll(students);
        Student yongStudent = sortYearsStudent.first();
        Student oldStudent = sortYearsStudent.last();

        //Для каждой группы найдите лучшего с точки зрения успеваемости студента.
        Map<Integer, Student> bestStudents = new HashMap<>();
        for (Student student : students) {
            Integer group = student.getNumberGroup();
            if (!bestStudents.containsKey(group)) {
                bestStudents.put(group, student);
            } else {
                if (Objects.requireNonNull(bestStudents.get(group)).getAverageMark() < student.getAverageMark()) {
                    bestStudents.put(group, student);
                }
            }
        }
    }

    static class Student {
        private String surname;
        private String name;
        private String patronymic;
        private int yearBirth;
        private int course;
        private Integer numberGroup;
        private List<Subject> grades;

        public Student(
                String surname,
                String name,
                String patronymic,
                int yearBirth,
                int course,
                int numberGroup,
                List<Subject> grade) {
            this.surname = surname;
            this.name = name;
            this.patronymic = patronymic;
            this.yearBirth = yearBirth;
            this.course = course;
            this.numberGroup = numberGroup;
            this.grades = grade;
        }


        public String getSurname() {
            return surname;
        }

        public String getName() {
            return name;
        }

        public String getPatronymic() {
            return patronymic;
        }

        public int getYearBirth() {
            return yearBirth;
        }

        public int getCourse() {
            return course;
        }

        public int getNumberGroup() {
            return numberGroup;
        }

        public List<Subject> getGrades() {
            return grades;
        }

        public double getAverageMark() {
            int countSubject = getGrades().size();
            double countMark = 0.0;
            for (Subject subject : grades) {
                countMark = countMark + subject.grade;
            }
            return countMark / countSubject;
        }
    }

    static class StudentCourseComparator implements Comparator<Student> {
        @Override
        public int compare(Student st1, Student st2) {
            if (st1.getCourse() > st2.getCourse()) {
                return 1;
            } else if (st1.getCourse() < st2.getCourse()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    static class StudentSurnameComparator implements Comparator<Student> {
        @Override
        public int compare(Student st1, Student st2) {
            return st1.getSurname().compareTo(st2.getSurname());
        }
    }

    static class StudentNameComparator implements Comparator<Student> {
        @Override
        public int compare(Student st1, Student st2) {
            return st1.getName().compareTo(st2.getName());
        }
    }

    static class StudentYearsComparator implements Comparator<Student> {
        @Override
        public int compare(Student st1, Student st2) {
            if (st1.getYearBirth() > st2.getYearBirth()) {
                return -1;
            } else if (st1.getYearBirth() < st2.getYearBirth()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    static class Subject {
        private Subjects title;
        private double grade;

        public Subject(Subjects title, double grade) {
            this.title = title;
            this.grade = grade;
        }

        public Subjects getTitle() {
            return title;
        }

        public double getGrade() {
            return grade;
        }
    }

    enum Subjects {
        MATH,
        HISTORY,
        LANGUAGE,
        PHYSICS,
        COMPUTER_SCIENCE
    }
}
