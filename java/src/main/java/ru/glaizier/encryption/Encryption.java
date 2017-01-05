package ru.glaizier.encryption;


/**
 * https://www.hackerrank.com/challenges/encryption
 */
public class Encryption {

    public static String encript(String s) {
        assert s != null;
        assert !s.contains(" ");

        int rows = (int) Math.floor(Math.sqrt(s.length()));
        int cols = (int) Math.ceil(Math.sqrt(s.length()));

        return null;
    }

}
