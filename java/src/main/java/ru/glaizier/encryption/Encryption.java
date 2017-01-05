package ru.glaizier.encryption;


/**
 * https://www.hackerrank.com/challenges/encryption
 */
public class Encryption {

    public static String encrypt(String s) {
        assert s != null;
        assert !s.contains(" ");

        int cols = (int) Math.ceil(Math.sqrt(s.length()));

        StringBuilder result = new StringBuilder(s.length());

        for (int colIndex = 0; colIndex < cols; colIndex++) {
            for (int rowIndex = colIndex; rowIndex < s.length(); rowIndex += cols)
                result.append(s.charAt(rowIndex));
            if (colIndex != cols - 1)
                result.append(" ");
        }

        return result.toString();
    }

}
