package ru.glaizier.jpoint2018.luxoft.day2;

/**
 * @author GlaIZier
 */
public class Quiz1 {

    private static void print(){
        System.out.println("A");
    }

    // What will be there?
    public static void main(String[] args) {
        ((Quiz1) null).print(); // AB because print is called statically, so no instances are needed
        System.out.println("B");
    }
}
