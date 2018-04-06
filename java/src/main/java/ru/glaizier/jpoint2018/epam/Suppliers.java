package ru.glaizier.jpoint2018.epam;

import java.util.AbstractMap;
import java.util.Map;
import java.util.function.Supplier;

public class Suppliers {
    private String s;

    public Map.Entry<String, String> say() {
        s = "joker";
        Supplier<String> s1 = s::toUpperCase;
        Supplier<String> s2 = () -> s.toUpperCase();
        s = "jokerAfter";
        return new AbstractMap.SimpleImmutableEntry<>(s1.get(), s2.get());
    }
}
