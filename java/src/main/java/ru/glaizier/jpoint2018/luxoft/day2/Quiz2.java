package ru.glaizier.jpoint2018.luxoft.day2;

import java.util.Arrays;
import java.util.List;

/**
 * @author GlaIZier
 */
public class Quiz2 {

    public void func(Integer i) {
        System.out.println("Integer");
    }
    public void func(Double i) {
        System.out.println("Double");
    }
    public void func(Object o) {
        System.out.println("Object");
    }


    // What function will be called?
    public static void main(String[] args) {
        List<?> nums = Arrays.asList(1/2);
        new Quiz2().func(nums.get(0)); // Object
    }
}
