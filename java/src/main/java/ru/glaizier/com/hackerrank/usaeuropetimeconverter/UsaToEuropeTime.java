package ru.glaizier.com.hackerrank.usaeuropetimeconverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsaToEuropeTime {

    private static Pattern INPUT_PATTERN = Pattern.compile("^([0,1][0-9])(:[0-5][0-9]:[0-5][0-9])(AM|PM)$");

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
        if (usaTime == null)
            throw new IllegalArgumentException("Wrong input format!");
        Matcher matcher = INPUT_PATTERN.matcher(usaTime);
        // match must be invoked before group get
        if (!matcher.matches())
            throw new IllegalArgumentException("Wrong input format!");

        String hours = matcher.group(1);
        int hoursInt = Integer.parseInt(hours);
        if (hoursInt > 12 || hoursInt < 1)
            throw new IllegalArgumentException("Wrong input format!");
        String minutesSeconds = matcher.group(2);
        String meridiem = matcher.group(3);

        if (meridiem.equals("AM") && hours.equals("12"))
                hours = "00";
        else if (meridiem.equals("PM") && hoursInt != 12) {
            hoursInt += 12;
            hours = String.valueOf(hoursInt);
        }
        return hours + minutesSeconds;
    }


}
