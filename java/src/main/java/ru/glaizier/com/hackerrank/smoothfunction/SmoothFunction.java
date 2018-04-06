package ru.glaizier.com.hackerrank.smoothfunction;


import java.util.function.Function;

/**
 * Идея сглаживания (smoothing a function) играет важную роль в обработке сигналов. Если f — функция, а dx — некоторое малое число, то сглаженная версия f есть функция, значение которой в точке x есть среднее между f (x − dx), f (x) и f (x + dx).
 * Напишите функцию smooth, которая в качестве ввода принимает функцию, вычисляющую f, и возвращает функцию, вычисляющую сглаженную версию f.
 * Пример:
 * $smoothFunc = smooth(function ($sum) {
 * return sin(rad2deg($num));
 * }, 15);
 * $smoothFunc(10) // ~ 0.438
 */
public class SmoothFunction {

    public static final double dx = Math.pow(10, -10);

    public static double smoothInPoint(Function<Double, Double> f, double point) {
        return ((f.apply(point - dx) + f.apply(point) + f.apply(point + dx)) / 3);
    }

}
