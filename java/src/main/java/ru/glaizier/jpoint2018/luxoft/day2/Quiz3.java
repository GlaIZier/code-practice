package ru.glaizier.jpoint2018.luxoft.day2;

/**
 * @author GlaIZier
 */
public class Quiz3 {

    // What will be printed?
    public static void main(String[] args) {
        System.out.println(
            true ? false : true == true ? false : true
        ); // false because of the first true
    }
}
