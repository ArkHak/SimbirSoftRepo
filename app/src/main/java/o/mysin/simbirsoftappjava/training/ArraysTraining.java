package o.mysin.simbirsoftappjava.training;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Набор тренингов по работе с массивами в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ArraysTrainingTest.
 */
public class ArraysTraining {

    /**
     * Метод должен сортировать входящий массив
     * по возрастранию пузырьковым методом
     *
     * @param valuesArray массив для сортировки
     * @return отсортированный массив
     */
    public int[] sort(int[] valuesArray) {
        int countArray = valuesArray.length;
        for (int i = 0; i < countArray - 1; i++) {
            for (int j = 0; j < countArray - i - 1; j++) {
                if (valuesArray[j] > valuesArray[j + 1]) {
                    int tempValue = valuesArray[j];
                    valuesArray[j] = valuesArray[j + 1];
                    valuesArray[j + 1] = tempValue;
                }
            }
        }
        return valuesArray;
    }

    /**
     * Метод должен возвращать максимальное
     * значение из введенных. Если входящие числа
     * отсутствуют - вернуть 0
     *
     * @param values входящие числа
     * @return максимальное число или 0
     */
    public int maxValue(int... values) {
        if (values.length == 0) {
            return 0;
        } else {
            return Arrays.stream(values).max().getAsInt();
        }
    }

    /**
     * Переставить элементы массива
     * в обратном порядке
     *
     * @param array массив для преобразования
     * @return входящий массив в обратном порядке
     */
    public int[] reverse(int[] array) {
        int tempLastElement;

        for (int i = 0; i < array.length / 2; i++) {
            tempLastElement = array[array.length - i - 1];
            array[array.length - i - 1] = array[i];
            array[i] = tempLastElement;
        }

        return array;
    }

    /**
     * Метод должен вернуть массив,
     * состоящий из чисел Фибоначчи
     *
     * @param numbersCount количество чисел Фибоначчи,
     *                     требуемое в исходящем массиве.
     *                     Если numbersCount < 1, исходный
     *                     массив должен быть пуст.
     * @return массив из чисел Фибоначчи
     */
    public int[] fibonacciNumbers(int numbersCount) {
        if (numbersCount < 1) {
            return new int[]{};
        } else {
            int[] fibonacciNumbersArray = new int[numbersCount];
            if (numbersCount >= 1) fibonacciNumbersArray[0] = 1;
            if (numbersCount >= 2) fibonacciNumbersArray[1] = 1;
            for (int i = 2; i < numbersCount; i++) {
                fibonacciNumbersArray[i] = fibonacciNumbersArray[i - 1] + fibonacciNumbersArray[i - 2];
            }
            return fibonacciNumbersArray;
        }
    }

    /**
     * В данном массиве найти максимальное
     * количество одинаковых элементов.
     *
     * @param array массив для выборки
     * @return количество максимально встречающихся
     * элементов
     */
    public int maxCountSymbol(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        {
            Map<Integer, Integer> map = new HashMap<>();

            for (int j : array) {
                if (map.containsKey(j)) {
                    map.put(j, map.get(j) + 1);
                } else {
                    map.put(j, 1);
                }
            }

            return Collections.max(map.values());
        }
    }
}