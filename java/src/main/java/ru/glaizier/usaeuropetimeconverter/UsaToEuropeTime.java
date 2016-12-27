package ru.glaizier.usaeuropetimeconverter;

import java.util.regex.Pattern;

public class UsaToEuropeTime {

    private static String INPUT_PATTERN = "^([0,1][0-9]):[0-5][0-9]:[0-5][0-9](AM|PM)$";

    /**
     * Given a time in -hour AM/PM format, convert it to military (-hour) time.
     * <p>
     * Note: Midnight is  on a -hour clock, and  on a -hour clock. Noon is  on a -hour clock, and  on a -hour clock.
     * <p>
     * Input Format
     * <p>
     * A single string containing a time in -hour clock format (i.e.:  or ), where  and .
     * <p>
     * Output Format
     * <p>
     * Convert and print the given time in -hour format, where .
     * <p>
     * Sample Input
     * <p>
     * 07:05:45PM
     * Sample Output
     * <p>
     * 19:05:45
     */

    public static String convert(String usaTime) {
        if (usaTime == null || !usaTime.matches(INPUT_PATTERN))
            throw new IllegalArgumentException("Wrong input format!");

        String hours = Pattern.compile(INPUT_PATTERN).matcher(usaTime).group();
        if (Integer.parseInt(hours) > 12 || Integer.parseInt(hours) < 1)
            throw new IllegalArgumentException("Wrong input format!");
        String meridiem = Pattern.compile(INPUT_PATTERN).matcher(usaTime).group();

        if (meridiem.equals("AM"))
            if (hours.equals("12"))
                hours = "00";

        // TODO continue here
//        else if (meridiem.equals("PM"))
//            if (hours.equals())
        return null;
    }


}
