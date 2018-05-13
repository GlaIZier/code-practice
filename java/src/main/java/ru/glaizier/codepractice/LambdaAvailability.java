package ru.glaizier.codepractice;

import java.util.function.Supplier;

public class LambdaAvailability {

    public String s = "s";

    public int i = 1;

    public static void main(String[] args) {
        LambdaAvailability la = new LambdaAvailability();
        String ss = "ss";
        int ii = 1;
        Supplier supplier = () -> {
            la.s  = la.s + "1";
            la.i = la.i + 1;
//            ii++;
//            ss += "s";
            return "some";
        };
    }

}
