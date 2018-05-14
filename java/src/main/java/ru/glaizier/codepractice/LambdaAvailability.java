package ru.glaizier.codepractice;

import java.util.function.Supplier;

public class LambdaAvailability {

    public String s = "s";

    public int i = 1;

    class MyInt {
        public int myI;
    }

    private Supplier<Integer> buildSupplier() {
        System.out.println("buildSupplier this = " + this);
        int magic = 7;
        class MyLocalInt {
            public int myI;
        }

        new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("abstract supplier this = " + this);
                return null;
            }
        }.get();
//        magic++; can't do it either.
        return () -> {
            System.out.println("result lambda this " + this);
            int i = magic + 1;
            // not effective final. We can't modify local vars as they are in stack
//            magic++;
            // But ...
            new MyInt().myI++;
            new MyLocalInt().myI++;
            return i;
        };
    }

    public static void main(String[] args) {
        LambdaAvailability la = new LambdaAvailability();
        String ss = "ss";
        int ii = 1;
        Supplier supplier = () -> {
            la.s  = la.s + "1";
            la.i = la.i + 1;
//            ii++; not effective final
//            ss += "s"; nor effective final
            return "some";
        };
        la.buildSupplier().get();
    }

}
