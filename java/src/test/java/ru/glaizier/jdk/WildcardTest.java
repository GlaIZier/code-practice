package ru.glaizier.jdk;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// https://docs.oracle.com/javase/tutorial/java/generics/subtyping.html
public class WildcardTest {

    @Test
    public void upperBoundWildcardBriefTest() {
        List<? extends Number> l = null;

        List<? extends Integer> lei = null;
        List<Integer> li = null;
        List<Double> ld = null;
        List<Number> ln = null;
        l = lei;
        l = li;
        l = ld;
        l = ln;

        List<Object> lo = null;
        List<?> lu = null;
        List<? super Number> lsn = null;
//        l = lo; // can't do it
//        l = lu; // can't do it
//        l = lsn; // can't do it

//        l.add(5); // can't do it
        l = new ArrayList<>();
        l.add(null);
        l = Collections.singletonList(1);
        Number n = l.get(0);
    }

    @Test
    public void lowerBoundWildcardBriefTest() {
        List<? super Number> l = null;

        List<Number> ln = null;
        List<Object> lo = null;
        List<? super Object> lso = null;
        l = ln;
        l = lo;
        l = lso;

        List<Integer> li = null;
        List<Double> ld = null;
        List<? super Integer> lsi = null;
        List<?> lu = null;
        List<? extends Number> len = null;
//        l = li; // can't do it
//        l = ld; // can't do it
//        l = lsi; // can't do it
//        l = lu; // can't do it
//        l = len; // can't do it

        l = new ArrayList<>();
        l.add(5);
        l.add(5.5);
        l.add(new Number() {
            @Override
            public int intValue() {
                return 0;
            }

            @Override
            public long longValue() {
                return 0;
            }

            @Override
            public float floatValue() {
                return 0;
            }

            @Override
            public double doubleValue() {
                return 0;
            }
        });
        l.add(null);
//        l.add(new Object()); // can't do it
        Object n = l.get(0);
    }

    @Test
    public void unBoundedWildcardBriefTest() {
        List<?> l = null;

        List<? extends Integer> lei = null;
        List<Integer> li = null;
        List<Double> ld = null;
        List<Number> ln = null;
        List<Object> lo = null;
        List<? super Number> lsn = null;
        l = lei;
        l = li;
        l = ld;
        l = ln;
        l = lo;
        l = lsn;

//        l.add(5); // can't do it
        l = new ArrayList<>();
        l.add(null);
        l = Collections.singletonList(1);
        Object n = l.get(0);
    }

    @Test
    public void withoutWildcardsBriefTest() {
        List<Integer> l = new ArrayList<>();
        l.add(4);
//        l.add(new Number() { //can't do it
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
        List<Number> ln = new ArrayList<>();
        ln.add(Integer.valueOf(5));
        ln.add(new Number() {
            @Override
            public int intValue() {
                return 0;
            }

            @Override
            public long longValue() {
                return 0;
            }

            @Override
            public float floatValue() {
                return 0;
            }

            @Override
            public double doubleValue() {
                return 0;
            }
        });
    }

}
