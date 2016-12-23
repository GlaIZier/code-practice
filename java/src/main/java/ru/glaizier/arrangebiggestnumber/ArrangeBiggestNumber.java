package ru.glaizier.arrangebiggestnumber;

import java.util.Arrays;

/**
 * Реализуйте функцию arrangeBiggestNumber, которая составляет самое большое число из переданного массива чисел и возвращает его строковое представление. Например из чисел [3, 24, 4] мы можем составить такие: 3244, 3424, 2434, 2443, 4324, 4243 и самое больше из них это4324.
 * Пример:
 * 998764543431 == arrangeBiggestNumber([1, 34, 3, 98, 9, 76, 45, 4]);
 */
public class ArrangeBiggestNumber {

    public static String arrange(String[] a) {
        Arrays.sort(a, (o1, o2) -> {
            int charIndex = 0;
            // here we have numbers without corner cases
            while (charIndex < o1.length() && charIndex < o2.length()) {
                if (Character.compare(o1.charAt(charIndex), o2.charAt(charIndex)) != 0)
                    //reverse strings because we need descending order
                    return Character.compare(o2.charAt(charIndex), o1.charAt(charIndex));
                charIndex++;
            }
            if (charIndex == o1.length()) {
                while (charIndex < o2.length()) {
                    if (Character.compare(o1.charAt(o1.length() - 1), o2.charAt(charIndex)) != 0)
                        return Character.compare(o2.charAt(charIndex), o1.charAt(o1.length() - 1));
                    charIndex++;
                }
            } else {
                while (charIndex < o1.length()) {
                    if (Character.compare(o1.charAt(charIndex), o2.charAt(o2.length() - 1)) != 0)
                        return Character.compare(o2.charAt(o2.length() - 1), o1.charAt(charIndex));
                    charIndex++;
                }
            }
            return 0;
        });
        return Arrays.stream(a).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }
}
