package ru.glaizier.jpoint2018.luxoft.day1;

public class Quiz3 {
    public static void main(String[] args) {
        String str = "true";
        if ("t" + "r" + "u" + "e" == "true") {
            System.out.println("1");
        }
        if (str + "" == "true") {
            System.out.println("2");
        }
        if (str.replace("T", "t") == "true") {
            System.out.println("3"); // 1, 3
        }
    }
}
