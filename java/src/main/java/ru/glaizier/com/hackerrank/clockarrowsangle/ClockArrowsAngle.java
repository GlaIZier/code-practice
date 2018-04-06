package ru.glaizier.com.hackerrank.clockarrowsangle;

public class ClockArrowsAngle {
    public static final int CIRCLE_DEGREES = 360;
    public static final int MINUTE_ARROW_ANGLE_STEP = CIRCLE_DEGREES / 60;
    public static final int HOUR_ARROW_ANGLE_STEP = CIRCLE_DEGREES / 12;

    public static void main(String[] args) {
        System.out.println("getAngle(1, 0) = " + getAngle(1, 0));
        System.out.println("getAngle(1, 10) = " + getAngle(1, 10));
        System.out.println("getAngle(6, 0) = " + getAngle(6, 0));
        System.out.println("getAngle(11, 59) = " + getAngle(11, 59));
        System.out.println("getAngle(1, 10) = " + getAngle(1, 5));
    }

    // hours 0 to 11
    // minutes 0 to 59
    public static float getAngle(int hours, int minutes) {
        if (hours < 0 || hours > 11 || minutes < 0 || minutes > 59)
            throw new UnsupportedOperationException();

        float minutesArrowAngle = MINUTE_ARROW_ANGLE_STEP * minutes;
        float hoursArrowAngle = HOUR_ARROW_ANGLE_STEP * hours +
                HOUR_ARROW_ANGLE_STEP * (minutesArrowAngle / CIRCLE_DEGREES);

        float arrowsAngle = Math.abs(minutesArrowAngle - hoursArrowAngle);
        return  arrowsAngle > CIRCLE_DEGREES / 2 ? CIRCLE_DEGREES - arrowsAngle : arrowsAngle;
    }

}
