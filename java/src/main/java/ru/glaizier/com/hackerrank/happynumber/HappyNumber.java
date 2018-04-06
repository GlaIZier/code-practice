package ru.glaizier.com.hackerrank.happynumber;


import java.math.BigDecimal;

/**
 * Назовем счастливыми числами те, которые в результате ряда преобразований вида "сумма квадратов цифр" превратятся в единицу. Например:
 * 7 => 72 = 49, 49 => 42 + 92 = 16 + 81 = 97, 97 => 92 + 72 = 81 + 49 = 130, 130 => 12 + 32 + 02 = 10, 10 => 12 + 02 = 1.
 * Вывод: исходное число 7 - счастливое.
 * Реализуйте и экспортируйте функцию happyNumber, которая должна вернуть true, если число счастливое, и false если нет. Количество итераций процесса поиска необходимо ограничить числом 10.
 */
public class HappyNumber {

    public static boolean isHappyNumber(int number) {
        if (number < 0)
            throw new IllegalArgumentException("Only positive Integer is acceptable");

        // avoid overflow number
        BigDecimal current = new BigDecimal(number);
        int count = 0;
        while (current.compareTo(BigDecimal.valueOf(1)) != 0 && count < 10) {
            BigDecimal newNumber = new BigDecimal(0);
            while (current.compareTo(BigDecimal.valueOf(0)) != 0) {
                int digit = current.remainder(BigDecimal.valueOf(10)).intValue();
                int powerOfTwoDigit = digit * digit;
                newNumber = newNumber.add(BigDecimal.valueOf(powerOfTwoDigit));
                current = current.divideToIntegralValue(BigDecimal.valueOf(10));
            }
            current = newNumber;
            count++;
        }
        return (current.compareTo(BigDecimal.valueOf(1)) == 0);
    }


}
