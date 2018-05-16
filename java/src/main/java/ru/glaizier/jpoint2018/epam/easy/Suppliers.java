package ru.glaizier.jpoint2018.epam.easy;

import java.util.AbstractMap;
import java.util.Map;
import java.util.function.Supplier;

public class Suppliers {
    private String s;

    public Map.Entry<String, String> say() {
        s = "joker";
        String localS ="g";
        localS = "ggg";
        Supplier<String> s1 = s::toUpperCase;
        Supplier<String> s2 = () -> s.toUpperCase();
        Supplier<String> s3 = localS::toUpperCase;
        // won't compile. Effectively final variables
//        Supplier<String> s4 = () -> localS.toUpperCase();
        s = "jokerAfter";
        localS = "gg";
        return new AbstractMap.SimpleImmutableEntry<>(s1.get(), s2.get());
    }
}
