package ru.glaizier.jdk;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;


// https://docs.oracle.com/javase/tutorial/java/generics/subtyping.html
public class WildcardTest {

    @Test
    public void wildcardTest() {
//        List<? extends Number> l = new ArrayList<Integer>();
        List<? extends Number> l = Arrays.asList(5, 6, 7);

        // U can't add nothing except null here
//        l.add(new Object());
//        l.add(new Number() {
//            @Override
//            public int intValue() {
//                return 0;
//            }
//
//            @Override
//            public long longValue() {
//                return 0;
//            }
//
//            @Override
//            public float floatValue() {
//                return 0;
//            }
//
//            @Override
//            public double doubleValue() {
//                return 0;
//            }
//        });
//        l.add(5);
//        l.add(Integer.valueOf(5));
        l.add(null);

        Number n = l.get(0);
        Integer i = (Integer) l.get(0);
        Object o = l.get(0);
//        Double d = l.get(0); can't get Double from there. U can only cast or get number and Object
    }

}
