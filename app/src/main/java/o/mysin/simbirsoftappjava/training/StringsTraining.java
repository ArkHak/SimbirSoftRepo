package o.mysin.simbirsoftappjava.training;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see StringsTrainingTest.
 */
public class StringsTraining {

    /**
     * Метод по созданию строки,
     * состоящей из нечетных символов
     * входной строки в том же порядке
     * (нумерация символов идет с нуля)
     *
     * @param text строка для выборки
     * @return новая строка из нечетных
     * элементов строки text
     */
    public String getOddCharacterString(String text) {
        StringBuilder resultStr = new StringBuilder();
        char[] chars = text.toCharArray();
        for (int i = 1; i < chars.length; i = i + 2) {
            resultStr.append(chars[i]);
        }
        return resultStr.toString();
    }

    /**
     * Метод для определения количества
     * символов, идентичных последнему
     * в данной строке
     *
     * @param text строка для выборки
     * @return массив с номерами символов,
     * идентичных последнему. Если таких нет,
     * вернуть пустой массив
     */
    public int[] getArrayLastSymbol(String text) {
        if (!text.isEmpty()) {
            char[] chars = text.toCharArray();

            ArrayList<Integer> numChars = new ArrayList<>();
            char lastSymbol = chars[text.length() - 1];

            for (int i = 0; i < chars.length - 1; i++) {
                if (lastSymbol == chars[i]) numChars.add(i);
            }
            return numChars.stream().mapToInt(i -> i).toArray();
        } else {
            return new int[]{};
        }
    }

    /**
     * Метод по получению количества
     * цифр в строке
     *
     * @param text строка для выборки
     * @return количество цифр в строке
     */
    public int getNumbersCount(String text) {
        String regex = "\\d";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * Дан текст. Заменить все цифры
     * соответствующими словами.
     *
     * @param text текст для поиска и замены
     * @return текст, где цифры заменены словами
     */
    public String replaceAllNumbers(String text) {
        String regex = "\\d";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String match = matcher.group();
            String replacement = changeChar(match);
            text = text.replace(match, replacement);
        }
        return text;
    }

    private String changeChar(String symbol) {
        final String[] arrayNumberBase = new String[]{
                "zero",
                "one",
                "two",
                "three",
                "four",
                "five",
                "six",
                "seven",
                "eigth",
                "nine"

        };
        int currentNumber = Integer.parseInt(symbol);
        if (currentNumber < arrayNumberBase.length) {
            return arrayNumberBase[Integer.parseInt(symbol)];
        } else {
            return symbol;
        }
    }


    /**
     * Метод должен заменить заглавные буквы
     * на прописные, а прописные на заглавные
     *
     * @param text строка для изменения
     * @return измененная строка
     */
    public String capitalReverse(String text) {
        StringBuilder resultStr = new StringBuilder();
        char[] chars = text.toCharArray();
        for (char symbol : chars) {
            if (Character.isUpperCase(symbol)) {
                resultStr.append(Character.toLowerCase(symbol));
            } else {
                resultStr.append(Character.toUpperCase(symbol));
            }
        }
        return resultStr.toString();
    }

}
