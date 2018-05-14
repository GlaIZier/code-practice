package ru.glaizier.jpoint2018.epam;

import java.util.AbstractMap;
import java.util.Map;
import java.util.function.Supplier;

public class Suppliers {
    private String s;

    public Map.Entry<String, String> say() {
        s = "joker";
        String ss ="g";
        Supplier<String> s1 = s::toUpperCase;
        Supplier<String> s2 = () -> s.toUpperCase();
        Supplier<String> s3 = ss::toUpperCase;
        // won't compile. Effectively final variables
//        Supplier<String> s4 = () -> ss.toUpperCase();
        s = "jokerAfter";
        ss = "gg";
        return new AbstractMap.SimpleImmutableEntry<>(s1.get(), s2.get());
    }
}
