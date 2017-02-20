package ru.glaizier.jdk;

import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// https://docs.oracle.com/javase/tutorial/java/generics/subtyping.html
public class WildcardTest {

    @Test
    public void wildcardTest() {
        List<? extends Number> l = new ArrayList<Double>();
        l = new ArrayList<Integer>(); // can't add anything here because if the implementation
//        List<? extends Number> l = Arrays.asList(5, 6, 7); // u can't add here anything. Even null, because asList returns constant list

        // U can't add nothing except null here because if u could then eg
        // List<? extends Serializable> l = new ArrayList<String>();
        // l.add(5); // in String list we can add 5;
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
        Integer i = (Integer) l.get(0); // can't get Integer from there. U can only cast or get number and Object
        Object o = l.get(0);
//        Double d = (Double) l.get(0); // ClassCastException
    }

    @Test
    public void upperBoundTest() {
        List<? extends Number> l = Arrays.asList(1, 2, 3);
        Number n = l.get(1);
        Integer i = (Integer) l.get(1);

//        List<Number> ll = new ArrayList<Integer>(); // can't do it
        List<? extends Number> lll = new ArrayList<Integer>();
        List<? extends Integer> llll = new ArrayList<>();
        lll = llll;

        List<Number> nn = new ArrayList<>();
        nn.add(new Integer(5));
        nn.add(new Double(5.1));

        List<? extends Serializable> k = new ArrayList<String>();
//        k.add(5); // can't do it. That's why u can't add anything in such list except null

        List<? super Object> lo = new ArrayList<Object>();
        List<?> wl;
        wl = lo;
    }

    @Test
    public void unboundedTest() {
//        List<Object> o = new ArrayList<Integer>(); // can't do it
        List<?> u = new ArrayList<Integer>();
        u = Arrays.asList(1, 2, 3);
//        u.add(new Object()); // can't do it
        Object o = u.get(0);
        Integer i = (Integer) u.get(0);
    }

    @Test
    public void lowerBoundTest() {
        List<? super Integer> l = Arrays.asList(1, 2, 3);
        l = new ArrayList<Integer>();
        l = new ArrayList<Number>();
        l = new ArrayList<Object>();

        // We can add values in here
        l.add(new Integer(1));
        Object o = l.get(0);
        Integer i = (Integer) l.get(0);
//        i = l.get(0); // can't do it
//        l.add(new Double(1.1)); // can't do it
//        l.add(new Object()); // can't do it


        List<? super Serializable> s = new ArrayList<Serializable>();
        s.add("sa");
        s.add(5);
        Object o1 = s.get(0);
        String s1 = (String) s.get(0);
        Integer i1 = (Integer) s.get(1);
    }

}
