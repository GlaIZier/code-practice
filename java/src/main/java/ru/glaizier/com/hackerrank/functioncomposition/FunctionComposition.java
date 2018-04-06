package ru.glaizier.com.hackerrank.functioncomposition;

import java.util.List;
import java.util.function.Function;

public class FunctionComposition {

    public static <T> T compose(List<Function<T, T>> functions, T startValue) {
        T currentValue = startValue;
        for (Function<T, T> function : functions) {
            currentValue = function.apply(currentValue);
        }
        return currentValue;
    }

}
