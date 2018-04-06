package ru.glaizier.com.hackerrank.smoothfunction;

import org.junit.Assert;
import org.junit.Test;

public class SmoothFunctionTest extends Assert {

    @Test
    public void testSmoothInPoint() {
        assertEquals(5, SmoothFunction.smoothInPoint(Double::doubleValue, 5), SmoothFunction.dx);
        assertEquals(-0.132351750, SmoothFunction.smoothInPoint(Math::sin, 25), SmoothFunction.dx);
    }


}
